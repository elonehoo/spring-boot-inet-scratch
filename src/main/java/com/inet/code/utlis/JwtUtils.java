package com.inet.code.utlis;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;
/**
 * JwtUtils
 *
 * @author HCY
 * @since 2020/10/27
 */
public class JwtUtils {

    private static final String SING = "INet";

    /**
     * 产生token
     * @author HCY
     * @since 2020-10-09
     */
    public static String getToken(Map<String , String> map){
        //设置过期时间 , 过期时间为7天
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE , 7);

        //创建 Builder
        JWTCreator.Builder builder = JWT.create();
        //遍历map,设置token参数
        map.forEach((key,value)->{
            builder.withClaim(key,value);
        });
        //设置过期时间
        return builder.withExpiresAt(instance.getTime())
                //设置签名
                .sign(Algorithm.HMAC256(SING));
    }

    /**
     * 验证token是否合法
     * @author HCY
     * @since 2020-10-09
     */
    public static Boolean verify(String token){
        try {
            JWT.require(Algorithm.HMAC256(SING))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取token中的某一个数据(String)
     * @author HCY
     * @since 2020-10-11
     * @param token
     * @param search
     * @return
     */
    public static String getString(String token,String search){
        try {
            return JWT.decode(token).getClaim(search).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token中的某一个数据(Integer)
     * @author HCY
     * @since 2020-10-11
     * @param token
     * @param search
     * @return
     */
    public static Integer getInteger(String token,String search){
        try {
            return JWT.decode(token).getClaim(search).asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token中的某一个数据(Double)
     * @author HCY
     * @since 2020-10-11
     * @param token
     * @param search
     * @return
     */
    public static Double getDouble(String token,String search){
        try {
            return JWT.decode(token).getClaim(search).asDouble();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token得信息
     * @author HCY
     * @since 2020-10-09
     */
    public static DecodedJWT getTokenInfo(String token){
        //获取 token 得 DecodedJWT
        return JWT.require(Algorithm.HMAC256(SING))
                .build()
                .verify(token);
    }
}
