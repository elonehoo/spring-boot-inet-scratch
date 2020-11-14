package com.inet.code.realm;
import com.inet.code.entity.JwtToken;
import com.inet.code.entity.User;
import com.inet.code.service.UserService;
import com.inet.code.utlis.JwtUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * InetRealm
 *
 * @author HCY
 * @since 2020/10/27
 */
@Service
public class InetRealm extends AuthorizingRealm  {
    private static final Logger LOGGER  = LogManager.getLogger(InetRealm.class);


    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 一定需要创建,不然出错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 身份验证
     * @author HCY
     * @since 2020-10-27
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        User user= (User) redisTemplate.opsForValue().get(token);
        if (user == null){
            throw new AuthenticationException("用户不存在");
        }
        if ( ! JwtUtils.verify(token)){
            throw new AuthenticationException("token出现错误");
        }
        return new SimpleAuthenticationInfo(token,token,"my_realm");
    }

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取
        User user= (User) redisTemplate.opsForValue().get(principalCollection.toString());
        //创建 simpleAuthorizationInfo 对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加 role 权限
        simpleAuthorizationInfo.addRole(user.getRoleName());
        //添加资源
        return simpleAuthorizationInfo;
    }
}
