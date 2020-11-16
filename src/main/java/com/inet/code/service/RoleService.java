package com.inet.code.service;

import com.inet.code.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface RoleService extends IService<Role> {
    /**
     * 通过名字进行获取权限名称
     * @author HCY
     * @since 2020-11-16
     * @param roleName 权限名字
     * @return Role实体类
     */
    Role getRoleName(String roleName);
}
