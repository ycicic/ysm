FROM java:8
ENV TAG_VERSION=0.0.1
ADD /target/ysm-$TAG_VERSION.jar /home/ysm/ysm-$TAG_VERSION.jar
EXPOSE 11004
LABEL version = "$TAG_VERSION"
ENTRYPOINT java -jar /home/ysm/ysm-$TAG_VERSION.jar
MAINTAINER Mirror ycicic