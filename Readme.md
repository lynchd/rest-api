DIT Distributed Systems REST Tutorial
===========================
The objective of this exercise is to clone, build and deploy a Restful Web API on a JBoss instance. 

* Configure your Linux instance on VirtualBox.
* Install Maven to run from the command line. On Debian/Ubuntu execute.
    sudo apt-get install maven  
* Install JBoss v7.0.2.Final from TAR file. 
    wget http://download.jboss.org/jbossas/7.0/jboss-as-7.0.2.Final/jboss-as-web-7.0.2.Final.tar.gz
* Extract JBoss
    tar xvfz jboss-as-web-7.0.2.Final.tar.gz
    cd jboss-as-web-7.0.2.Final.tar.gz/bin
* Ensure nothing in running on port 8080 - e.g. Tomcat     
    fuser 8080/tcp
* If so force kill where PID is gleaned frmo the above fuser
    sudo kill -9 PID 
* Start JBoss
    ./standalone.sh
* Execute a git clone of this repository
    git clone
* Navigate to the resulting rest-api directory
    cd rest-api
* Prepare the maven project for Eclipse
    mvn eclipse:eclipse
* Import the project into eclipse
    Import > Existing Projects Into Workspace 
* Deploy to JBoss
    mvn install
* Use Postman or CURL to explore the API - e.g to Login
    curl 
* Replace the *fudged* persistence layer with a database of your choice.  

    
    
