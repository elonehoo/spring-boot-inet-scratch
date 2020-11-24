package com.inet;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.inet.code.entity.User;
import com.inet.code.mapper.UserMapper;
import com.inet.code.utlis.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class INetApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

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

    @Test
    void configLoads4(){
        Boolean one = true;
        Boolean two = false;
        if (!one.equals(two)){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }

    @Test
    void configLoads5(){
        Date date = new Date();
        if (!date.equals("")){
            System.out.println(1);
        }else {
            System.out.println(2);
        }
    }

    @Test
    void configLoads6(){
        redisTemplate.opsForValue().set("key1","hcy",60*5, TimeUnit.SECONDS);
    }

    @Test
    void configLoads7(){
        redisTemplate.delete("key1");
    }

    @Test
    void configLoads8(){

        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("huchengyea@163.com");
        account.setUser("huchengyea");
        account.setPass("SDZSHTMHUKMVSCRA");
        MailUtil.send(account,"3522758312@QQ.com", "测试", "邮件来自晓寻遥测试", false);
    }

    @Test
    void configLoads9(){
        System.out.println(RandomUtil.randomString(5));
    }

    @Test
    void configLoads10(){
//        System.out.println(Validator.isEmail("2414776185"));
        String password = "hcy123";
        System.out.println(Validator.isGeneral(password));
    }

    @Test
    void configLoads11(){
        User user = (User) redisTemplate.opsForValue().get("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IjI0MTQ3NzYxODVAcXEuY29tIiwiZXhwIjoxNjA2MTQ0Mzk2LCJ1c2VySWQiOiIzZDI4MzQ2ODMxNzBhMzY5ZWU0Njk1ZDFiZTA2MzY3NyJ9.uqMdZBUJqgS5O-GCokpeEQmg6fIzgx9yroRaymlwwUY");
        System.out.println(user.getUserName());
        System.out.println(user.getRoleName());
    }

    @Test
    void configLoads12(){
        int a[] = {1,2,3,4,5};
        for (int x : a){
            System.out.println(x);
        }
    }
}
