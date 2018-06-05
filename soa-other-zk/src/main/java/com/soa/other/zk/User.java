package com.soa.other.zk;

import java.io.Serializable;

/**
 * Created by pengyunlong on 2018/6/4.
 */
public class User implements Serializable{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
