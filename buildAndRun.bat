@echo off
call mvn clean package
call docker build -t apirest/apirest .
call docker rm -f apirest
call docker run -d -p 9080:9080 -p 9443:9443 --name apirest apirest/apirest