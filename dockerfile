# Use Tomcat 9 with Java 8 as the base image
FROM tomcat:9-jdk8

# Copy the WAR file to the webapps directory in the container
COPY ./target/ROOT.war /usr/local/tomcat/webapps/ROOT.war


# Expose port 8080 for the Tomcat server
EXPOSE 8080

# Start the Tomcat server
CMD ["catalina.sh", "run"]