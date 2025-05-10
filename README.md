# Mobile Automation Testing for E-Commerce Application

**Project Type:** ITI Graduation Project  
**Tools & Frameworks:** Appium · Android Driver · Selenium WebDriver · TestNG · Allure Reports · Excel  
**Platform:** Android (Tested via Android Studio Emulator and Real Devices)

## Project Overview

This project focuses on automating test scenarios for a mobile e-commerce application, covering core functionalities such as login, product browsing, and cart management. It was developed and executed as part of the ITI graduation project using industry-standard tools and frameworks.
## Demo
https://drive.google.com/file/d/1qKmsGu2hPvUOJqB5xshkkEdTwCb3F85K/view?usp=drive_link

## Key Contributions

- Conducted end-to-end automation testing using Appium with Android Driver and Selenium WebDriver, all integrated into a TestNG framework.
- Developed and executed automation scripts for critical features including Login, Cart, Product Listing, and full E2E workflows.
- Designed comprehensive test scenarios and maintained reusable test data sets.
- Generated defect and test execution reports using Excel and TestNG logs.
- Captured screenshots on pass/fail/skip events using TestNG Listeners and visualized results with Allure Reports.
- Applied smoke, regression, and functional testing methodologies within Agile sprints to support continuous delivery.

## Folder Structure

project-root/
├── src/
│ └── test/
│ └── java/
│ └── ... (Test Packages by Feature)
├── resources/
│ ├── test-data/
│ └── config/
├── reports/
│ └── allure-results/
├── testng.xml
├── README.md
└── pom.xml

markdown
Copy
Edit

## Reporting

- Test results are tracked and exported via Allure Reports.
- Defects and summaries documented in structured Excel sheets.
- Screenshots auto-captured on test failure or skip for enhanced debugging.

## Execution

To run tests:
```bash
mvn clean test
Then generate Allure Report:

bash
Copy
Edit
allure serve target/allure-results
Notes
Make sure Android Studio and emulators are properly configured.

Update device capabilities in the config file before test execution.

Project is scalable to include iOS and web automation components.
