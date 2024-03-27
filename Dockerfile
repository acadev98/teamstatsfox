# Dockerfile
FROM maven:3.8.4-openjdk-17-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR construido de la aplicación Spring Boot a /app dentro del contenedor
COPY target/teamstatsfox-1.0.0.war /app/teamstatsfox-1.0.0.war

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8090

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "-Dlogging.config=/app/src/main/resources/logback.xml", "teamstatsfox-1.0.0.war"]