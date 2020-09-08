# EZBOB Home Assignment

## The system contains 2 microservices. One for processing the request and the second one to log it.

Author: Tamir Mayblat, tamirmayb@gmail.com

## Content

## Prerequisites :

* A working rabbitmq server with factory settings (localhost:5672, username: guest, password: guest)

## How to use :

* Start rabbitmq server. 
* Start LoggerApp (8081) and ShuffleApp (8080) microservices.
* Send post request to the shuffle ms - http://localhost:8080/ezbob/shuffle. 
* Set request parameter to be n (the number which the shuffled array os based on) 

* Example :  curl -d "input=5" -X POST http://localhost:8080/ezbob/shuffle
or use any rest client to send the requests.

### Thanks