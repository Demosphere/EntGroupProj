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
 * This class will control the method calls of the web service for authenticating site, and user CRUDs.
 *
 * Created by Brendon on 3/31/2016.
 */
@Path("/authenticator")
public class Authenticator {

    private final Logger log = Logger.getLogger(this.getClass());
    private UserToJSON mapper = new UserToJSON();

    private static UsersEntityDaoWithHibernate userRetriever = new UsersEntityDaoWithHibernate();
    private static SitesDao siteRetriever = new SitesDao();

    // A user storage which stores <username, password>
    private static Set<UsersEntity> usersStorage = new HashSet<UsersEntity>();
    private static List<SitesEntity> sitesStorage = new ArrayList<SitesEntity>();

    /**
     * This method will add the specified user to the sites user list.
     *
     * @param siteKey
     * @param username
     * @param password
     * @return JSONified UsersEntity
     */
    @GET
    @Path("/validate/{siteKey}/{username}/{password}")
    @Produces("application/json")
    public String validate(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) throws Exception{
    //public String validate(@PathParam("siteKey") String siteKey, @FormParam("username") String username, @FormParam("password") String password ) {
        log.info("validate - The Call Was Successful");
        if ( isSiteKeyValid(siteRetriever.getAllSites(), siteKey) ) {
            log.info("validate - Site Key is Valid");
            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( siteHasUser(usersStorage, username) ) {
                log.info("validate - Site has User");

                UsersEntity user = userRetriever.getUserByUsername(username);
                if ( user.getUserName().equals(username) && user.getPassword().equals( password ) ) {
                    log.info("validate - VALID USER!!!");

                    return mapper.createJSONFromUser(user);
                }
                return errorJSON("Failed User Login", "validate", "Invalid User Password");
            }
            return errorJSON("Failed User Login", "validate", "Site Does Not Contain User");
        }
        return errorJSON("Failed Site Login", "validate", "Site Key Invalid");
    }

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
    @Produces("application/json")
    public String subscribe(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password )  throws Exception {
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

                return mapper.createJSONFromUser(userNew);
            }
            return errorJSON("Failed To Subscribe User", "subscribe", "Site Already Has Username");
        }
        return errorJSON("Failed Site Login", "subscribe", "Site Key Invalid");
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
    @Produces("application/json")
    public String unsubscribe(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("password") String password ) throws Exception {
        log.info("unsubscribe - The Call Was Successful");
        sitesStorage = siteRetriever.getAllSites();
        if (isSiteKeyValid(sitesStorage, siteKey)) {

            log.info("unsubscribe - Site Key is Valid");
            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if (siteHasUser(usersStorage, username)) {

                UsersEntity user = userRetriever.getUserByUsername(username);
                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    log.info("unsubscribe - VALID USER!!!");

                    UsersEntity newUser = new UsersEntity();
                    newUser.setUserId(userRetriever.getUserByUsername(username).getUserId());
                    boolean newUserID = userRetriever.deleteUser(newUser);

                    return mapper.createJSONFromUser(newUser);
                }
                return errorJSON("Failed To UnSubscribe User", "unsubscribe", "User Password Invalid");
            }
            return errorJSON("Failed To UnSubscribe User", "unsubscribe", "Site Does Not Have Username");
        }
        return errorJSON("Failed Site Login", "unsubscribe", "Site Key Invalid");
    }

    /**
     * This method will remove the specified user to the sites userlist.
     *
     * @param siteKey
     * @param username
     * @param passwordOld
     * @param passwordNew
     * @return TRUE if the user was added
     *         FALSE if there was an error attempting to add the user to the sites userlist.
     */
    @PUT
    @Path( "/updatepassword/{siteKey}/{username}/{passwordOld}/{passwordNew}" )
    @Produces("application/json")
    public String updatepassword(@PathParam("siteKey") String siteKey, @PathParam("username") String username, @PathParam("passwordOld") String passwordOld, @PathParam("passwordNew") String passwordNew ) throws Exception {
        log.info("updatepassword - The Call Was Successful");
        sitesStorage = siteRetriever.getAllSites();
        if ( isSiteKeyValid(sitesStorage, siteKey) ) {

            log.info("updatepassword - Site Key is Valid");
            usersStorage = siteRetriever.getSiteByKey(siteKey).getUsers();
            if ( siteHasUser(usersStorage, username) ) {

                UsersEntity userIterator = userRetriever.getUserByUsername(username);
                if (userIterator.getUserName().equals(username) && userIterator.getPassword().equals(passwordOld)) {

                    UsersEntityDaoWithHibernate dao = new UsersEntityDaoWithHibernate();
                    UsersEntity user = new UsersEntity();

                    user.setSite(siteRetriever.getSiteByKey(siteKey));
                    user.setUserId(userRetriever.getUserByUsername(username).getUserId());
                    user.setUserName(username);
                    user.setPassword(passwordNew);
                    user.setUserRole(userRetriever.getUserByUsername(username).getUserRole());

                    dao.updateUser(user);

                    return mapper.createJSONFromUser(user);
                }
                return errorJSON("Failed User Update", "updatepassword", "User Old Password Invalid, Cannot Update");
            }
            return errorJSON("Failed User Update", "updatepassword", "Site Does Not Have User");
        }
        return errorJSON("Failed Site Login", "updatepassword", "Site Key Invalid");
    }

    /**
     * This method checks is the service key is valid
     *
     * @param siteKey
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */
    public boolean isSiteKeyValid( List<SitesEntity> sites, String siteKey ) {
        for(SitesEntity site : sites) {
            if( site.getSiteKey().equals(siteKey)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if the site has the user in it userlist
     *
     * @param users
     * @param userName
     * @return TRUE if site userlist has username
     * FALSE for otherwise.
     */
    public boolean siteHasUser ( Set<UsersEntity> users, String userName) {
        for(UsersEntity user : users) {
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method generates a JSON string error message.
     *
     * @param errorType
     * @param method
     * @param description
     * @return TRUE if service key matches the pre-generated ones in service key
     * storage. FALSE for otherwise.
     */
    public String errorJSON ( String errorType, String method, String description) {
        String errorJSON = "{ \"error\": \"" + errorType + "\", \"method\": \"" +
                method + "\", \"description\": \"" + description + "\" }";

        return errorJSON;
    }
}

