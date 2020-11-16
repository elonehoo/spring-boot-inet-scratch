package com.inet.code.service.impl;

import com.inet.code.entity.Role;
import com.inet.code.mapper.RoleMapper;
import com.inet.code.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role getRoleName(String roleName) {
        return roleMapper.getRoleName(roleName);
    }
}
