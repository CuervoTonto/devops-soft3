# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

COPY gradle gradle
COPY gradlew ./gradlew
COPY settings.gradle settings.gradle

COPY dominio/src/ dominio/src/
COPY aplicacion/src/ aplicacion/src/
COPY infraestructura/src/ infraestructura/src/
COPY arranque/src/ arranque/src/

COPY dominio/build.gradle dominio/
COPY aplicacion/build.gradle aplicacion/
COPY infraestructura/build.gradle infraestructura/
COPY arranque/build.gradle arranque/

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

RUN ./gradlew bootJar --no-daemon -x test

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY --from=builder /app/arranque/build/libs/*.jar app.jar

RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]