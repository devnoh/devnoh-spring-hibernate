FROM tomcat:8.0-jre8

MAINTAINER "Sehwan Noh <devnoh@gmail.com>"

RUN rm -Rf /usr/local/tomcat/webapps/*
COPY ./target/devnoh-spring-hibernate /usr/local/tomcat/webapps/ROOT