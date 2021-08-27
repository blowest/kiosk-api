# 💻 Kiosk Project API Server
*Kiosk 프로젝트의 API server 저장소 입니다.*

## Tech Stack
* Spring Boot 2.4.6
* Spring Data JPA 2.4.6
* MySQL 8
* Swagger
* Docker, Docker-Compose
* AWS-EC2, AWS-RDS

## Feature
### 1. Auto Deploy
```shell
#!/bin/bash

REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=kiosk-api
BRANCH_NAME=develop

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git Pull"

git checkout $BRANCH_NAME
git pull

echo "> Start Project Build"

./gradlew build

echo "> Move to step1 directory"

cd $REPOSITORY

echo "> Copy Build File"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> Check pid of currently running application"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME})

echo "PID of currently running application: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> There is no application being executed"
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> Deloy new application"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar -Du=$PROJECT_NAME -Dspring.profiles.active=develop $REPOSITORY/$JAR_NAME 2>&1 &
```


