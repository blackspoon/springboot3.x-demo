package com.clx.springboot30.application.model;

import java.io.Serializable;

public class SysUser implements Serializable {

    private static final long serialVersionUID = -4415438719697624729L;

    private String id;

    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
