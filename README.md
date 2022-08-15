# Bitcoin-rate-analyser
Application to fectch current Bitcoin rate for a currenc and minimum and maxminum Bitcoin rate for a currency over a period of time.

User is promoted to enter teh currency code for which the Bitcoin rate is expected.
--------------------------------------------------------------------------------------------------------------------
Sample output:

Welcome to Bitcoin Rate Application.

Please enter the currency code....

inr

Bitcoin rate information for 'inr' currency:
 
 Current rate: 1908541.4269
 
 Minimum rate in the last 120 days : 1509187.4544 
 
 Maximum rate in the last 120 days : 3192475.6358

Do you want to continue? (y/n)

n

Bitcoin Rate Application closed

-------------------------------------------------------------------------------------------------------------------------
Steps to run the application using an IDE:

 1. Import the project in any of the java supported IDEs
 2. Run Main.java
-------------------------------------------------------------------------------------------------------------------------
Useful command to build, test and run Bitcoin rate analyser in linux terminal:

1. ./gradlew test
-------------------------------------------------------------------------------------------------------------------------

Steps to build the application:

1. ./gradlew build
-------------------------------------------------------------------------------------------------------------------------

Steps to build and run the application in local:

Build:

1. ./gradlew build 

2. ./gradlew jar

Run:

3. java -jar build/libs/bitcoin_fetcher-1.0-SNAPSHOT.jar.jar

-------------------------------------------------------------------------------------------------------------------------
Steps to build and run docker image:

Build: 

1. sudo docker build . -t bitcoin-rate-fetcher

Run: 

2. sudo docker run -it bitcoin-rate-fetcher:latest
-------------------------------------------------------------------------------------------------------------------------

Dockerhub link: https://hub.docker.com/r/nitin8717/bitcoin/tags

