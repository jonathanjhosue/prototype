prototype: Example Using Multiple Java EE 7 Technologies Deployed as an EAR
==============================================================================================
Author: Jonathan Sanchez
Level: Intermediate
Technologies: EAR
Target Product: EAP

What is it?
-----------

This project is setup to allow you to create a compliant Java EE 7 application using JSF, CDI 1.0, EJB, JPA and Bean Validation
It uses Maven and git tools.

System requirements
-------------------

All you need to build this project is Java 8 (Java SDK 8) or better, Maven 3.0 or better.

The application this project produces is designed to be run on Wildfly 8. 

 
Configure Maven
---------------

If you have not yet done so, you must [Configure Maven](../README.md#configure-maven) before testing the quickstarts.


Start Wildfly 8 with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   WILDFLY_HOME/bin/standalone.sh
        For Windows: WILDFLY_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../README.md#build-and-deploy-the-quickstarts) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

4. This will deploy `target/prototype.ear` to the running instance of the server.


Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/prototype-web>.

1. Enter a name, email address, and Phone nubmer in the input field and click the _Register_ button.
2. If the data entered is valid, the new member will be registered and added to the _Members_ display list.
3. If the data is not valid, you must fix the validation errors and try again.
4. When the registration is successful, you will see a log message in the server console:

        Registering _the_name_you_entered_


