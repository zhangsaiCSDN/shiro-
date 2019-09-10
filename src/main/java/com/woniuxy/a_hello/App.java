package com.woniuxy.a_hello;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class App {
	@Test
	public void test() throws Exception {
		// ����shiro.ini�����ļ����õ�һ������
		Factory<SecurityManager> factory =//
				new IniSecurityManagerFactory("classpath:com/woniuxy/a_hello/shiro.ini");
		// �ӹ����л�ȡһ��SecurityManager���ʵ��
		// SecurityManager��shiro�еĴ��ܹܣ�������ֹ��ܣ���֤����Ȩ�����桢�Ự������
		SecurityManager securityManager = factory.getInstance();
		// ��SecurityManager����Ϊȫ�ֵġ� ������ʵ��web�����У����д��벻��д��������һ����������ɵģ�
		SecurityUtils.setSecurityManager(securityManager);
		
		// ��ȡһ��Subject���󣬸ö������Ҫ���ʵ�ǰϵͳ���Ǹ����壺�ˡ�����һ������
		Subject subject = SecurityUtils.getSubject();
		
		// ��֤�����ʣ�
		UsernamePasswordToken token = new UsernamePasswordToken("bar","456");
		
		// ��ʱshiro��ܻ��Զ������û�������token�е��˻����룬��shiro.ini�����õ��˻���������бȽϡ�
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("�˻�������");
			return;
		} catch (IncorrectCredentialsException e) {
			System.out.println("���벻��ȷ");
			return;
		}
		
		System.out.println("��֤�ɹ���" + subject.getPrincipal());
		
		// ��Ȩ�����ʣ�
		System.out.println("admin: " + subject.hasRole("admin"));
		System.out.println("guest: " + subject.hasRole("guest"));
		
//		subject.checkRole("admin");
		
//		System.out.println("user:save " + subject.isPermitted("user:save"));
//		System.out.println("user:delete " + subject.isPermitted("user:delete"));
//		System.out.println("user:update " + subject.isPermitted("user:update"));
//		System.out.println("user:find " + subject.isPermitted("user:find"));
		
//		subject.checkPermission("user:save");
		
		
		// �˳���¼
		subject.logout();
		
	}
	
	
	@Test
	public void test2() throws Exception {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		IniRealm realm = new IniRealm("classpath:com/woniuxy/a_hello/shiro.ini");
		securityManager.setRealm(realm);
		
		SecurityUtils.setSecurityManager(securityManager);
		UsernamePasswordToken token = new UsernamePasswordToken("bar", "456");
		
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
		} catch (Exception e) {
			System.out.println("��֤ʧ��:" + e);
			return;
		}
		
		System.out.println("��֤ͨ����" + subject.getPrincipal());
		
		System.out.println("admin: " + subject.hasRole("admin"));
		System.out.println("guest: " + subject.hasRole("guest"));
		
		subject.checkPermission("user:save");
		
		subject.logout();
		
		
	}
}
