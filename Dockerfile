# Define la imagen base
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo en la aplicación
WORKDIR /app

# Copia el JAR de la aplicación en el contenedor
COPY target/teamstatsfox-1.0.0.war.jar /app

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "teamstatsfox-1.0.0.war.jar"]