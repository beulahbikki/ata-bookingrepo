# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/Booking-service-0.0.1-SNAPSHOT.jar /app/Booking-service.jar

# Expose the port for the Booking-service
EXPOSE 9127

# Run the application
ENTRYPOINT ["java", "-jar", "/app/Booking-service.jar"]

