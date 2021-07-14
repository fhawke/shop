package com.lsh.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
@MapperScan("com.lsh.shop.dao")
public class App implements WebMvcConfigurer {
	//当你遇到action错误提示，说明Springboot内置的tomcat端口号被占用，需要输入两条命令
	//netstat -nao
	//查找8080端口号对应的进程id
	//taskkill /t /f /pid 进程id


	/*项目搭建：
	1、pom.xml导入jar包
	2、aoolication.yml配置SpringBoot,配置数据库链接信息
	3、App.java是项目的启动文件，里面包含main函数和后面再说
	4、Bootstrap、Java SpringBoot+Mybatis+MyBatisPlus+FreeMaker
	 */

	/*数据库设计：商城、点餐、买电影票、图书借书系统
	1、所有项目都要有用户！用户表tbl_user	id	username
	2、类别表，帽子，衣服，裤子；社会科学、自然科学：中餐、快餐
	3、商品表，菜品表，电影票类，音乐类
	4、订单表，花了多少钱，买了什么东西，姓名，手机，收货地址
	5、....
	 */


	//static/index.html这个文件是整个项目的入口文件，可以直接输入localhost：8080进入

	//主函数，整个项目的启动入口
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
	}
	
	//上传图片要用
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/shop/**").addResourceLocations("file:D:/create/shop/");
	}
	
}
