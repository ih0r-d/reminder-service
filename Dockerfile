FROM alpine

MAINTAINER Ihor Didyk <ihordidyk95@gmail.com>

RUN apk add openjdk11\
    maven\
    curl

ARG APP_HOME=/home/app/reminder-service

ARG OUT_FILE=target/remindme-service-0.0.1-SNAPSHOT.jar

ARG PORT=8080

ARG VM_OPTION=--illegal-access=deny

VOLUME ${APP_HOME}

WORKDIR ./ ${APP_HOME}

COPY ./ ${APP_HOME}

EXPOSE ${port}

ADD ${OUT_FILE} reminder-service.jar

CMD java -jar ${VM_OPTION} reminder-service.jar
