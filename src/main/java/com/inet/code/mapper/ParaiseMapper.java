package com.inet.code.mapper;

import com.inet.code.entity.Paraise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface ParaiseMapper extends BaseMapper<Paraise> {

    /**
     * 判断是否已经点赞过了
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的邮箱
     * @return Paraise
     */
    Paraise getJudgeUserLike(String userEmail, String thumbUpEmail);

    /**
     * 取消点赞操作
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的用户邮箱
     * @return Boolean
     */
    Boolean getTrampleLike(String userEmail, String thumbUpEmail);
}
