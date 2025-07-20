# Java 21 기반 이미지
FROM eclipse-temurin:21-jdk-alpine

# 작업 디렉토리 생성
WORKDIR /app

# target 폴더 안의 jar 파일 복사
COPY target/*.jar app.jar

# Spring Boot 기본 포트
EXPOSE 8080

# 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]
