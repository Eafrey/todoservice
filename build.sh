#!/bin/bash

./gradlew clean bootRepackage

docker build --rm . --tag chensen/todo-service:${VER:?invalid version}
#docker push chensen/todo-service:${VER:?invalid version}

export VER
#docker stack deploy todo -c docker-compose.yml