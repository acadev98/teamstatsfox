# Dockerfile
FROM maven:3.8.4-openjdk-17-slim

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el código fuente de tu aplicación al contenedor
COPY target/teamstatsfox-1.0.0.war app.war

# Exponer el puerto en el que escucha tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "app.war"]
