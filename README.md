# Krieger Digital - SET HOMEWORK
This project contains the solution implemented by me -Juan Muñoz- for the Krieger digital set homework.

## Overview
+ Ide used: IntelliJ Ultimate 2020.3.3
+ Language: Java (11)
+ Dependencies manager: Gradle (v6.8.3)
+ Testing FW: TestNG (7.4.0)

Probably the MVP for this automation can be achieved with a simpler solution, but the intention was also built a complete and scalable solution/framework.

## Project Layers
This project implements BDD tests with 4 main layers. Its main responsibility are:

1 Features: Contains the tests wrote using gherkin.

2 Steps: Connects the gherkin tests with its java implementation.

3 Definitions: Implements the algoritms to execute the steps.

4 Pages: Allows interaction with page elements exposing business-oriented actions.

It's common to find the 2nd and 3rd as only one layer, but personally I prefer this project implementation because allow reusing a lot of code oriented to the domain and simplify the maintenance. 

## Important plugins installed on the IDE
+ Gradle - bundled 203.7717.56
+ Gherkin - bundled 203.7717.56
+ Cucumber for java - bundled 203.7717.56

## Optional1
+ To assure the quality of the functionality, is important to add some non-happy path scenarios like the following:
  + Verify the behavior when the 'Abseden' button is pressed when the email input is empty.
  + Verify the behavior when an email with a wrong format is used.
    
  Also, can be useful verify the newsletter subscription functionality with a little screen resolution to verify the correct behavior in the responsive page mode.

  I consider that the newsletter subscription is an important functionality to the company publicity and information diffusion, but it's necessary to confirm first its importance with the product owner to define the priority and impact of the automation of these tests. It's also necessary ask the development team if with their unit tests already have coverage of this functionality to avoid unnecessary redundancies.

## Notes
+ To the last optional scenario, the subscription completed url contains other elements appended to the expected one, as the related to the subscription campaing (i.e. "utm_medium=email&utm_campaign=DOI&utm_adgroup=20190513_DOI-Online-10Eur"). Considering this, the test verifies that the url contains the expected value, not that they are the same.
+ The tests can be executed from the feature files, from the test runners or with a gradle command line (I recommend using the gradle wrapper if the execution is with a gradle command line).
+ The execution report and log are generated in the Reports folder, it's in the project root.
+ The chrome driver is inside the project and its version is for the latest chrome version (90.0.4430.212), this project doesn't use and automatic driver downloader/manager. 