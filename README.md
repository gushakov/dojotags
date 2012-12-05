dojotags
========

JSP tag library implementation which uses Dojo toolkit on the client side and Spring MVC on the server side.


Getting Dojo web JAR
--------------------

To create Dojo web JAR downloade the latest Dojo binary from https://dojotoolkit.org/download/ and package
it in a JAR with the following structure:

/META-INF/resources/(dijit, dojo, dojox here)

The path /META-INF/resources/ is referenced in DojoTagsWebMvcConfigurerAdapter.java when declaring a resource
handler for the static resources.

Minify JavaScript
-----------------

It could be done with the help of "granule" library, see http://code.google.com/p/granule/
and https://github.com/JonathanWalsh/Granule for more details. To use the library one only needs to embed the <d:page>
dojotags into granule's <g:compress> tag.


Testing
-------

This project can be tested as a standard webapp, run it via the following Maven target:

mvn tomcat7:run

from the project root directory. This should launch an embedded Tomcat 7 instance. The project entry page is accessible
under localhost, port 8080.

Once the project is more advanced, I'll add the Ant target to get a JAR with all the resources: JavaScript, tag classes,
and tag library descriptor file, which can then be deployed to any webapp as a standard resource.