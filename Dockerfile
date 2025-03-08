FROM openjdk:23-jdk-slim

COPY ./out/production/tictactoe/ /tmp

WORKDIR /tmp

CMD ["javac", "Main.java"]

ENTRYPOINT ["java","Main"]