package com.bmf.gp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by felic on 3/28/2016.
 */

public class UsersEntity {

    private Integer userId;
    private String userName;
    private String password;
    private String userRole;
    private SitesEntity site;

    public UsersEntity() {}

    public UsersEntity(Integer userId, String userName, String password, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @JsonIgnore
    public SitesEntity getSite() {
        return site;
    }

    public void setSite(SitesEntity site) {
        this.site = site;
    }
}
