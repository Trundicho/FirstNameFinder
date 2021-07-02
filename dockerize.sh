#!/bin/bash
image=first-name-finder
version=1.0
network=mynetwork
hostport=8083
sudo docker login -u trundicho
sudo docker build -t $image:$version .
sudo docker tag $image:$version trundicho/$image:$version
sudo docker push trundicho/$image:$version
sudo docker network create $network
sudo docker container stop $image
sudo docker container rm $image
sudo docker run --network $network --name $image -p $hostport:8080 $image:$version
