package com.inet.code.service.impl;

import com.inet.code.entity.Attention;
import com.inet.code.mapper.AttentionMapper;
import com.inet.code.service.AttentionService;
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
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {

    @Resource
    private AttentionMapper attentionMapper;

    /**
     * 通过用户的邮箱和需要关注的邮箱寻找是否关注过该用户
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param focusEmail 关注的邮箱用户
     * @return Attention
     */
    @Override
    public Attention getWhetherAttention(String userEmail, String focusEmail) {
        return attentionMapper.getWhetherAttention(userEmail , focusEmail);
    }

    /**
     * 取消关注操作,删除该记录
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param focusEmail 取消关注的邮箱
     * @return 布尔类型
     */
    @Override
    public Boolean takeOffOperating(String userEmail, String focusEmail) {
        return attentionMapper.takeOffOperating(userEmail,focusEmail);
    }
}
