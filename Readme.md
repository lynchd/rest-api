DIT Distributed Systems REST Tutorial
===========================
The objective of this exercise is to clone, build and deploy a Restful Web API on a JBoss instance. 

Configure your Linux instance on VirtualBox.
For Debian/Ubuntu Install Maven to run from the command line. 

    ```
    sudo apt-get install maven 
    ``` 
Install JBoss v7.0.2.Final from TAR file. 

    ```
    wget http://download.jboss.org/jbossas/7.0/jboss-as-7.0.2.Final/jboss-as-web-7.0.2.Final.tar.gz
    ```
Extract JBoss

    ```
    tar xvfz jboss-as-web-7.0.2.Final.tar.gz
    cd jboss-as-web-7.0.2.Final.tar.gz/bin
    ```
Ensure nothing in running on port 8080 - e.g. Tomcat     

    ```
    fuser 8080/tcp
    ```
If so force kill where PID is gleaned frmo the above fuser

    ```    
    sudo kill -9 PID 
    ```
Start JBoss

    ```
    ./standalone.sh
    ```
Execute a git clone of this repository

    ```
    git clone https://github.com/lynchd/rest-api.git
    ```
Navigate to the resulting rest-api directory

    ```
    cd rest-api
    ```
Prepare the maven project for Eclipse

    ```
    mvn eclipse:eclipse
    ```
Import the project into eclipse

    ```
    Import > Existing Projects Into Workspace 
    ```
Deploy to JBoss

    ```
    mvn install
    ```
Use Postman or CURL to explore the API - e.g to Login

    ```
    david@ronaldo:~$ curl -d'{
        "email" : "stock@testuser.com",
        "password" : "test"
    }' -H "content-type:application/json" http://localhost:8080/users-1.0/login
    >>> Response 
    { "userId":"1","authToken":"fa910e21-7e1e-3726-a9ed-70329b60668d"} 
    ``` 
Replace the *fudged* persistence layer with a database of your choice. That is, rewrite these. 
https://github.com/lynchd/rest-api/blob/master/src/main/java/ie/dit/users/data/repository/LoginRepository.java
https://github.com/lynchd/rest-api/blob/master/src/main/java/ie/dit/users/data/repository/UserRepository.java
    
    
