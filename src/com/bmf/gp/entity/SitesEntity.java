package com.bmf.gp.entity;

import java.util.Set;

/**
 * Created by felic on 3/28/2016.
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SitesEntity that = (SitesEntity) o;

        if (siteId != null ? !siteId.equals(that.siteId) : that.siteId != null) return false;
        if (siteKey != null ? !siteKey.equals(that.siteKey) : that.siteKey != null) return false;
        return users != null ? users.equals(that.users) : that.users == null;

    }

    public int hashCode() {
        int result = siteId != null ? siteId.hashCode() : 0;
        result = 31 * result + (siteKey != null ? siteKey.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "SitesEntity{" +
                "siteId=" + siteId +
                ", siteKey='" + siteKey + '\'' +
                ", users=" + users +
                '}';
    }
}
