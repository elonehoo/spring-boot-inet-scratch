package com.inet.code.mapper;

import com.inet.code.entity.Portrait;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-16
 */
public interface PortraitMapper extends BaseMapper<Portrait> {
    /**
     * 随机产生一个头像网址
     * @author HCY
     * @since 2020-11-16
     * @param randomInt 第几个
     * @return Portrait实体类
     */
    Portrait getRandomImages(int randomInt);
}
