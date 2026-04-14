# QualityHR

Selenium Java automation framework for the OrangeHRM live demo application, built using the Page Object Model (POM), TestNG, WebDriverManager, and ExtentReports.

## Project Objective

This project was created for a Selenium automation hackathon to demonstrate:

- Selenium WebDriver fundamentals
- Page Object Model design
- TestNG annotations, listeners, and DataProviders
- Explicit wait usage
- Screenshot capture on failure
- HTML reporting with ExtentReports
- Clean and reusable framework structure

## Application Under Test

- URL: `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`
- Default username: `Admin`
- Default password: `admin123`

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- ExtentReports
- Jackson Databind

## Framework Features

- Page Object Model for all major modules
- Centralized config with `config.properties`
- Driver initialization using WebDriverManager
- TestNG suite execution through `testng.xml`
- JSON-based `@DataProvider` for login scenarios
- Shared wait utilities with explicit waits
- Screenshot capture on test failure
- Extent HTML reporting

## Modules Covered

- Authentication
  - Valid login
  - Invalid login
  - Logout
  - Empty credentials validation
- Employee Management
  - Add employee
  - Search employee
  - Open employee record
  - Invalid employee search
- Leave Management
  - Open leave module
  - Apply leave
  - Validate leave behavior for past dates
- User Role Management
  - Create user
  - Verify user in admin list
  - Delete user
- Form Validations
  - Empty mandatory fields
  - Invalid date handling
  - Dropdown visibility and selection

## Project Structure

```text
QualityHR/
|-- pom.xml
|-- testng.xml
|-- README.md
|-- reports/
|   `-- extent-report.html
|-- screenshots/
|   `-- *.png
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   `-- com/srm/qualityhr/
|   |   |       |-- App.java
|   |   |       |-- constants/
|   |   |       |   `-- FrameworkConstants.java
|   |   |       |-- pages/
|   |   |       |   |-- AdminPage.java
|   |   |       |   |-- BasePage.java
|   |   |       |   |-- DashboardPage.java
|   |   |       |   |-- LeavePage.java
|   |   |       |   |-- LoginPage.java
|   |   |       |   |-- MyInfoPage.java
|   |   |       |   `-- PIMPage.java
|   |   |       `-- utils/
|   |   |           |-- ConfigReader.java
|   |   |           |-- DriverFactory.java
|   |   |           |-- ScreenshotUtils.java
|   |   |           `-- WaitUtils.java
|   |   `-- resources/
|   |       `-- config.properties
|   `-- test/
|       |-- java/
|       |   `-- com/srm/qualityhr/
|       |       |-- AppTest.java
|       |       |-- base/
|       |       |   `-- BaseTest.java
|       |       |-- dataproviders/
|       |       |   `-- TestDataProvider.java
|       |       |-- listeners/
|       |       |   |-- ExtentManager.java
|       |       |   `-- TestListener.java
|       |       |-- model/
|       |       |   `-- LoginData.java
|       |       |-- tests/
|       |       |   |-- AuthTests.java
|       |       |   |-- EmployeeManagementTests.java
|       |       |   |-- FormValidationTests.java
|       |       |   |-- LeaveManagementTests.java
|       |       |   `-- UserRoleManagementTests.java
|       |       `-- utils/
|       |           `-- TestDataFactory.java
|       `-- resources/
|           `-- testdata/
|               `-- login-data.json
|-- target/
|   `-- generated build output
`-- test-output/
    `-- TestNG generated reports
```

## Configuration

The main execution settings are stored in:

- `src/main/resources/config.properties`

Example values:

```properties
browser=chrome
base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
timeout=20
headless=false
admin.username=Admin
admin.password=admin123
```

## How To Run

### Run the full suite

```bash
mvn test
```

### Compile without running tests

```bash
mvn -DskipTests compile
```

### Run from TestNG suite

The project is already wired to use:

- `testng.xml`

## Test Data

Login data is provided from:

- `src/test/resources/testdata/login-data.json`

This is consumed through:

- `TestDataProvider.java`

## Reporting

After execution:

- Extent report: `reports/extent-report.html`
- Failure screenshots: `screenshots/`
- TestNG reports: `test-output/`

## Important Notes

- `Thread.sleep()` is not used in the framework
- The framework uses explicit waits
- URLs and credentials are externalized in `config.properties`
- Page classes contain locators and actions
- Test classes call only page methods
- Some flows on the live OrangeHRM demo can be unstable because the site is public and dynamic

## Future Improvements

- Retry failed tests using `IRetryAnalyzer`
- Headless execution profile
- Better live-site dropdown handling
- Excel-based or database-based test data
- CI integration with Jenkins or GitHub Actions

## Author

Developed as a Selenium Java Test Automation Framework for hackathon evaluation and demonstration.
