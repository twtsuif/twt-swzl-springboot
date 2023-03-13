FROM java:8
ADD target/*.jar twt-swzl.jar
EXPOSE 8092
RUN echo "Asia/Shanghai" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
ENTRYPOINT ["java","-jar","/twt-swzl.jar"]