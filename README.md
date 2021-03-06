# devnoh-spring-hibernate
Building a Web Application with Spring 4 and Hibernate 4

* Spring 4 + Hibernate 4 + c3p0 (or dbcp) + Oracle
* Annotation-based Configuration
* Testing with JUnit 4 + Mockito

## Build Status

[![Build Status](https://travis-ci.org/devnoh/devnoh-spring-hibernate.svg?branch=master)](https://travis-ci.org/devnoh/devnoh-spring-hibernate)

## Dependencies

* Servlet 3.0 + JSP 2.2 + JSTL 1.2
* Spring Framework 4
* Hibernate 4
* 3p0 (or dbcp) + Oracle (H2)
* JUnit 4 + DBUnit + Mockito
* Log4J 2

## How to build

**Maven:**
```
$ mvn clean package
```

**Gradle:**
```
$ gradle build
```

## How to run

**Maven:**
```
$ mvn jetty:run
$ mvn tomcat7:run
```

**Gradle:**
```
$ gradle jettyRun
$ gradle tomcatRun
```

## Run It on Docker

```
$ docker build -t devnoh/spring-hibernate .
$ docker run -it --rm -p 8888:8080 devnoh/spring-hibernate
```
and access http://192.168.99.100:8888
