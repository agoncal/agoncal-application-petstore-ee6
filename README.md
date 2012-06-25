# Application - Petstore Java EE 6

* *Author* : [Antonio Goncalves](http://www.antoniogoncalves.org)
* *Level* : Intermediate
* *Technologies* : Java EE 6 (JPA 2.0, CDI 1.0, Bean Validation 1.0, EJB Lite 3.1, JSF 2.0, JAX-RS 1.1)
* *Application Servers* : GlassFish 3.x, JBoss 7.x, TomEE 1.x
* *Summary* : A Petstore-like application using Java EE 6

[Download the code from GitHub](https://github.com/agoncal/agoncal-application-petstore-ee6)

## Purpose of this application

Do you remember the good old Java [Petstore](http://java.sun.com/developer/releases/petstore/) ? It was a sample application created by Sun for its [Java BluePrints](http://www.oracle.com/technetwork/java/javaee/blueprints/index.html) program. The Java Petstore was designed to illustrate how J2EE (and then Java EE) could be used to develop an eCommerce web application. Yes, the point of the Petstore is to sell pets online.

The Petstore had a huge momentum and we started to see plenty of Petstore-like applications flourish. The idea was to build an application with a certain technology. Let's face it, the J2EE version was far too complex using plenty of (today outdated) [design patterns](http://java.sun.com/blueprints/corej2eepatterns/). When I wrote my [Java EE 5 book](http://www.eyrolles.com/Informatique/Livre/java-ee5-9782212120387) I decided to write a Petstore-like application but much simpler. But again, it's out-dated today.

So what you have here is another Petstore-like application but using [Java EE 6](http://jcp.org/en/jsr/detail?id=316) and all its goodies (CDI, EJB Lite, REST interface). It is based on the Petstore I developped for my [Java EE 5 book](http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587) (sorry, it's written in French). I've updated it to be more Java EE 6, ask some friends to contribute, and here we are. The goals of this sample is to :

* use Java EE 6 and just Java EE 6 : no external framework or dependency, we even use the `java.util.logging` API ;o)
* make it simple : no complex business algorithm, the point is to bring Java EE 6 technologies together to create an eCommerce website

If you want to use a different web interface, external frameworks, add some sexy alternative JVM language… feel free to fork the code. But we won't do it. We want this EE 6 Petstore to remain simple and to stick to Java EE 6.

The only external framework that we use is [Arquillian](http://arquillian.org/). Arquillian is used for integration testing. Using Maven profile, you can test services, injection, persistence... against different application servers

## Component diagram

## Glassfish 3.1.2

[Glassfish](http://glassfish.java.net) is the [Java EE 6](http://jcp.org/en/jsr/detail?id=316) reference implementation.

## Compile and package

Being Maven centric, you can compile and package it with `mvn clean compile`, `mvn clean package` or `mvn clean install`. The `package` and `install` phase will automatically trigger the unit tests. Once you have your war file, you can deploy it.

 GlassFish is the default deployment application server, so you don't need to use any Maven profile. But if you wanted you could do `mvn -Pglassifh-embedded clean install`.

### Test with Glassfish embedded

Launching tests under [Glassfish](http://glassfish.java.net/public/downloadsindex.html) is straight forward. You only have to lauch :

    mvn clean install -Pglassfish-embedded

Galssfish will launch during the build and tests will be executed in it.

### Deploy in Glassfish

This sample has been tested with GlassFish 3.1.2 in several modes :

* GlassFish runtime : [download GlassFish](http://glassfish.java.net/public/downloadsindex.html), install it, start GlassFish (typing `asadmin start-`domain) and once the application is packaged deploy it (using the admin console or the command line `asadmin deploy target/applicationPetstore.war`)
* GlassFish embedded : use the [GlassFish Maven Plugin](http://maven-glassfish-plugin.java.net/) by running `mvn clean package embedded-glassfish:run` (you might have to increase Perm Gen space with `MAVEN_OPTS` set to `-XX:MaxPermSize=128m`)

## JBoss 7.1.1

[JBoss 7.x](http://www.jboss.org/jbossas/downloads) implements Java EE 6.

### Test with JBoss managed

To launch tests under JBoss you first should install [JBoss](http://www.jboss.org/jbossas/downloads) and set the environnment variable `JBOSS_HOME` to JBoss installation directory. Once this is done you'll launch the test with this command

    mvn clean install -Pjbossas7-managed

During the build, Arquillian will launch JBoss and execute the tests in it.

### Deploy with JBoss 7.1.1

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

## Third Party Tools & Frameworks

### Arquillian

### JRebel

[JRebel](http://zeroturnaround.com/software/jrebel/) is a JVM-plugin that makes it possible for Java developers to instantly see any code change made to an app without redeploying. It is very useful when you develop in a managed environment like application servers. If you need/want to use JRebel, just follow the [manual](http://zeroturnaround.com/software/jrebel/documentation/). To generate a rebel.xml file just do  `mvn jrebel:generate`

### Sonar

[Sonar](http://www.sonarsource.org/) provides services for continuous inspection of code quality. I use it to have some metrics on the Yaps Petstore (and produce, hopefully, not too ugly code with good code coverage). You can also use it to get some metrics. [Download](http://www.sonarsource.org/downloads/), [install](http://docs.codehaus.org/display/SONAR/Installing+Sonar) and run Sonar with `mvn -Pjacoco,glassfish3 install sonar:sonar` (or `mvn -Pjacoco,jboss7 install sonar:sonar` to run on JBoss 7). For integration testing we need to use [JaCoCo](http://www.eclemma.org/jacoco/). To view the code coverage of integartion tests, make sure you add the "Integration test coverage" widget to your Sonar dashboard (you need admin priviliges).

## Developpers

Some people who worked on this project :

* [Antoine Sabot-Durand](https://twitter.com/#!/antoine_sd)
* [Brice Leporini](https://twitter.com/#!/blep)
* Hervé Le Morvan

## Creative Commons

I use [Silk Icons](http://www.famfamfam.com/lab/icons/silk/) which are in Creative Commons

## Bugs & Workaround

* [If you can't deploy with Intellij IDEA`and JDK 7 on JBoss](http://youtrack.jetbrains.com/issue/IDEA-826) : `-Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.KQueueSelectorProvider


<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>
