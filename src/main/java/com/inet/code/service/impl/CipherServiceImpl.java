package com.inet.code.service.impl;

import com.inet.code.entity.Cipher;
import com.inet.code.mapper.CipherMapper;
import com.inet.code.service.CipherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
@Service
public class CipherServiceImpl extends ServiceImpl<CipherMapper, Cipher> implements CipherService {
    @Resource
    private CipherMapper cipherMapper;
    /**
     * 通过邮箱寻找用户的密码
     * @author HCY
     * @since 2020-11-16
     * @param userEmail 用户邮箱
     * @return Cipher实体类
     */
    @Override
    public Cipher getByEmail(String userEmail) {
        return cipherMapper.getByEmail(userEmail);
    }
}
