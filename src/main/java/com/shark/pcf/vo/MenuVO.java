package com.shark.pcf.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 界面是那个使用的菜单对象
 */
public class MenuVO implements Serializable {
    private Long id;
    private String name;
    private String icon;
    private String url;

    private List<MenuVO> children;

    public MenuVO(Long id, String name, String icon, String url) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuVO> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

    /**
     * @return
     */
    public boolean isHasChildren() {
        return !getChildren().isEmpty();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }
}
