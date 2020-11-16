package com.inet.code.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.inet.code.entity.Portrait;
import com.inet.code.mapper.PortraitMapper;
import com.inet.code.service.PortraitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-11-16
 */
@Service
public class PortraitServiceImpl extends ServiceImpl<PortraitMapper, Portrait> implements PortraitService {

    @Resource
    private PortraitMapper portraitMapper;

    @Override
    public Portrait getRandomImagesUrl() {
        //获取默认头像中的所有条目数 - 1中的随机数
        int randomInt = RandomUtil.randomInt(0, this.count() - 1);
        return portraitMapper.getRandomImages(randomInt);
    }
}
