# CatalogSubmission
# * RUN THE ALTERNATIVE SOLUTION INDEX.HTML/newIndex.html IN CASE THE JAVA CODE IS NOT ABLE TO IMPORT THE GSON FILE


This repo consists of the code for the given complex yet simple to solve assignment problem


** README Is Made with the help of ai, its hard to write all this on my own :)**



**Prerequisites**
        Java Development Kit (JDK)
        Maven (for dependency management)
        Setup Instructions
        Install Java and Maven

Ensure that Java and Maven are installed on your system. You can check the installation with:

**bash
Copy code
java -version
mvn -v**
If not installed, download and install from the Java website and the Maven website.

Create a Project Directory

Create and navigate to your project directory:

bash
Copy code
mkdir JsonExample
cd JsonExample
Create pom.xml

Create a file named pom.xml with the necessary Maven dependencies. Use Gson or Jackson based on your preference:

For Gson, include the Gson dependency in pom.xml.
For Jackson, include the Jackson dependency in pom.xml.
Create Directory Structure

Create the required directory structure:

**bash
Copy code
mkdir -p src/main/java
Add Java Code**

Create a file named JsonFileReader.java in src/main/java and add your Java code to handle JSON parsing using the chosen library (Gson or Jackson).

Add JSON File

Place your JSON file (e.g., data.json) in the root of your project directory.

Compile the Code

Open a terminal or Command Prompt in your project directory and run:

bash
Copy code
mvn compile
Run the Program

Execute the program using:

bash
Copy code
mvn exec:java -Dexec.mainClass="JsonFileReader"
=========================================================================================================================
**IN CASE YOU GET ERROR IN MVN **

E.g. 
The term 'mvn' is not recognized as the name of a cmdlet, function, script file, or operable program. Check the spelling of the name, or if a path was included, verify that the path is correct and try again.
At line:1 char:1
+ mvn clean install
+ ~~~
    + CategoryInfo          : ObjectNotFound: (mvn:String) [], CommandNotFoundException
    + FullyQualifiedErrorId : CommandNotFoundException

# IN CASE THE GSON FILE IS NOT IMPORTED YOU CAN TRY OUT THESE COMMANDS ON THE BASH 
Run mvn clean install:

Go to your project directory and run the following command:

bash Commands
mvn clean install
mvn compile
mvn exec:java -Dexec.mainClass="ShamirSecretSharing"



