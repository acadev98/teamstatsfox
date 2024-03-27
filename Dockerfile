# Dockerfile
FROM maven:3.8.4-openjdk-17-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /be-teamstatsfoxs

# Copia el JAR de la aplicación en el contenedor
COPY target/teamstatsfox-1.0.0.war .

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "teamstatsfox-1.0.0.war"]