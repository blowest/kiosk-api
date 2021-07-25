cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)