FROM tomcat:9.0.20-jdk11
COPY target/*.war /usr/local/tomcat/webapps/
CMD ["catalina.sh","run"]