# CloudMore Test Automation Project
This test automation project supports testing of Web UI in Java-Selenium.

## Supported Platforms
* Windows

## Supported Browsers
* Google Chrome [Windows]

## Table of Contents
1. Configuring test project
2. Running tests
3. Allure report
4. Project structure

## 1. Configuring test project

Run this command from the start to ensure that you don't have anything corrupted

```
$ mvn clean install -U -DskipTests=true
```

## 2. Running Web tests

I used Maven as a tool for building and managing the project. 

To execute tests itself the next Command line should be used:

$ mvn clean test

The report can be found in **root** folder of a project. The folder named **AutomationReport** contains the generated report. 
Just open 'Cloudmore-automation-report.html' file in a browser and navigate. A sample report generated at run time is also pushed along with the code for reference 

**NOTE**: I have tested only in chrome browser.


### 4 Project structure

This is a template for UI-tests automation solution.

It is a maven project:

`'src/main/java/com.cloudmore'` package includes Selenium wrappers, Reporters, and other generic libraries, that may be reused between different classes without any modification.

`'src/main/java/com.cloudmore/exceptions'` has the customized exception handling class

`'src/main/java/com.cloudmore/pageObjects'` has all the classes designed in page object model. Web elements of each page is also within the same class

`'src/main/java/com.cloudmore/utility'` has the web-driver initializer, report generator, screeshot methods, listners, constants etc

`'src/test/java/CloudmoreTestSuite'` has been designed as the test management suite for testing the UI scenarios

`'src/test/java/resources'` has the configuration package with the property file to load url etc.

`'AutomationReport'` The html final report is generated in this package

`'target/output'` Will have the screenshot copied

`'testng.xml'` testng xml that contains the testcases in order for the execution
