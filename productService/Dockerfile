# Use OpenJDK image
FROM eclipse-temurin:17-jdk-jammy


# Set work directory
WORKDIR /app

# Copy jar from target folder
COPY target/productService-0.0.1-SNAPSHOT.jar app.jar

# Expose app port
EXPOSE 8084

# Run jar
ENTRYPOINT ["java", "-jar", "app.jar"]

