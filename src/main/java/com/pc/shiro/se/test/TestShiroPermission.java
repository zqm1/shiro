package com.pc.shiro.se.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiroPermission {
	public static void main(String[] args) {
		TestShiroPermission permission = new TestShiroPermission();
		permission.testHasRole();
	}

	// 通用 登录
	public void login(String configFile, String username, String password) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		subject.login(token);
	}

	public Subject subject() {
		return SecurityUtils.getSubject();
	}

	public void testHasRole() {
		login("classpath:./permission/shiro-role.ini", "zhang", "123");
		// 判断拥有角色：role1
		subject().hasRole("role1");
		System.out.println("判断拥有角色" + subject().hasRole("role1"));
		// 判断拥有角色：role1 and role2
		subject().hasAllRoles(Arrays.asList("role1", "role2"));
		System.out.println(subject().hasAllRoles(Arrays.asList("role1", "role2")));
		// 判断拥有角色：role1 and role2 and !role3
		boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
	}

	public void testCheckRole() {
		login("classpath:shiro-role.ini", "zhang", "123");
		// 断言拥有角色：role1
		subject().checkRole("role1");
		// 断言拥有角色：role1 and role3 失败抛出异常
		subject().checkRoles("role1", "role3");
	}

}
