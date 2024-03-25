# Dockerfile
FROM maven:3.8.4-openjdk-17-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /be-teamstatsfoxs

# Copia el archivo pom.xml y las fuentes a la imagen
COPY pom.xml .
COPY src ./src

# Empaqueta la aplicación utilizando Maven
RUN mvn clean package

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8090

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "target/teamstatsfox-1.0.0.war"]
