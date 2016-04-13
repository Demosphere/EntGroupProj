package com.bmf.gp.persistence;

import com.bmf.gp.entity.SitesEntity;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by felic on 4/8/2016.
 */
public class SitesEntityDaoWithHibernateTest {
    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void testGetAllSites() throws Exception {

        SitesDao dao = new SitesDao();
        List<SitesEntity> sites = dao.getAllSites();

        assertTrue("There is the wrong amount in the list", sites.size() > 0);
    }

    @Test
    public void testGetSite() throws Exception {

        SitesDao dao = new SitesDao();
        SitesEntity site = dao.getSite(16);
        assertNotNull("Could not get site", site);
    }

    @Test
    public void testGetSiteIdByKey() throws Exception {

        SitesDao dao = new SitesDao();
        SitesEntity site = dao.getSiteByKey("55c40d6a-d498-41e0-899f-c1f4af71f39e");

        Integer siteId = site.getSiteId();
        log.info("Site Id: " + siteId);

        assertNotNull("Could not get site", site.toString());
    }

    @Test
    public void testUpdateSite() throws Exception {

        SitesDao dao = new SitesDao();
        SitesEntity site = new SitesEntity();
        site.setSiteId(12);
        site.setSiteKey("Updated-Test-Site-Key");

        dao.updateSite(site);
        assertEquals("This is the wrong site", "Updated-Test-Site-Key", site.getSiteKey());
    }

    @Test
    public void testDeleteSite() throws Exception {

        SitesDao dao = new SitesDao();
        SitesEntity site = new SitesEntity();
        int sizeBefore;
        int sizeAfter;
        site.setSiteId(8);

        sizeBefore = dao.getAllSites().size();
        dao.deleteSite(site);
        sizeAfter = dao.getAllSites().size();

        assertTrue("The site was not deleted", sizeBefore > sizeAfter);
    }

    @Test
    public void testAddSite() throws Exception {

        SitesDao dao = new SitesDao();
        int insertSiteId = 0;

        //create site to add
        SitesEntity site = new SitesEntity();
        site.setSiteKey(UUID.randomUUID().toString());

        insertSiteId = dao.addSite(site);

        assertTrue(insertSiteId > 0);
    }
}
