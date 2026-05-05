# Build
FROM gradle:8.14-jdk21 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon

# Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create cache audio
RUN mkdir -p /tmp/kiku/audio-cache

# Copy the jar
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]