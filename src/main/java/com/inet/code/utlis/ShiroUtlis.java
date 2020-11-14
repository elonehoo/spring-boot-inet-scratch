package com.inet.code.utlis;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
/**
 * ShiroUtlis
 *
 * @author HCY
 * @since 2020/10/27
 */
public class ShiroUtlis {
    /**
     * 加密盐
     */
    private static final String SIGN = "HCY";

    /**
     * 加密算法
     */
    private static final String ALG = "MD5";

    /**
     * 散列次数
     */
    private static final int HASH = 1024;

    /**
     * 创建加密过后的字符串
     * @author HCY
     * @since 2020-10-27
     * @param password
     * @return
     */
    public static String encryption(String password){
        return new SimpleHash(ALG, password, ByteSource.Util.bytes(SIGN), HASH).toString();
    }

}
