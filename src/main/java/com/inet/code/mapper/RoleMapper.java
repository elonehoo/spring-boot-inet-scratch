package com.inet.code.mapper;

import com.inet.code.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过权限获取权限的实体类
     * @author HCY
     * @since 2020-11-16
     * @param roleName 权限名字
     * @return Role名字
     */
    Role getRoleName(String roleName);
}
