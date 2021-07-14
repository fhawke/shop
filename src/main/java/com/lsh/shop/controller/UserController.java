package com.lsh.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsh.shop.bean.UserBean;
import com.lsh.shop.mapper.UserMapper;

// Controller就是SpringMvc
@Controller
public class UserController extends BaseController {
	// controller层调用mapper层
	@Autowired // 创建对象
	private UserMapper userMapper;
	//     localhost:8080/login?username=admin&password=123456
	// 所有的地址，拼前不拼后，前面会有一个/，后面就没有
	// 重定向：redirect:/地址.html
	//   1.重定向浏览器地址会发生改变
	//   2.重定向可以访问到static文件夹里面的页面
	//   3.重定向不可以访问到templates
	//   4.重定向可以找到static里面的文件，也可以找到controller里面的地址
	//   5.重定向传参，xxx?a=avalue&b=bvalue
	
	// 转发：/地址
	//   1.转发浏览器地址栏不会发生改变
	//   2.转发可以访问到templates文件夹里面的页面
	//   3.转发不可以访问到static
	//   4.转发不可以找到controller里面的地址
	//   5.转发传参，req.setAttribute("a", avalue);
	@RequestMapping("/login")
	public String login(String username, String password) {
		UserBean user = userMapper.getUser(username, password);
		return user != null ? ("redirect:/main?uid=" + user.getId()) : 
			("redirect:/index.html?msg=" + getUTF8Param("用户名或密码错误"));
	}
	//    localhost:8080/main
	@RequestMapping("/main")
	public String main(int uid, HttpServletRequest req) {//uid是当前登录用户的用户id
		req.setAttribute("bean", userMapper.selectById(uid));
		return "/main";//这是转发，找到templates下面的main.html
	}
	
}
