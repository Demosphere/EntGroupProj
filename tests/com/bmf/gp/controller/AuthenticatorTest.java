package com.bmf.gp.controller;

import com.bmf.gp.entity.SitesEntity;
import com.bmf.gp.entity.UsersEntity;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by felic on 4/12/2016.
 */
public class AuthenticatorTest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void testIsSiteKeyValid() throws Exception {

        SitesEntity site1 = new SitesEntity();
        site1.setSiteId(22);
        site1.setSiteKey("87d3e948-efd0-40e2-af79-2d532c390d09");

        SitesEntity site2 = new SitesEntity();
        site2.setSiteId(28);
        site2.setSiteKey("6b92ccd4-a59e-4e80-99c2-854ea3dd5a2c");

        List<SitesEntity> sites = new ArrayList<SitesEntity>();
        sites.add(site1);
        sites.add(site2);

        String siteKey = "87d3e948-efd0-40e2-af79-2d532c390d09";

        Authenticator myAuthenticator = new Authenticator();

        Boolean result = myAuthenticator.isSiteKeyValid(sites, siteKey);

        assertEquals(true, result);
    }

    @Test
    public void testSiteHasUser() throws Exception {

        UsersEntity user1 = new UsersEntity();
        user1.setUserName("Gamora");
        user1.setPassword("password1");
        user1.setUserRole("admin");

        UsersEntity user2 = new UsersEntity();
        user2.setUserName("Groot");
        user2.setPassword("password1");
        user2.setUserRole("admin");

        UsersEntity user3 = new UsersEntity();
        user3.setUserName("Rocket Raccoon");
        user3.setPassword("password1");
        user3.setUserRole("admin");

        Set<UsersEntity> users = new HashSet<UsersEntity>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        String username = "Groot";

        Authenticator myAuthenticator = new Authenticator();

        Boolean result = myAuthenticator.siteHasUser(users, username);

        assertEquals(true, result);
    }
}