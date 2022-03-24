# Imagen base 
FROM openjdk:8-jdk-alpine

# Author
LABEL MAINTAINER = "jlunah.01@gmail.com"

#Configurar Zona Horaria
RUN apk add --no-cache tzdata
ENV TZ=America/Lima
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Puertos expuestos
EXPOSE 8080
EXPOSE 8000

# -> EXPOSE 8080 Puerto donde se despliega mi APP
# -> EXPOSE 8000 Para la JVM
# Argumentos
#ARG DEPLOY_PROFILE=dev
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT java $JAVA_OPTS \
			-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n \
			-Djava.security.egd=file:/dev/./urandom \
            -jar /app.jar