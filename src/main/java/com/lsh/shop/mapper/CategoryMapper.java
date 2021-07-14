package com.lsh.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.shop.bean.CategoryBean;

@Mapper
public interface CategoryMapper extends BaseMapper<CategoryBean> {

	@Select("select * from tbl_category where category like #{category}")
	List<CategoryBean> getLike(@Param("category") String category);
	
}
