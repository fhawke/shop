package com.lsh.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// 一个Bean对应一张表
// UserBean对应tbl_user表
// 表里面有什么Bean里面就有什么
// mybatisplus要求我们这么做，会得到简单的增删改查不需要写sql语句
@TableName("tbl_user")
public class UserBean {
	// id是主键
	@TableId(value="id",type=IdType.AUTO)//auto_increment自动增长
	private Integer id;//Bean里面的int尽量使用Integer来替代
	// int、Integer区别？
	// int不能接受null类型，Integer接受null类型
	private String username;
	private String password;
	// 生成set get方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
