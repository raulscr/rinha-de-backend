#!/bin/sh

echo 'Building the source image ...'
docker build -t rinha:1.0 .
echo 'Starting database and application'
docker-compose -f docker/docker-compose.yml -p rinha up -d
