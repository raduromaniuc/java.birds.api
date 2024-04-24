# Start with a Maven and Java 11 base image
FROM maven:3.6.3-openjdk-11

# Set the working directory in the Docker container
WORKDIR /app

# Copy your project's pom.xml and source code into the Docker container
COPY pom.xml .
COPY src /app/src

# Run Maven package to build the application, skipping tests
RUN mvn clean package -DskipTests

# Copy the built jar file to the root for easy execution
RUN cp target/java-birds-api-0.0.1-SNAPSHOT.jar /java-birds-api.jar

# Expose port 8080 for the application
EXPOSE 8080

# Define the command to run your app using java -jar
ENTRYPOINT ["java", "-jar", "/java-birds-api.jar"]