# ----------------------------Initial Build Image-------------------------
FROM adoptopenjdk:11-jre-hotspot
# ------------------------------------------------------------------------
# 	Arguments
# ------------------------------------------------------------------------
ARG USER=spring
ARG USER_ID=0
ARG USER_HOME=/app
ARG USER_GROUP=spring
ARG USER_GROUP_ID=0
ARG JAR_FILE=target/*.jar

ARG DEPENDENCY=target/dependency
ARG CERTIFICATES=certificates

# ------------------------------------------------------------------------

VOLUME /tmp

ENV TZ="Africa/Johannesburg"

# create the non-root user and group and set MOTD login message

# set the user and work directory
USER ${USER_ID}

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

## do the below 2 steps for each of your certs

ENTRYPOINT ["java","-cp","app:app/lib/*","za.co.test.Application"]



























