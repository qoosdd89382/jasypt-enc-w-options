# Maunal

## Prerequisite
Install `openssl` and run:
```shell
openssl genrsa -out private.pem 2048
# Use private key to generate public key for ENC
openssl rsa -in private.pem -outform PEM -pubout -out public.pem
# DEC private key for Jasypt:
openssl pkcs8 -topk8 -inform pem -in private.pem -outform pem -nocrypt -out privatepkcs8.pem
```

## Build
```shell
mvn clean package  -Dmaven.test.skip=true
```

## Use
```shell
java -jar .\jasypt-0.0.1-SNAPSHOT.jar -pk D:\public.pem -i preparedToEncString
```