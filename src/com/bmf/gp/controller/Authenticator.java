package com.bmf.gp.controller;

import com.bmf.gp.entity.SitesEntity;
import com.bmf.gp.entity.UsersEntity;
import com.bmf.gp.mapper.UserToJSON;
import com.bmf.gp.persistence.SitesDao;
import com.bmf.gp.persistence.UsersEntityDaoWithHibernate;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import java.util.*;
/**
 * Created by Brendon on 3/31/2016.
 */
@Path("/authenticator")
public class Authenticator {

    private final Logger log = Logger.getLogger(this.getClass());
    private UserToJSON mapper = new UserToJSON();

    public static final Integer NO_ROWS = -805;
    public static final Integer ROWS_FOUND = 100;
    public static final Integer INVALID = -948;

    private static UsersEntityDaoWithHibernate userRetriever = new UsersEntityDaoWithHibernate();
    private static SitesDao siteRetriever = new SitesDao();

    // A user storage which stores <username, password>
    private static Set<UsersEntity> usersStorage = new HashSet<UsersEntity>();
    private static List<SitesEntity> sitesStorage = new ArrayList<SitesEntity>();

    @GET
    @Path("/get/{siteKey}/{username}/{password}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) {
        // Return some cliched textual content
        log.info("The Call Was Successful");
        return "HELLS YEAH " + siteKey + username +password;
    }

    @GET
    @Path("/validate/{siteKey}/{username}/{password}")
    @Produces("application/json")
    public String validate(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) throws Exception{
    //public String login(@PathParam("siteKey") String siteKey, @FormParam("username") String username, @FormParam("password") String password ) {
        log.info("The Call Was Successful");
        if ( isSiteKeyValid(siteRetriever.getAllSites(), siteKey) ) {
            log.info("Site Key is Valid");
            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( siteHasUser(usersStorage, username) ) {
                log.info("Site has User");
                for (UsersEntity user : usersStorage) {
                    if ( user.getUserName().equals(username) && user.getPassword().equals( password ) ) {
                        log.info("VALID USER!!!");

                        return mapper.createJSONFromUser(user);
                    }
                }
            }
        }
        return "failed login";
    }


/*    @POST
    @Path( "logout/{siteKey}/{username}" )
    public void logout(PathParam("siteKey") String siteKey, @PathParam("username") String username ) throws GeneralSecurityException {
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {
           return;
        }

        throw new GeneralSecurityException( "Invalid service key and authorization token match." );
    }*/

/**
     * This method will add the specified user to the sites user list.
     *
     * @param siteKey
     * @param username
     * @param password
     * @return TRUE if the user was added
     *         FALSE if there was an error attempting to add the user to the sites userlist.
     */

    @PUT
    @Path( "/subscribe/{siteKey}/{username}/{password}" )
    //@Produces( MediaType.APPLICATION_JSON )
    public String subscribe(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) {
        log.info("subscribe - The Call Was Successful");
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {

            log.info("subscribe - Site Key is Valid");
            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( !siteHasUser(usersStorage, username) ) {
                log.info("subscribe - Site does not have user");

                SitesDao dao = new SitesDao();
                SitesEntity site = dao.getSiteByKey(siteKey);

                UsersEntity userNew = new UsersEntity();
                userNew.setUserName(username);
                userNew.setPassword(password);
                userNew.setUserRole("user");
                site.addUser(userNew);

                dao.updateSite(site);

                return "User added";
            }
            return "Site already has user.";
        }
        return "Invalid site.";
    }

/**
     * This method will remove the specified user to the sites userlist.
     *
     * @param siteKey
     * @param username
     * @param password
     * @return TRUE if the user was added
     *         FALSE if there was an error attempting to add the user to the sites userlist.
     */
    @DELETE
    @Path( "/unsubscribe/{siteKey}/{username}/{password}" )
    //@Produces( MediaType.APPLICATION_JSON )
    public String unsubscribe(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) {
        log.info("unsubscribe - The Call Was Successful");
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {

            log.info("unsubscribe - Site Key is Valid");
            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( siteHasUser(usersStorage, username) ) {

                UsersEntity newUser = new UsersEntity();
                newUser.setUserId(userRetriever.getUserByUsername(username).getUserId());
                boolean newUserID = userRetriever.deleteUser(newUser);

                return "User Deleted";
            }
            return "User not found for site";
        }
        return "Site Invalid";
    }

    /**
     * This method checks is the service key is valid
     *
     * @param siteKey
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */
    public boolean isSiteKeyValid( List<SitesEntity> sites, String siteKey ) {
        log.info("Here - 1.0");
        for(SitesEntity site : sites) {
            log.info("Here - 2.0");
            if( site.getSiteKey().equals(siteKey)){
                log.info("Here - 2.1");
                return true;
            }
        }
        log.info("Here - 3.0");
        return false;
    }

    public boolean siteHasUser ( Set<UsersEntity> users, String userName) {
        for(UsersEntity user : users) {
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
}

