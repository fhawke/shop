package com.lsh.shop.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsh.shop.bean.OrderBean;
import com.lsh.shop.bean.ProductBean;
import com.lsh.shop.bean.ShoppingBean;
import com.lsh.shop.bean.WxResp;
import com.lsh.shop.mapper.CategoryMapper;
import com.lsh.shop.mapper.OrderMapper;
import com.lsh.shop.mapper.ProductMapper;
import com.lsh.shop.mapper.ShoppingMapper;
import com.lsh.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController{
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    //localhost:8080/wx/index,这个地址浏览器可以访问，小程序可以访问
    @GetMapping("/index")
    public void index(Integer cid, HttpServletResponse resp){
        WxResp r= new WxResp();//只是一个普通对象
        r.category = categoryMapper.selectList(null);//查找值赋值给r对象

        if (!r.category.isEmpty()){
            r.product = cid!=null? productMapper.getProduct(cid) :
            productMapper.getProduct(r.category.get(0).getId());
        }
        r.hot = productMapper.getHot();
        outRespJson(r,resp);//可以把一个对象转换成json字符串
        // 输出到小程序或者浏览器中
    }
    @PostMapping("/order")
    public void order(OrderBean bean,HttpServletResponse resp){

        System.out.println(bean.getName());
        System.out.println(bean.getMobile());
        System.out.println(bean.getAddress());
        System.out.println(bean.getJson());
        //json字符串专成对象数组，固定写法，使用谷歌研发的gson jar包
        //new TypeToken<List<ProductBean>>(){}.getType()
        List<ProductBean> product = new Gson().fromJson(bean.getJson(),new TypeToken<List<ProductBean>>(){}.getType());
        WxResp r = new WxResp();
         String alert = NotNullUtil.isBlankAlert(bean);
        if (alert!=null){//有错误
            r.fail(alert);//失败了
        }else if (product.isEmpty()){
            r.fail("购物车啥也没有！");//空数组
        } else {
            bean.setCtime( new Date());//当前时间就是下订单时间
            orderMapper.insert(bean);//把新订单添加到订单表中
            System.out.println(bean.getId());//新增数据的主键id==oid
            for (ProductBean p:product){
                ShoppingBean s = new ShoppingBean(bean.getId(),p.getId(),p.getCount());
                shoppingMapper.insert(s);
            }

        }
        outRespJson(r,resp);
    }
}
