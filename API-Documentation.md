API Documentation
=======
Loquaciously-Worded Login
=======
Version 0.9

###Overview
The Loquaciously-Worded Login Service is a RESTful API. Use your browser's address bar to pass parameters to manage users for your web application. There are four management functions which you can perform: log a user into your application, subscribe a user, update user information, and delete a user.

### Registration
To register your site, please submit an Issue with your site name and email address. A key will be generated and provided to allow your site to access the service.

###Methods
#####Validate (@GET)
* To log a user into your application, you will need to pass in three parameters: your site key, the username, and the user's password. Use the following url to log a user into your application:

>  authenticator/validate/{siteKey}/{username}/{password}

#####Subscribe (@PUT)
* To subscribe (add) a new user, you will need to pass in three parameters: your site key, the username, the user's password. The users role will automatically be set by our process as a "user". Use the following url to subscribe a user:

>  authenticator/subscribe{siteKey}/{username}/{password}/{role}

#####Unsubscribe (@DELETE)
* To unsubscribe (delete) an existing user, you will need to pass in three parameters: your site key, the username and password. Use the following url to unsubscribe a user:

> authenticator/unsubscribe{siteKey}/{username}/{password}

#####Update (@PUT)
* To update a user's information, you will need to pass in three parameters: your site key, the username, the user's new password. Use the following url to update information for a user:

> authenticator/update{siteKey}/{username}/{passwordOld}/{passwordNew}

####Responses
#####Success Response
* When successfully logging in, the service will return the JSONified User Entity
* > { "userId" : <userId> , "userName" : <userName> , "password" : <password> , "userRole" : <role> }

##### Failure Response
* Returned when the method request fails
* > { "error": <message>, "method": <api method>, "description": <failure reason> 
