package com.pc.shiro.se.test.myrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-25
 * <p>
 * Version: 1.0
 */
public class MyRealm1 implements Realm {

	public String getName() {
		System.out.println("看下作用  getname()");
		return "myrealm1";
	}

	public boolean supports(AuthenticationToken token) {
		System.out.println("看下作用  supports() ");
		return token instanceof UsernamePasswordToken; // 仅支持UsernamePasswordToken类型的Token
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal(); // 得到用户名
		System.out.println("用户名是" + username);
		String password = new String((char[]) token.getCredentials()); // 得到密码
		System.out.println("密码是" + password);
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}
}
