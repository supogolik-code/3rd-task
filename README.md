# Stellar Burgers UI tests

UI autotests based on Page Object. The project uses JUnit 4, Selenium, and Allure.

## Test coverage

The UI suite checks:

- registration with valid data;
- validation for a short password during registration;
- login from the home page, personal account link, registration form, and password recovery form;
- switching constructor sections: buns, sauces, and fillings.

## Run in Google Chrome

```shell
mvn clean test -Dbrowser=chrome
```

## Run in Yandex Browser

```shell
mvn clean test -Dbrowser=yandex
```

If Yandex Browser is installed in a non-standard directory, pass the executable explicitly:

```shell
mvn clean test -Dbrowser=yandex -Dyandex.binary="C:\path\to\browser.exe"
```

If Selenium Manager cannot pick a ChromeDriver version compatible with Yandex Browser, pass the driver explicitly:

```shell
mvn clean test -Dbrowser=yandex -Dyandex.driver="C:\path\to\chromedriver.exe"
```

The default mode is headless. Use `-Dheadless=false` to see the browser.

## Test reports

JUnit/Surefire XML and text reports are generated after each test run:

```text
target/surefire-reports/
```

Allure raw results are generated here:

```text
target/allure-results/
```

Generate a static Allure report:

```shell
mvn allure:report
```

The generated report entry point is:

```text
target/site/allure-maven-plugin/index.html
```

To open the report through a temporary local server, use:

```shell
mvn allure:serve
```

The latest documented test run is available in `docs/test-report.md`. The static Allure report artifact for that run is stored in `reports/allure-report-2026-09-06.zip`.
