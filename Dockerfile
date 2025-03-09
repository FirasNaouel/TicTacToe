FROM openjdk:23-jdk-slim

WORKDIR /app

# Copy Java source files into the container
COPY ./src/ /app/

# Compile Java files at build time
RUN javac Main.java

# Set the default command to run the Java program
CMD ["java", "Main"]