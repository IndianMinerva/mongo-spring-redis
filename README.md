### How to run the application
 execute the command `sh run.sh`
 
### TODO
* Logging
* Swagger
* Integration tests using test-containers
* Security
* Urls to clear the redis cache


#### cURL examples
* To create a URL hash `curl localhost:8080 --data '{"url" : "google.com"}' -H 'Content-Type: application/json'`
* To get the original URL `curl -v localhost:8080/1d5920f4b4`