#MAVN Config
FROM maven:3.9.9-eclipse-temurin-23-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn package


FROM eclipse-temurin:23-jre AS builder
WORKDIR application
COPY --from=build /usr/app/target/async-api-async-db-0.0.1-SNAPSHOT.jar application.jar
#Expose Port
EXPOSE 8080
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:23
#Copy layers from build
WORKDIR application

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
# set entrypoint layeres spring boot application
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]