package com.shark.pcf.service.impl;

import com.shark.common.exception.FatalBizException;
import com.shark.common.utils.security.Md5Utils;
import com.shark.pcf.entity.PcfUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * Created by win7 on 2014/12/27.
 */
@Service
public class PcfPasswordServiceImpl {


    public void validate(PcfUser user, String password) {

        FatalBizException.throwWhenTrue(!matches(user, password), "用户名或密码不正确");


    }

    public boolean matches(PcfUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUserCd(), newPassword, ""));
    }


    public String encryptPassword(String username, String password, String salt) {
        return (Md5Utils.hash(password));
    }


    public static void main(String[] args) {
        System.out.println(new PcfPasswordServiceImpl().encryptPassword("monitor", "123456", "iY71e4d123"));
        System.out.println(new PcfPasswordServiceImpl().encryptPassword("1", "11", ""));
        System.out.println("111=" + Md5Utils.hash(Md5Utils.hash("admin123456")));
        System.out.println("222=" + Md5Utils.hash(("d7c6c07a0a04ba4e65921e2f90726384")));
        System.out.println("222=" + Md5Utils.hash(Md5Utils.hash("d7c6c07a0a04ba4e65921e2f90726384")));
        System.out.println("2221=" + Md5Utils.hash(Md5Utils.hash((Md5Utils.hash("admin123456")))));

        System.out.println("333=" +new SimpleHash("md5", "12345678", null, 1).toHex());
        System.out.println("333=" + Md5Utils.hash(Md5Utils.hash("12345678")));
        System.out.println("333=" +new SimpleHash("md5", "12345678", null, 2).toHex());
        System.out.println("333=" +new SimpleHash("md5", "25d55ad283aa400af464c76d713c07ad", null, 1).toHex());
        //111=224cf2b695a5e8ecaecfb9015161fa4b
        //222=ed7bf4b59cd04ac01b0dfc3664769a73
    }
}
