package com.lsh.shop.bean;

import java.util.List;

public class WxResp {
    //响应码，默认为1，正确，错误为0
    public int code=1;
    //错误信息
    public String  msg="";
    //失败函数
    public void fail(String msg){
        this.code=0;
        this.msg=msg;
    }
    public List<CategoryBean> category;//类别数组

    public List<ProductBean> product;//商品数组

    public List<ProductBean> hot;//热卖数组
}
