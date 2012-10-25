dojotags
========

JSP tag library implementation which uses Dojo MVC on the client side and Spring MVC on the server side.


Getting Dojo web JAR
--------------------

In the project's pom.xml there is a dependency to dojotoolkit-1.8.1.jar installed locally. To create this file
I just downloaded the latest Dojo binary from https://dojotoolkit.org/download/ and pakaged it in a JAR with
the following structure:

/META-INF/resources/(dijit, dojo, dojox here)

The path /META-INF/resources/ is referenced in WebConfig.java when declaring a ResourceHandler for the static
resources.

Testing
-------

For now this is a standard webapp to test it run the Maven target:

mvn tomcat7:run

from the project root directory this should launch the embedded Tomcat instance. The project entry page is accesible
under localhost, port 8080.

Once the project is more advanced, I'll add the Ant target to get a JAR with all the resources: JavaScript, tag classes, tld,
which can then be deployed to any webapp as a standard taglib.