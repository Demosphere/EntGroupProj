#EntJavaGroupProject Journal

To document project progress, accomplishments, snags and time spent on this class.

[Time Log](timelog.md)

##Week 8
###3/16/16

Completed:
* Created the repository
* Worked on Project Plan
* This project started out as a daily email service providing a gif(more probably a link) straight to your inbox based on whatever tags you submitted. 

##Week 9
###3/25/16

Completed:
* Created database design
* Discussed roles/tasks for team members
* Remember when we said this started as an email service. HAHA! We threw that out the window. Hard to get motivated on a project you really find no use for yourselves. We opted for a login service. We got the ball rolling with the database design. Talked about what needed doing in a very conceptual manner. None of us had really thought heavily about what the service would functionally be doing.

###3/28/16

Completed:
* Setup database on OpenShift
* Created entities for all database tables (users, user_roles, user_roles pk, sites)
* Create UsersEntityDao, UsersEntityDaoWithHibernate, UsersEntityDaoWithHibernateTest
* Unsuccessfully attempted to connect to database hosted on OpenShift
* We had a hell of a time getting the service to connect to the OpenShift hosted database. We gave up attempting it in the short term. 
###4/5/16

Completed:
* Authorization class took shape. 
* Built out a few other classes that would become an attempt at proper security for the service...Oh boy...

###4/6/16
  * Worked on getting more details of the service working. This is where the project starts to go FUBAR in several ways. First, nobody project worked quite the same way. One could compile and build, another could not. One issue would be fixed and two more revealed. It was the start of what looked a lot like [this](http://thecodinglove.com/post/142634733056/when-i-have-spent-hours-for-the-stupidest-bug-ever) and [this](http://thecodinglove.com/post/140341983198/thinking-about-all-these-bugs).

###4/9/16

Completed:
* Setup associations between entities, and created unit tests to test the associations. This took longer than I expected, as I needed to do a lot of research to figure out how to associate entities. 
* We made some progress thing were starting to take shape. Our unit tests all started passing.

###4/10/2016
* This is where things got super exciting. After struggling with a few of the classes, we realized our "security" management was really messing things up. We couldn't come up with a good way to handle it. So, we looked at the project and Brendon proceded to do [this](http://thecodinglove.com/post/142017464612/dont-worry-let-me-refactor-this). We nuked our security, libraries and tried to get the beast of our service back into scope. Unfortunately, it was the start of a few rough nights that left us looking...[rough](http://thecodinglove.com/post/141555469537/tenth-coffee-of-the-day)

###4/11/16

Completed:
* Attempted to deploy application, both locally and to Openshift. This was very unsuccessful. We decided to scale back our project, and hopefully will get the project deployed tomorrow. 
* Did we realize that we had two days left? Did we ever! After a brief [meltdown](http://thecodinglove.com/post/137751129327/when-it-all-started-with-one-little-bug) Sunday night we had our project back up and running. Really it was something funny with our project and the repository and who knows what else. But after attempting to get th repository back into shape we just started a new project, handled our libraries better and in the end saved ourselves some grief.
* 

###4/12/16

* Completed: ALL THE THINGS! But seriously, we got this badboy running. Our tests were working, our OpenShift was worked. Our demo was working. It was really a miracle. Then again, it did take a little...[extra](http://thecodinglove.com/post/142620759293/one-day-before-deadline)...from each of us. 

###4/13/16
* Demo day! Well, it was quick but it feels like our project has ended quickly and without issue and [here](http://thecodinglove.com/post/139902441109/finishing-10-minutes-before-release) we are. Now we just hope our demo goes smoothly infront of people...Because we all know how that goes...Here is to avoiding any [surprises](http://thecodinglove.com/post/140790640782/when-the-demo-doesnt-go-as-expected)...

