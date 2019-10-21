# i2m-brainstorming-app-backend
## TODOS
* Inspiration: Image-Upload
* Tracking Events
* Annotations
* Export


The current backend for the 2018-A Brainstorming App.

This project is based on the following stack:
* [java (openjdk 11)](https://openjdk.java.net/projects/jdk/11/)
* [gradle](https://gradle.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data Rest](https://docs.spring.io/spring-data/rest/docs/current/reference/html/)
* [Docker](https://www.docker.com/)
* [The gitlab registry](https://docs.gitlab.com/ee/user/project/container_registry.html)

## Using a pre-build container from the registry:

    // Login has to be done only once
    docker login registry.gitlab.com
    // Login with your gitlab.com credentials

    docker pull registry.gitlab.com/mackeprm/i2m-brainstorming-app-backend/brainstormingapp:latest
    
    docker run -p 8080:8080 registry.gitlab.com/mackeprm/i2m-brainstorming-app-backend/brainstormingapp

## Build

Building the standalone jar:

    ./gradlew bootJar
   
Creates the jar in /build/libs/brainstormingapp-0.0.2-standalone.jar
    
Running the standalone server.
    
    java -jar brainstormingapp-standalone.jar
    
    curl "http://localhost:8080/api"
    
    
## Build Container

    ./gradlew docker
    
    docker run -p 8080:8080 registry.gitlab.com/mackeprm/i2m-brainstorming-app-backend/brainstormingapp
    
    curl "http://localhost:8080/api"
    
If you want to push to the registry:

    ./gradlew dockerPush

## Authentication
uses [JWT](https://jwt.io/) as authentication mechanism.
### Moderator Authentication Workflow
The moderator can login using the "/auth" endpoint with a POST request and a json payload of username/password
In dev mode, the moderator user is admin:admin
This returns a JWT with the following claims:

    sub	-> <Username>
    iat	-> <Creation Date>
    exp	-> <Expiration Date>

to refresh this token, please use the "/refresh" endpoint

### Ideator Authentication Workflow
To create an anonymous JWT for an ideator, please use the "/create-session" endpoint with a POST request that contains "challengeId" as the POST json payload.

Example (Challenge 2 from the dummy data):

    {"challengeId":"d409963e-43dd-429c-9f8f-f1fb3ebc1425"}
    
This returns a JWT with the following claims:

    "sub" -> "911cb31c-53a1-4678-b602-f59cfdfe14bf"
    "s" -> "c43b1d17-27c3-46c8-80aa-b064f8b23218"
    "c" -> "d409963e-43dd-429c-9f8f-f1fb3ebc1425"
    "t" -> "ids"
    "exp" -> "1550067852"
    "iat" -> "1549463052"

Where
* sub is the anonymous ideator-id
* s is the session id
* c is the challenge id
* t is the type "ids" -> ideationSession

if you use this token, you will get access to ROLE_IDEATOR endpoints

TODO refreshing this token is currently not implemented.

## Uploading Images
You can upload images by POST to the following URL:

    http://localhost:8080/api/images/

Body:
    
    {
    "inspirationId":"ba7794aa-423a-4567-9bb2-c3fd4c5bfb39",
    "data":"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAa4AAAFACAIAAABr2Q8/AAAVM0lEQVR4nO3daWAV5aGH8f87M2dJQggEJKCCiAVRrPuuuF3cl7pytfRarbYuV1Gs3lbrVm2t4oZLb12qtigWUSsubUVFishVqqKCiIoICsoaQsh6ZnvvhzkcAgRIFA0lz+9L5syZ5X0xPM45Z0JMn8mhAKB9c9p6AADQ9kghAJBCACCFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCAAihQAgUggAIoUAIFIIACKFACBSCACSvLYeAP5t2JqqaM5Mk8namipJtq7GO+BY46WSZ6NP3jPdtnY6dS1sH302w3TewuncbZ0HjON49nR5KUWRGmptfY3bfw9T1iWa+bbpUOb07FvYMnx7vPv9/U2maNW+fi7+dJqtrXb6DHC69ojnzYrnfWr9BjXUmS49TIcyp1c/06Fs4/8pYDNFCtFS4TsT/L+MUH2N4siUV5iuW3q7DlRZF0m2trrxxrOVLS66aYyzxVaSbBw33nSuKS4tGj7WZIubPaD9ak7j8AvVUCtrJZku3dM/vtLpM6Bx+IXugL2zl9+bbBYvmpe7c5h30A8y592YH8mUl3J/ukmN9aa0k61aUnTTGP/5R+LZ023lAgW+Ka+wdSu8vQdlzv/td/Hngs0CKURLpQ45OXXIyY23XBBNm5z5+T1u7/6SbHWltTb+4hPFkepr/JG3ZH9+t6R49nQ11NmGumjqRG//o5s9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"
    }

Returns the relative path of the image file:

    /media/inspirations/ba7794aa-423a-4567-9bb2-c3fd4c5bfb39.png