package com.zcg.hgshop;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserStart {
	public static void main(String[] args) throws IOException {
		System.out.println("用户部分开始启动");
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext-dubbo-provider.xml","classpath:applicationContext-dao.xml");
		context.start();
		System.out.println("用户部分启动完成");
		
		System.in.read();
		
	}
}
