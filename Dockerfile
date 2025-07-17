FROM openjdk:21-jdk-slim
WORKDIR /app
COPY src/ /app/src/
COPY Java-OOP-Assessment-STEMLINK.iml /app/
RUN javac src/*.java
CMD ["sh", "-c", "cd /app && java -cp src BookingPlatform"]
