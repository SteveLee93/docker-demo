# Java 21 이미지 사용
FROM eclipse-temurin:21-jdk

# 작업 디렉토리 설정
WORKDIR /app 

# 빌드된 JAR 파일 복사
COPY target/*.jar app.jar

# JVM 튜닝 옵션 추가
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
