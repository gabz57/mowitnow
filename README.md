# Mowitnow

Mowitnow is a simple test project using Java 8 and build with Gradle
The gradle configuration uses the Spring boot dependency-management plugin to define dependency versions in a predefined and compatible configuration (pom.xml as Bill Of Material)

## Retrieve the project
`git clone https://github.com/gabz57/mowitnow.git`

## Generate the documentation
Execute `./gradlew javadoc` in the root directory to generate the javadoc for this project

## Build the project
Execute `./gradlew clean build` in the root directory to build the executable jar file

## Run the application
Execute `java -jar build/libs/Mowitnow-0.0.1-SNAPSHOT.jar <sample-file>` to run the java application  
Example : `java -jar build/libs/Mowitnow-0.0.1-SNAPSHOT.jar src/test/resources/demo/demoData`

# Notes
- The Javadoc plugin and comments are here for the demo, actually I don't write comments for javadoc as I try to correctly design method signatures and namings, and peer review can even allow better namings 
- The design pattern *Interpreter* is applied in this codebase, to transform each line content into data for the mowers
- One could reduce code around the main method, I haven't focused on a separation of the CLI and others IO, but one could extract them before adding more behaviours
