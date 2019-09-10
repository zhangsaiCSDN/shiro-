package com.woniuxy.c_realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm3 extends AuthorizingRealm {
	
	// ��֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		System.out.println("��֤3");

		if(!"ekin".equals(username)) {
			return null;
		}
		
		return new SimpleAuthenticationInfo(username, "789", "myRealm3");
		
	}
	
	// ��Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String pricipal = (String) principals.getPrimaryPrincipal();
		
		System.out.println(pricipal + "������Ȩ");
		
		SimpleAuthorizationInfo ai = new SimpleAuthorizationInfo();
		
		if("andy".equals(pricipal)) {
			ai.addRole("admin");
			ai.addRole("guest");
		}
		
		return ai;
	}

}
