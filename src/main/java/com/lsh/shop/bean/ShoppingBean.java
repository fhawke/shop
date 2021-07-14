package com.lsh.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tbl_shopping")
public class ShoppingBean {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer oid;
    private Integer pid;
    private Integer count;

    //生成构造方法，有参构造方法
    public ShoppingBean(Integer oid, Integer pid, Integer count) {
        this.oid = oid;
        this.pid = pid;
        this.count = count;
    }
    public ShoppingBean(){//无参构造方法

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
