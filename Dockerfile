FROM openjdk:21-jdk-slim
WORKDIR /app
COPY src/ /app/src/
COPY Java-OOP-Assessment-STEMLINK.iml /app/
RUN javac src/*.java
CMD ["java", "-cp", "src", "BookingPlatform"]
