package com.inet.code.mapper;

import com.inet.code.entity.Attention;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface AttentionMapper extends BaseMapper<Attention> {

    /**
     * 通过用户的邮箱和需要关注的邮箱寻找是否关注过该用户
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param focusEmail 关注者邮箱
     * @return Attention
     */
    Attention getWhetherAttention(String userEmail, String focusEmail);

    /**
     * 取消关注操作,删除该记录
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param focusEmail 取消关注的邮箱
     * @return 布尔类型
     */
    Boolean takeOffOperating(String userEmail, String focusEmail);
}
