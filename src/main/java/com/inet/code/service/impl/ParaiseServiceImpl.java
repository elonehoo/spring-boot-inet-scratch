package com.inet.code.service.impl;

import com.inet.code.entity.Paraise;
import com.inet.code.mapper.ParaiseMapper;
import com.inet.code.service.ParaiseService;
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
public class ParaiseServiceImpl extends ServiceImpl<ParaiseMapper, Paraise> implements ParaiseService {

    @Resource
    private ParaiseMapper paraiseMapper;

    /**
     * 判断是否已经点赞过了
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的邮箱
     * @return Paraise
     */
    @Override
    public Paraise getJudgeUserLike(String userEmail, String thumbUpEmail) {
        return paraiseMapper.getJudgeUserLike(userEmail,thumbUpEmail);
    }

    /**
     * 取消点赞的操作,删除表中的点赞记录
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的用户邮箱
     * @return Boolean
     */
    @Override
    public Boolean getTrampleLike(String userEmail, String thumbUpEmail) {
        return paraiseMapper.getTrampleLike(userEmail,thumbUpEmail);
    }
}
