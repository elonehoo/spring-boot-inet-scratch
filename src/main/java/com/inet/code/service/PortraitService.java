package com.inet.code.service;

import com.inet.code.entity.Portrait;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HCY
 * @since 2020-11-16
 */
public interface PortraitService extends IService<Portrait> {
    /**
     * 产生一个头像
     * @author HCY
     * @since 2020-11-16
     * @return Portrait实体类
     */
    Portrait getRandomImagesUrl();

}
