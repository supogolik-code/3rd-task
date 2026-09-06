# UI test report

Run date: 2026-09-06 22:10 MSK

Project: Stellar Burgers UI tests

Environment:

- OS: Windows 10 amd64
- Java: OpenJDK 11.0.31 Amazon Corretto
- Maven: Apache Maven 3.9.15
- Browser: Google Chrome 152.0.7977.76
- Test command: `mvn clean test -Dbrowser=chrome`
- Allure command: `mvn allure:report`

## Summary

| Suite | Tests | Passed | Failed | Errors | Skipped | Time |
| --- | ---: | ---: | ---: | ---: | ---: | ---: |
| `ConstructorSectionTest` | 3 | 3 | 0 | 0 | 0 | 31.74 s |
| `LoginTest` | 4 | 4 | 0 | 0 | 0 | 50.42 s |
| `RegistrationTest` | 2 | 2 | 0 | 0 | 0 | 20.62 s |
| **Total** | **9** | **9** | **0** | **0** | **0** | **102.60 s** |

Result: build success, all UI tests passed.

Generated reports:

- Surefire reports: `target/surefire-reports/`
- Allure raw results: `target/allure-results/`
- Static Allure report: `target/site/allure-maven-plugin/index.html`
- Attached static report artifact: `reports/allure-report-2026-09-06.zip`

Note: Selenium printed CDP version warnings for Chrome 152.0.7977.76, but the warnings did not affect the test result.
