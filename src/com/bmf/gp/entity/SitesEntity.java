package com.bmf.gp.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by felic on 4/13/2016.
 */
public class SitesEntity {
    private Integer siteId;
    private String siteKey;
    private Set<UsersEntity> users;

    public SitesEntity() {}

    public SitesEntity(Integer siteId, String siteKey) {
        this.siteId = siteId;
        this.siteKey = siteKey;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    public Set<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UsersEntity> users) {
        this.users = users;
    }

    public void addUser(UsersEntity user) {
        this.users.add(user);
    }
}
