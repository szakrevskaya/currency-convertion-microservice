# Use an official Maven image as the base image
FROM maven:latest AS builder
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests

# Next stage where we use openjdk:17-oracle container
FROM  openjdk:17-oracle
#Set up working dir for it
WORKDIR /app
## Copy the built JAR file from the previous stage to the container. Jar file was build in app directory.
COPY --from=builder /app/target/currency-conversion-service-1.0.jar .
## Set the command to run the application
CMD ["java", "-jar", "currency-conversion-service-1.0.jar"]
CMD ["-jar","currency-conversion-service-1.0.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
ENTRYPOINT ["java"]