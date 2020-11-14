package com.inet;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.inet.code.entity.User;
import com.inet.code.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class INetApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads1() {
        System.out.println(IdUtil.randomUUID());
    }

    @Test
    void configLoads2(){
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        System.out.println(md5.digestHex("123"));//202cb962ac59075b964b07152d234b70
        System.out.println(DigestUtil.md5Hex("123"));
    }

    /**
     * 测试登录请求
     * @author HCY
     * @since 2020-11-14
     */
    @Test
    void configLoads3(){
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String account = "huchengyea@163.com";
        String password = md5.digestHex("123");
        User user = userMapper.getLogin(account, password);
        System.out.println(user);
    }

}
