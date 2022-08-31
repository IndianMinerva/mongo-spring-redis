#!/usr/bin/env bash


export DOCKER_HOST_IP=$(docker-machine ip default)
docker-compose up -d