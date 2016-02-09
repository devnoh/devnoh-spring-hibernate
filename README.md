# spring-hibernate
Building a Web App with Spring 4 and Hibernate 4

* Spring 4 + Hibernate 4 + c3p0 (or dbcp) + Oracle
* Annotation-based Configuration
* Testing with JUnit 4 + Mockito

How to build
--------------------

$ mvn clean package

How to run
--------------------

$ mvn jetty:run

or

$ mvn tomcat7:run


Run It on Docker
----------------

$ docker build -t devnoh/spring-hibernate .
$ docker run -it --rm -p 8888:8080 devnoh/spring-hibernate

and access http://192.168.99.100:8888
