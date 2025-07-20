# 1단계: 빌드 이미지 (Maven 사용 예시)
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

WORKDIR /app

# pom.xml 먼저 복사 (의존성 캐싱 목적)
COPY pom.xml .

# 의존성 다운로드
RUN mvn dependency:go-offline

# 소스 복사
COPY src ./src

# 빌드 수행 (테스트는 스킵 가능)
RUN mvn clean package -DskipTests

# 2단계: 실행 이미지
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# 빌드 단계에서 생성한 jar 파일 복사
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
