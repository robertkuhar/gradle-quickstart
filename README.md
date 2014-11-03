gradle-quickstart
=================

The purpose of gradle-quickstart is to explore how Gradle can be used to build and package a Java webapp
leveraging "the usual suspects" of Java, Jersey, Logback, and Jackson.

Build with ./gradlew (Unix) or gradlew.bat (Windows).

Upon a successful build the deliverable is at build/libs like build/libs/gradle-quickstart-1.0.war.

The gradle Jetty Plug-in is live allowing you to launch a Jetty container from the command line like [ gradle jettyRun ]

This project supports both Eclipse and IntelliJ through their respective plug-ins.  [ gradle eclipse ] will
bootstrap an eclipse project.  [ gradle idea ] will do the same for IntelliJ.
