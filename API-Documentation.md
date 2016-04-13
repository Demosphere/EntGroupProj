API Documentation
=======
Loquaciously-Worded Login
=======
Version 1.0

###Overview
The Loquaciously-Worded Login Service is a RESTful API. Use your browser's address bar to pass parameters to manage users for your web application. There are four management functions which you can perform: log a user into your application, subscribe a user, update user information, and delete a user.

You will need a site key to use this service. To obtain a site-key, email us at LoquaciouslyWordedLogin@gmail.com.

###Methods
#####Validate (@GET)
* To log a user into your application, you will need to pass in three parameters: your site key, the username, and the user's password. Use the following url to log a user into your application:

>  authenticator/validate/{site key}/{username}/{password}

#####Subscribe (@PUT)
* To subscribe (add) a new user, you will need to pass in three parameters: your site key, the username, the user's password. The users role will automatically be set by our process as a "user". Use the following url to subscribe a user:

>  authenticator/subscribe{site key}/{username}/{password}/{role}

#####Unsubscribe (@DELETE)
* To unsubscribe (delte) an existing user, you will need to pass in three parameters: your site key, the username and password. Use the following url to unsubscribe a user:

> authenticator/unsubscribe{site key}/{username}/{password}

#####Update (@PUT)
* To update a user's information, you will need to pass in three parameters: your site key, the username, the user's new password. Use the following url to update information for a user:

> authenticator/update{site key}/{username}/{passwordOld}/{passwordNew}
