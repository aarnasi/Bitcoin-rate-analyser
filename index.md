Bitcoin rate analyser application.

Useful command to build. test and run  Bitcoin rate analyser in linux terminal:

./gradlew test

Steps to build the application:

./gradlew build

Steps to build and run the application in local:

./gradlew build
./gradlew jar

java -jar build/libs/bitcoin_fetcher-1.0-SNAPSHOT.jar.jar 


Steps to build and run docker image:

Build: 
sudo docker build . -t bitcoin-rate-fetcher

Run:
sudo docker run -it bitcoin-rate-fetcher:latest

Dockerhub link:
https://hub.docker.com/r/nitin8717/bitcoin/tags









