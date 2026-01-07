# Use OpenJDK image
FROM openjdk:17-jdk-slim

# Set work directory
WORKDIR /app

# Copy jar from target folder
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# Expose app port
EXPOSE 8082

# Run jar
ENTRYPOINT ["java", "-jar", "app.jar"]


