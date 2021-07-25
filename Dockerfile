FROM openjdk:11

ENV REPOSITORY=/usr/src
ENV PROJECT_NAME=kiosk-api
ENV JAR_DIR=$REPOSITORY/$PROJECT_NAME/build/libs

COPY . $REPOSITORY/$PROJECT_NAME
WORKDIR $REPOSITORY/$PROJECT_NAME
RUN ./gradlew build
WORKDIR $JAR_DIR

CMD java -jar -Du=kiosk-api -Dspring.profiles.active=develop kiosk-0.0.1-SNAPSHOT.jar 2>&1
#ENTRYPOINT cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/
#WORKDIR $REPOSITORY
#ENV  JAR_NAME=${ls -tr $REPOSITORY/ | grep jar | tail -n 1)}
#CMD java -jar -Du=kiosk-api -Dspring.profiles.active=develop $JAR_NAME 2>&1
#CMD java -jar -Du=kiosk-api -Dspring.profiles.active=develop kiosk-0.0.1-SNAPSHOT.jar 2>&1