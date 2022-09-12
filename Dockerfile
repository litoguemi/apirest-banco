#Descargamos la imagen oficial de java runtime
FROM openjdk:17-slim as build

#Información de quien mantiene esta imagen
MAINTAINER Miguel Jarama

# Agregamos el jar al contenedor
COPY target/apirest-banco-0.0.1-SNAPSHOT.jar apirest-banco-0.0.1-SNAPSHOT.jar

#ejecuta la aplicación
ENTRYPOINT ["java","-jar","/apirest-banco-0.0.1-SNAPSHOT.jar"]