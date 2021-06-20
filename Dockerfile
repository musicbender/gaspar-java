# build
FROM maven:3.8.1-openjdk-16
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["mvn","spring-boot:run"]