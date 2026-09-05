# Stellar Burgers UI tests

UI autotests based on Page Object. The project uses JUnit 4, Selenium, and Allure.

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

The default mode is headless. Use `-Dheadless=false` to see the browser. Generate the report with `mvn allure:serve`.
