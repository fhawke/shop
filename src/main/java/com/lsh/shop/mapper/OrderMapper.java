package com.lsh.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.shop.bean.OrderBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderBean> {

}
