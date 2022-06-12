FROM openjdk:17
WORKDIR /opt/app
COPY target/*.jar customerservice.jar
CMD ["java", "-jar","/opt/app/customerservice.jar"]

#docker run -d -e SPRING_DATASOURCE_URL=jdbc:mariadb://customerservice-mariadb3000-net/customerservice -p 8080:8080 --network customerservice-net --name customerservice customerservice