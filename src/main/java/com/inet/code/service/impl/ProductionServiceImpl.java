package com.inet.code.service.impl;

import com.inet.code.entity.Production;
import com.inet.code.mapper.ProductionMapper;
import com.inet.code.service.ProductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 上传的项目的管理模块 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-12-11
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {

}
