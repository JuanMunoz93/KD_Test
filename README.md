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

## Notes
+ To the last optional scenario, the subscription completed url contains other elements appended to the expected one, as the related to the subscription campaing (i.e. "utm_medium=email&utm_campaign=DOI&utm_adgroup=20190513_DOI-Online-10Eur"). Considering this, the test verifies that the url contains the expected value, not that they are the same.
