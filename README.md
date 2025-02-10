# Maunal

## Prerequisite
Install `openssl` and run:
```shell
openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -outform PEM -pubout -out public.pem
# DEC private key for Jasypt:
openssl pkcs8 -topk8 -inform pem -in private.pem -outform pem -nocrypt -out privatepkcs8.pem
```
## Use
```shell
java -jar -pk D:\public.pem -i preparedToEncString
```