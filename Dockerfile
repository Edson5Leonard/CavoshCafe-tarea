# 1. Usar Java 21 como versión base
FROM eclipse-temurin:21-jdk-alpine

# 2. Carpeta donde se ejecutará la app dentro del contenedor
WORKDIR /app

# 3. Copiar el ejecutable .jar que se va a generar
COPY target/*.jar app.jar

# 4. Puerto que expondrá el contenedor
EXPOSE 8080

# 5. Comando para iniciar tu API
ENTRYPOINT ["java", "-jar", "app.jar"]