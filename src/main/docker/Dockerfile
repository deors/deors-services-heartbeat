#Dockerfile on-off-machine-times

# User Java 8 container as base container
FROM java:8

# Folder used by spring-boot for tomcat working directories
VOLUME /tmp

# Add the application
# ADD target/deors.services.heartbeat-1.0-SNAPSHOT.war app.war
ADD deors.services.heartbeat-1.0-SNAPSHOT.war app.war

# prepare the application to serve static content
RUN sh -c 'touch /app.war'

# system property to improve tomcat start time
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]

# expose port 8080
EXPOSE 8080
