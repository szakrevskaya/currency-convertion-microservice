# Use an official Maven image as the base image
FROM maven:17-jdk-slim AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY /target/currency-conversion-service-1.0.jar .
# Set the command to run the application
CMD ["java", "-jar", "currency-conversion-service-1.0.jar"]
CMD ["-jar","currency-conversion-service-1.0.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
ENTRYPOINT ["java"]