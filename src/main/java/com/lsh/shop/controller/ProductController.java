package com.lsh.shop.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.lsh.shop.bean.ProductBean;
import com.lsh.shop.mapper.ProductMapper;
import com.lsh.shop.util.NotNullUtil;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	@Autowired
	private ProductMapper productMapper;
	
	@GetMapping("/list")
	public String list(int cid, HttpServletRequest req) {
		req.setAttribute("retList", productMapper.getProduct(cid));
		req.setAttribute("cid", cid);
		return "/product/list";
	}
	@GetMapping("/add")
	public String add(Integer id, int cid, HttpServletRequest req) {
		req.setAttribute("cid", cid);
		req.setAttribute("bean", id != null ? productMapper.selectById(id) : null);
		return "/product/add";
	}
	@PostMapping("/add")
	public String add(ProductBean bean, HttpServletResponse resp) {
		if (NotNullUtil.isBlank(bean)) {
			// 如果有@NotNull注解的属性，不管是哪个属性，只要发现值是空的，就true
			return jsAlert("请完善商品信息！",
					("/product/add?cid=" + bean.getCid() + 
							(bean.getId()!=null?"&id="+bean.getId():"")),
					resp);
		}
		bean.setPrice(Math.abs(bean.getPrice()));
		bean.setNum(Math.abs(bean.getNum()));
		if (bean.getId() != null) {
			productMapper.updateById(bean);
		} else {
			productMapper.insert(bean);
		}//bean里面有cid，你得检查一下表单有没有提交cid过来
		return "redirect:/product/list?cid=" + bean.getCid();
	}
	
	@GetMapping("/del")
	public String del(int id, int cid) {
		productMapper.deleteById(id);
		return "redirect:/product/list?cid=" + cid;
	}
	//    /product/logo
	@RequestMapping("/logo")
	public void logo(MultipartFile file, HttpServletResponse resp) {
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		try {
			file.transferTo(new File("D:/create/shop/file/" + fileName));
		} catch (Exception e) {
			System.out.println("你检查一下有没有创建好这个路径！");
		}
		// 输出图片路径给页面，少了D:/create，为什么要少了这个东西?
		outRespJson("/shop/file/" + fileName, resp);
	}
	
}
