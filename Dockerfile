# Sử dụng OpenJDK 17 làm base image
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR của ứng dụng vào container
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Câu lệnh để chạy ứng dụng
CMD ["java", "-jar", "app.jar"]