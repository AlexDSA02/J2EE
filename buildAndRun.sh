#!/bin/sh
mvn clean package && docker build -t apirest/apirest .
docker rm -f apirest || true && docker run -d -p 9080:9080 -p 9443:9443 --name apirest apirest/apirest