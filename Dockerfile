# syntax=docker/dockerfile:1
FROM centos:centos7
FROM maven:3.8.1-openjdk-17-slim
COPY . /opt
WORKDIR /opt
COPY .mvn/ .mvn
COPY mvnw .mvnw
COPY src ./src
CMD ./mvnw spring-boot:run