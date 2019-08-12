FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Ihor Didyk <ihordidyk95@gmail.com>
WORKDIR /home/app
COPY target/remindme-service-0.0.1-SNAPSHOT.jar /home/app/app.jar
EXPOSE 8080
CMD java -jar --illegal-access=deny -Dspring.data.mongodb.uri=mongodb://mongo-dev:27017/reminder_db /home/app/app.jar