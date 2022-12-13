FROM openjdk:8
ADD /target/ysm.jar /usr/local/ysm/ysm.jar
EXPOSE 20801
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Shanghai","/usr/local/ysm/ysm.jar"]
MAINTAINER https://github.com/ycicic/ysm