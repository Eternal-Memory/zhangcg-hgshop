package com.zcg.hgshop;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootStart {
	public static void main(String[] args) throws IOException {
		System.out.println("豪哥商城项目开始启动");
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext-dubbo-provider.xml",
				"classpath:applicationContext-dao.xml","classpath:applicationContext-kafka.xml");
		context.start();
		System.out.println("豪哥商城项目启动完成");
		
		System.in.read();
		
	}
}
