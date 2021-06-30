#!/bin/bash
sudo docker login -u trundicho
sudo docker build -t first-name-finder:1.0 .
sudo docker tag first-name-finder:1.0 trundicho/first-name-finder:1.0
sudo docker push trundicho/first-name-finder:1.0
