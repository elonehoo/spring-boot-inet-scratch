package com.inet.code.service.impl;

import com.inet.code.entity.Matter;
import com.inet.code.mapper.MatterMapper;
import com.inet.code.service.MatterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 上传的文件表,一般只存储sb3 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
@Service
public class MatterServiceImpl extends ServiceImpl<MatterMapper, Matter> implements MatterService {

}
