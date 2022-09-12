# apirest-banco
API rest para gestion de cuentas de banco

## Instrucciones de despliegue
1. Para el acceso a la consola de la base de datos H2, luego de ejecutar la aplicacion ingresar a la ruta: http://localhost:8080/h2/
2. En la pantalla de conexion expecificar la JDBC URL: jdbc:h2:file:./bancodb
3. Ejecutar el script de esquema de base de datos, ubicado en la carpeta: ./src/main/resources/BaseDatos.sql

## Documentación de APIs
1. Para la documentacion de las API se puede visualizar la documentacion en la URL: http://localhost:8080/swagger-ui.html

## Pruebas con Postman
1. La version usada es: Postman v9.31.0
2. Se puede importar el archivo con datos de prueba en postman desde el archivo ubicado en la carpeta: ./src/main/resources/BancoRest.postman_collection.json

## Docker Imagen
1. La imagen de docker se puede descargar en: https://hub.docker.com/repository/docker/litoguemi/apirest-banco
