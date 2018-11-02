package com.zhu.shiro.test.spring_shiro.dao;

import com.zhu.shiro.test.spring_shiro.entity.Permission;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface PermissionDao {

	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);

}
