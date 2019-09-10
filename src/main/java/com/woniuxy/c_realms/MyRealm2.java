package com.woniuxy.c_realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm2 extends AuthorizingRealm {
	
	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		System.out.println("认证2");

		if(!"eason".equals(username)) {
			return null;
		}
		
		return new SimpleAuthenticationInfo(username, "456", "myRealm2");
		
	}
	
	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String pricipal = (String) principals.getPrimaryPrincipal();
		
		System.out.println(pricipal + "进行授权");
		
		SimpleAuthorizationInfo ai = new SimpleAuthorizationInfo();
		
		if("eason".equals(pricipal)) {
			ai.addRole("guest");
		}
		
		return ai;
	}

}
