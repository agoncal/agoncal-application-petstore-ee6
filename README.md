# Application - Petstore Java EE 6

## Purpose of this application

Do you remember the good old Java [Petstore](http://java.sun.com/developer/releases/petstore/) ? It was a sample application created by Sun for its [Java BluePrints](http://www.oracle.com/technetwork/java/javaee/blueprints/index.html) program. The Java Petstore was designed to illustrate how J2EE (and then Java EE) could be used to develop an eCommernce web application. Yes, the point of the Petstore is to sell pets online.

The Petstore had a huge momentum and we started to see plenty of Petstore -like applications flourish. The idea was to build an application with a certain technology. Let's face it, the J2EE version was far too complex using plenty of (today outdated) [design patterns](http://java.sun.com/blueprints/corej2eepatterns/). When I wrote my [Java EE 5 book](http://www.eyrolles.com/Informatique/Livre/java-ee5-9782212120387) I decided to write a Petstore-like application but much simpler. But again, it's out-dated today.

So what you have here is another Petstore-like application but using Java EE 6 and all its goodies (CDI, EJB Lite, REST interface). The goals of this sample is to :

* use Java EE 6 and just Java EE 6 : no external framework or dependency, we even use the `java.util.logging` API ;o)
* make it simple : no complex business algorithm, the point is to bring Java EE 6 technologies together to create an eCommerce website

If you want to use a different web interface, external frameworks, add some sexy alternative JVM language… feel free to fork the code. But we won't do it. We want this EE 6 Petstore to remain simple and to stick to Java EE 6.

## Component diagram



## Compile and package

Being Maven centric, you can compile and package it with `mvn clean compile`, `mvn clean package` or `mvn clean install`. The `package` and `install` phase will automatically trigger the unit tests. Once you have your war file, you can deploy it.

## Deploy the sample

This sample has been tested with GlassFish 3.1.2 in several modes :

* GlassFish runtime : [download GlassFish](http://glassfish.java.net/public/downloadsindex.html), install it, start GlassFish (typing `asadmin start-`domain) and once the application is packaged deploy it (using the admin console or the command line `asadmin deploy target/applicationPetstore.war`)
* GlassFish embedded : use the [GlassFish Maven Plugin](http://maven-glassfish-plugin.java.net/) by running `mvn clean package embedded-glassfish:run` (you might have to increase Perm Gen space with `MAVEN_OPTS` set to `-XX:MaxPermSize=128m`)

## Execute the sample

Once deployed go to the following URL and start buying some pets: [http://localhost:8080/applicationPetstore](http://localhost:8080/applicationPetstore).
You can use these login/passwd :
marc/marc
bill/bill
jobs/jobs

The admin [REST interface](rs/application.wadl) allows you to create/update/remove items in the catalog, orders or customers. You can run the following [curl](http://curl.haxx.se/) commands :

* `curl -X GET http://localhost:8080/applicationPetstore/rs/catalog/categories`
* `curl -X GET http://localhost:8080/applicationPetstore/rs/catalog/products`
* `curl -X GET http://localhost:8080/applicationPetstore/rs/catalog/items`

You can also get a JSON reprensetation as follow :

* `curl -X GET -H "accept: application/json" http://localhost:8080/applicationPetstore/rs/catalog/items`


<div class="footer">
        <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
    </div>