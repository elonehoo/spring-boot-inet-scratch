package com.inet.code.mapper;

import com.inet.code.entity.Cipher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface CipherMapper extends BaseMapper<Cipher> {
    /**
     * 通过邮箱查找用户的密码
     * @author HCY
     * @since 2020-11-16
     * @param userEmail 用户邮箱
     * @return Cipher实体类
     */
    Cipher getByEmail(String userEmail);
}
