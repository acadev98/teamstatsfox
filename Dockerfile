# Dockerfile
FROM maven:3.8.4-openjdk-17-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación Spring al contenedor
COPY target/teamstatsfox-1.0.0.war app.war

# Comando para ejecutar la aplicación Spring al iniciar el contenedor
CMD ["java", "-jar", "app.war"]