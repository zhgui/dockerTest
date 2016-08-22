package com.shark.pcf.service.impl;

import com.shark.common.entity.search.SearchOperator;
import com.shark.common.entity.search.Searchable;
import com.shark.common.exception.FatalBizException;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.PcfResource;
import com.shark.pcf.repository.PcfResourceRepository;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.service.PcfUserService;
import com.shark.pcf.vo.MenuVO;
import com.shark.pcf.vo.PcfResourceVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by win7 on 2014/12/3.
 */
@Service
public class PcfResourceServiceImpl extends BaseService<PcfResource, Long> implements PcfResourceService {
    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfResourceRepository resourceRepository;

    @Autowired
    private PcfUserService userService;

    public ReportPage findByCond(Searchable searchable) {
        String sltFlds = " PERMISSION_ID,PERMISSION_NAME,PERMISSION,NOTES,DELETE_FLAG,SORT_KEY,date_format(RECORD_DATE,'%Y-%m-%d %H:%i:%s') RECORD_DATE";
        String fromTbl = " from PCF_PERMISSION_T where  " + SearchConditions.DFT_GROUP + "  order by SORT_KEY ";
        SearchConditions conditions = SearchConditions.forNoGroupSearch(searchable, fromTbl, sltFlds);
        return condQueryBO.searchPage(conditions);
    }

    public PcfResourceVO save(PcfResourceVO resourceVO) {
        getParentIds(resourceVO);
        PcfResource resource = new PcfResource();
        ReflectUtils.copyAllPropertiesByName(resourceVO, resource, true);
        resource = resourceRepository.save(resource);
        ReflectUtils.copyAllPropertiesByName(resource, resourceVO, true);
        return resourceVO;
    }

    public PcfResourceVO update(PcfResourceVO resourceVO) {
        getParentIds(resourceVO);

        PcfResource resource = resourceRepository.findOne(resourceVO.getResourceId());
        ReflectUtils.copyAllPropertiesByName(resourceVO, resource, false);
        resource = resourceRepository.save(resource);
        ReflectUtils.copyAllPropertiesByName(resource, resourceVO, true);
        return resourceVO;
    }

    private void getParentIds(PcfResourceVO resourceVO) {
        //检查父级
        PcfResource parentResource = resourceRepository.findOne(resourceVO.getParentId());
        if (parentResource != null) {
            String parentIds = parentResource.getId() + "/";
            while (parentResource.getParentId() >= 0) {
                parentResource = resourceRepository.findOne(parentResource.getParentId());
                parentIds = parentResource.getId() + "/" + parentIds;
            }
            //拼接所有父级资源串
            resourceVO.setParentIds(parentIds);
        }
    }


    public PcfResourceVO findOneVO(Long resourceId) {
        PcfResource resource = findOne(resourceId);
        PcfResourceVO resourceVO = new PcfResourceVO();
        ReflectUtils.copyAllPropertiesByName(resource, resourceVO, true);

        PcfResource parentResource = findOne(resource.getParentId());
        if (parentResource != null) {
            resourceVO.setParentName(parentResource.getResourceName());
        }
        return resourceVO;
    }


    public List<ZTree<Long>> findTree() {
        List<PcfResource> list = findAll(new Sort(Sort.Direction.ASC, "parentId","sortKey"));
        List<ZTree<Long>> trees = new ArrayList<ZTree<Long>>();
        for (PcfResource resource : list) {
            ZTree<Long> zTree = new ZTree<Long>();
            zTree.setIconSkin(StringUtils.defaultString(resource.getIcon(), ""));
            zTree.setId(resource.getId());
            zTree.setIsParent(false);
            zTree.setName(resource.getResourceName());
            zTree.setNocheck(true);
            zTree.setOpen(false);
            zTree.setpId(resource.getParentId());
            zTree.setRoot(resource.getParentId() < 0);
            trees.add(zTree);
        }
        return trees;
    }

    public List<MenuVO> findMenus(Long userId) {
        Searchable searchable =
                Searchable.newSearchable()
                        .addSearchFilter("deleteFlag", SearchOperator.eq, PcfResource.DELETEFLAG_VAILD)
                        .addSort(new Sort(Sort.Direction.DESC, "parentId", "sortKey"));

        List<PcfResource> resources = findAllWithSort(searchable);

        Set<String> userPermissions = userService.findStringPermissions(userId);

        Iterator<PcfResource> iter = resources.iterator();
        while (iter.hasNext()) {
            PcfResource resource = iter.next();
            //子节点，并且没权限才删除
            if (isLeaf(resource, resources) && !hasPermission(resource, userPermissions)) {
                iter.remove();
            }
        }

        return convertToMenus(resources);
    }

    private boolean isLeaf(PcfResource resource, List<PcfResource> resources) {

        for (PcfResource res : resources) {
            if (res.getParentId().equals(resource.getId())) {
                return false;
            }
        }
        return true;
    }

    public static List<MenuVO> convertToMenus(List<PcfResource> resources) {

        if (resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        PcfResource rootRes = null;
        for (PcfResource resource : resources) {
            if (resource.getParentId() < 0) {
                rootRes = resource;
            }
        }

        FatalBizException.throwWhenNull(rootRes, "资源根节点不存在");
        resources.remove(rootRes);

        MenuVO root = convertToMenu(rootRes);

        recursiveMenu(root, resources);
        List<MenuVO> menus = root.getChildren();
        removeNoLeafMenu(menus);

        return menus;
    }

    private static void removeNoLeafMenu(List<MenuVO> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            MenuVO m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && org.springframework.util.StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

    private static void recursiveMenu(MenuVO menu, List<PcfResource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
            PcfResource resource = resources.get(i);
            if (resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (MenuVO subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }

    private static MenuVO convertToMenu(PcfResource resource) {
        return new MenuVO(resource.getId(), resource.getResourceName(), resource.getIcon(), resource.getUrl());
    }

    private boolean hasPermission(PcfResource resource, Set<String> userPermissions) {
//        String actualResourceIdentity = findActualResourceIdentity(resource);
        if (org.springframework.util.StringUtils.isEmpty(resource.getIdentify())) {
            return true;
        }

        for (String permission : userPermissions) {
            if (hasPermission(permission, resource.getIdentify())) {
                return true;
            }
        }

        return false;
    }

    private boolean hasPermission(String permission, String actualResourceIdentity) {

        //得到权限字符串中的 资源部分，如a:b:create --->资源是a:b
        String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));

        //如果权限字符串中的资源 是 以资源为前缀 则有权限 如a:b 具有a:b的权限
        /*if (permissionResourceIdentity.startsWith(actualResourceIdentity)) {
            return true;
        }*/


        //模式匹配
        WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
        WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

        return p1.implies(p2) || p2.implies(p1);
    }

    /**
     * 得到真实的资源标识  即 父亲:儿子
     *
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(PcfResource resource) {

        if (resource == null) {
            return null;
        }

        StringBuilder s = new StringBuilder(resource.getIdentify());

        boolean hasResourceIdentity = !org.springframework.util.StringUtils.isEmpty(resource.getIdentify());

        PcfResource parent = findOne(resource.getParentId());
        while (parent != null) {
            if (!org.springframework.util.StringUtils.isEmpty(parent.getIdentify())) {
                s.insert(0, parent.getIdentify() + ":");
                hasResourceIdentity = true;
            }
            parent = findOne(parent.getParentId());
        }

        //如果用户没有声明 资源标识  且父也没有，那么就为空
        if (!hasResourceIdentity) {
            return "";
        }


        //如果最后一个字符是: 因为不需要，所以删除之
        int length = s.length();
        if (length > 0 && s.lastIndexOf(":") == length - 1) {
            s.deleteCharAt(length - 1);
        }

        //如果有儿子 最后拼一个*
        boolean hasChildren = false;
        for (PcfResource r : findAll()) {
            if (resource.getId().equals(r.getParentId())) {
                hasChildren = true;
                break;
            }
        }
        if (hasChildren) {
            s.append(":*");
        }

        return s.toString();
    }
}
