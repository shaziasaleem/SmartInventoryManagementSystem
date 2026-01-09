# Use OpenJDK image
FROM eclipse-temurin:17-jdk-jammy


# Set work directory
WORKDIR /app

# Copy jar from target folder
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# Expose app port
EXPOSE 8082

# Run jar
ENTRYPOINT ["java", "-jar", "app.jar"]


