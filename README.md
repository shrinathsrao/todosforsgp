# todosforsgp

Pre-requisites to run the code : Eclipse (Or any similar IDE), Selenium WebDriver, Java installed on the system, Cucumber Eclipse Plugin (Or any other similar plugin for other IDEs)

How to run the code: 
1) Navigate to "\src\test\java\stepDefinitions" and find the file "TestRunner.java". This is your test runner file which will be used to run the test cases.
2) Once this file is open in your IDE, after you have built the project, Run this file as a "JUnit Test".
3) Wait and observe the code as it gets executed and it tests the application
4) Navigate to "\target\HtmlReports" and find the "report.html" file. Opening this file after a successful execution will give you the HTML report of the execution results.

Feature file ("\src\test\resources\Features") :
This file contains the list of all scenarios that are being tested. Here is the list for your reference - 
1 - Validate that User is able to add items to the to-do list
2 - Validate that User is able to edit the added items to the to-do list
3 - Validate that User is able to delete the added item from the to-do list
4 - Validate that User is able to check the added items as completed on the to-do list
5 - Validate that User is able to clear all the completed items on the to-do list
6 - Validate that User is able to view the appropriate values using filters on landing page of to-do list.
Instead of running testRunner as a JUnit test, you can also run the feature file as a "Cucumber Feature". [Right click on feature file > Run As > Cucumber Feature].
However, for proper HTML Reports, it is recommended to run as a JUnit test.

Possible reasons for build/execution failure:
1) I have used Chrome and Edge drivers ("\src\test\resources\drivers") for cross-browser testing. Please ensure that the drivers for these two are consistent with the browser versions your system is running.
2) If you get this error - Errors running builder 'Integrated External Tool Builder', then try and execute as a JUnit test the second time, it will probably work the second time. If not, then go to Project > Properties > Builders. Here, you can remove the error causing builder.
3) If the Maven dependencies have not been downloaded, please check pom.xml file and verify if there are no errors.


Note - You can also find JSON and JUnit (xml) reports in the "\target" folder.
