package com.lsh.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.shop.bean.ShoppingBean;
import org.apache.ibatis.annotations.Mapper;

//简单的增删改查就不用写sql语句
@Mapper
public interface ShoppingMapper extends BaseMapper<ShoppingBean> {

}
