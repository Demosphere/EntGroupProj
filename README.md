# EntJavaGroupProject

###Problem Statement
Our web service will handle authentication/login for a website. It will have all basic functions for a user logging into a web application and for web administrators managing users (add/remove users).

###Project Technologies/Techniques
* Database (MySQL and Hibernate)
	- Store users
	- Store sites
* Logging
  * Configurable logging using Log4J. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting. 
* Site and database hosted on OpenShift
* Hibernate
* Unit Testing
  * JUnit tests to achieve 70% code coverage


###Design

Scope:
* user level
  * attempt login
    * returns all varaibles that are required for the app for it to figure out if they can access pages
    * role, pass/fail login

* DB design:
  * site
    * site_#
    * site_key
  * users
    * user_id
    * site_#
    * user_name
    * password
    * user_role

###[Project Plan](projectplan.md)


###[Development Journal](journal.md)

###[Demo](http://tomcatserver2-demosphere.rhcloud.com/EntGroupProj_Web/description.html)
