package com.inet.code.service;

import com.inet.code.entity.Paraise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface ParaiseService extends IService<Paraise> {
    /**
     * 通过用户的邮箱和需要点赞的邮箱判断是否已经点赞过了
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的邮箱
     * @return Paraise 实体类
     */
    Paraise getJudgeUserLike(String userEmail, String thumbUpEmail);

    /**
     * 取消点赞的操作,删除表中的点赞记录
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的用户邮箱
     * @return Boolean
     */
    Boolean getTrampleLike(String userEmail, String thumbUpEmail);
}
