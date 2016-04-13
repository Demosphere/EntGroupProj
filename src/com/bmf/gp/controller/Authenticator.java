package com.bmf.gp.controller;

import com.bmf.gp.entity.SitesEntity;
import com.bmf.gp.entity.UsersEntity;
import com.bmf.gp.persistence.SitesDao;
import com.bmf.gp.persistence.UsersEntityDaoWithHibernate;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.GeneralSecurityException;
import java.util.*;
/**
 * Created by Brendon on 3/31/2016.
 */
@Path("/authenticator")
public class Authenticator {

    private final Logger log = Logger.getLogger(this.getClass());

    public static final Integer NO_ROWS = -805;
    public static final Integer ROWS_FOUND = 100;
    public static final Integer INVALID = -948;

    private static UsersEntityDaoWithHibernate userRetriever = new UsersEntityDaoWithHibernate();
    private static SitesDao siteRetriever = new SitesDao();

    // A user storage which stores <username, password>
    private static Set<UsersEntity> usersStorage = new HashSet<>();
    private static Set<SitesEntity> sitesStorage = new HashSet<>();

    @GET
    @Path("/get/{siteKey}/{username}/{password}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) {
        // Return some cliched textual content
        return "HELLS YEAH " + siteKey + username +password;
    }

    @GET
    @Path("/login/{siteKey}/{username}/{password}")
    //@Produces("application/xml")
    public String login(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) {
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
                        String authToken = UUID.randomUUID().toString();

                        return authToken;
                    }
                }
            }
        }
        return "failed login";
    }

/*

    @POST
    @Path( "logout" )
    public void logout( String siteKey, String authToken ) throws GeneralSecurityException {
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {
           return;
        }

        throw new GeneralSecurityException( "Invalid service key and authorization token match." );
    }

    */
/**
     * This method will add the specified user to the sites user list.
     *
     * @param siteKey
     * @param username
     * @param password
     * @return TRUE if the user was added
     *         FALSE if there was an error attempting to add the user to the sites userlist.
     *//*

    @POST
    @Path( "subscribe" )
    @Produces( MediaType.APPLICATION_JSON )
    public int subscribeUser( String siteKey, String username, String password ) {
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {

            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( !siteHasUser(usersStorage, username) ) {

                UsersEntity newUser = new UsersEntity();
                newUser.setUserName(username);
                newUser.setPassword(password);
                newUser.setUserRole("User");
                newUser.setSite(siteRetriever.getSiteByKey(siteKey));

                int newUserID = userRetriever.addUser(newUser);

                return ROWS_FOUND;
            }
            return NO_ROWS;
        }
        return INVALID;
    }

    */
/**
     * This method will remove the specified user to the sites userlist.
     *
     * @param siteKey
     * @param username
     * @param password
     * @return TRUE if the user was added
     *         FALSE if there was an error attempting to add the user to the sites userlist.
     *//*

    @POST
    @Path( "unsubscribe" )
    @Produces( MediaType.APPLICATION_JSON )
    public int unSubscribeUser( String siteKey, String username, String password ) {
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {

            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( siteHasUser(usersStorage, username) ) {

                UsersEntity newUser = new UsersEntity();
                newUser.setUserId(retrieveUserID(usersStorage, username));
                newUser.setUserName(username);
                newUser.setPassword(password);
                newUser.setUserRole("User");
                newUser.setSite(siteRetriever.getSiteByKey(siteKey));

                boolean newUserID = userRetriever.deleteUser(newUser);

                return ROWS_FOUND;
            }
            return NO_ROWS;
        }
        return INVALID;
    }
*/

    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param siteKey The service key
     * @param authToken The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     */
    public boolean isAuthTokenValid( String siteKey, String authToken ) {
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {
            return true;
        }
        return false;
    }

    /**
     * This method checks is the service key is valid
     *
     * @param siteKey
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */
    public boolean isSiteKeyValid( Set<SitesEntity> sites, String siteKey ) {
        for(SitesEntity site : sites) {
            if( site.getSiteKey().equals(siteKey)){
                return true;
            }
        }
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

    public Integer retrieveUserID ( Set<UsersEntity> users, String userName) {
        for (UsersEntity user : users) {
            if (user.getUserName().equals(userName)) {
                return user.getUserId();
            }
        }
        return -0;
    }

}

