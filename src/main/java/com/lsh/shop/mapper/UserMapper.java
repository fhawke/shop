package com.lsh.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.shop.bean.UserBean;

// 接口继承接口，继承BaseMapper之后，就可以实现简单的增删改查
// 复杂的增删改查，就只能写SQL语句
@Mapper
public interface UserMapper extends BaseMapper<UserBean> {
	// 通过用户名或密码去tbl_user表里面找这个人，如果找到了，则登录成功
	// 如果找不到，则登录失败，这个查询操作，有点复杂需要写SQL语句
	// MyBatis注解用法：
	// @Select注解是用来装select语句
	// @Insert注解		装insert语句
	// @Delete注解		装delete语句
	// @Update注解		装update语句
	// MyBatis XML用法：后面再说，写代码比较繁琐，不打算细讲，你知道一下就行了
	
	// 调用这个抽象函数，就相当于是执行了这条SQL语句
	// 绑定参数，方法形参要绑定到占位符?那个地方
	// #{}是取出@Param的意思
	@Select("select * from tbl_user where username=#{username} and password=#{password}")
	UserBean getUser(@Param("username") String username, @Param("password") String password);
	
}
