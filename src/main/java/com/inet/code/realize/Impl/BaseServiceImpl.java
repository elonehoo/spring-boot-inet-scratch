package com.inet.code.realize.Impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.inet.code.entity.User;
import com.inet.code.mapper.UserMapper;
import com.inet.code.realize.BaseService;
import com.inet.code.service.LabelService;
import com.inet.code.service.TypeService;
import com.inet.code.utlis.JwtUtils;
import com.inet.code.utlis.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * BaseServiceImpl
 *
 * @author HCY
 * @since 2020/11/22 下午 09:19
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private TypeService typeService;

    @Resource
    private LabelService labelService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录操作
     * @author HCY
     * @since 2020-11-13
     * @param account 账号
     * @param password 密码
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getLogin(String account, String password, String path) {
        //通过账号或者密码进行登录操作
        User user = userMapper.getLogin(
                account
                , DigestUtil.md5Hex(password));
        //账号或者密码错误
        if (user == null){
            return new Result().result404("您的账号或者密码错误",path);
        }
        //产生 token 的条件
        Map<String, String> map = new HashMap<>(2);
        map.put("userName",user.getUserName());
        map.put("userId",user.getUserUuid());
        //产生 token
        String token = JwtUtils.getToken(map);
        //存入 Redis
        redisTemplate.opsForValue().set(token,user,7, TimeUnit.DAYS);
        //创建返回值
        Map<String, String> results = new HashMap<>(2);
        results.put("info","登录成功");
        results.put("token",token);
        return new Result().result200(results,path);
    }

    /**
     * 退出
     * @author HCY
     * @since 2020-11-14
     * @param token 令牌
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getExit(String token, String path) {
        if (redisTemplate.delete(token)) {
            return new Result().result200("退出成功",path);
        }
        return  new Result().result500("退出失败",path);
    }

    /**
     * 查看所有的类别
     * @author HCY
     * @since 2020/12/4 下午 08:18
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    @Override
    public Result getListType(String path) {
        return new Result().result200(typeService.list(),path);
    }

    /**
     * 查看所有的标签
     * @author HCY
     * @since 2020/12/4 下午 09:29
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    @Override
    public Result getListLabel(String path) {
        return new Result().result200(labelService.list(),path);
    }


}
