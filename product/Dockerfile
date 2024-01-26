FROM openjdk:17-jdk-alpine
EXPOSE 8080
RUN apk add --no-cache maven
ENV EUREKA=http://localhost:8761/eureka
WORKDIR /product
COPY ./ ./
RUN mvn package
CMD ["java", "-jar","/product/target/product-0.0.1-SNAPSHOT.jar"]