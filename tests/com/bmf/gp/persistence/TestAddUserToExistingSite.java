package com.bmf.gp.persistence;

import com.bmf.gp.entity.SitesEntity;
import com.bmf.gp.entity.UsersEntity;
import org.junit.Test;

/**
 * Created by felic on 4/10/2016.
 */
public class TestAddUserToExistingSite {

    @Test
    public void testAddUserToExistingSite() throws Exception {

        SitesDao dao = new SitesDao();
        SitesEntity site = dao.getSite(7);

        UsersEntity user1 = new UsersEntity();
        user1.setUserName("Thanos");
        user1.setPassword("password1");
        user1.setUserRole("admin");
        site.addUser(user1);

        UsersEntity user2 = new UsersEntity();
        user2.setUserName("Ronan");
        user2.setPassword("password1");
        user2.setUserRole("admin");
        site.addUser(user2);

        UsersEntity user3 = new UsersEntity();
        user3.setUserName("Magneto");
        user3.setPassword("password1");
        user3.setUserRole("admin");
        site.addUser(user3);

        UsersEntity user4 = new UsersEntity();
        user4.setUserName("GreenGoblin");
        user4.setPassword("password1");
        user4.setUserRole("admin");
        site.addUser(user4);

        dao.updateSite(site);
    }
}