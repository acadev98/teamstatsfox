# Dockerfile
FROM maven:3.8.4-openjdk-17-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /be-teamstatsfoxs

# Empaqueta la aplicación utilizando Maven
RUN mvn clean package

# Copia el JAR de la aplicación en el contenedor
COPY target/teamstatsfox-1.0.0.jar /be-teamstatsfoxs

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "teamstatsfox-1.0.0.war"]