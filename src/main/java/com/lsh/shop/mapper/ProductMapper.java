package com.lsh.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.shop.bean.ProductBean;
@Mapper
public interface ProductMapper extends BaseMapper<ProductBean> {
	// 根据类别id查询该类别下对应的商品列表
	@Select("select tbl_product.*, tbl_category.category "
			+ "from tbl_product left join tbl_category "
			+ "on tbl_product.cid = tbl_category.id "
			+ "where cid = #{cid}")
	List<ProductBean> getProduct(@Param("cid") int cid);
	
	//@Delete("delete from tbl_product where cid=#{cid}")
	//void deleteByCid(@Param("cid")int cid);

	@Select("select * from tbl_product where hot=1")
	List<ProductBean> getHot();
}
