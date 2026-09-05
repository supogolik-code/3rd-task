package ru.praktikum.stellarburgers.ui.test;

import io.qameta.allure.Allure;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.praktikum.stellarburgers.ui.config.WebDriverFactory;

import java.io.ByteArrayInputStream;

public abstract class BaseUiTest {

    protected WebDriver driver;

    @Rule
    public final TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable exception, Description description) {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure screenshot", "image/png",
                        new ByteArrayInputStream(screenshot), ".png");
            }
        }

        @Override
        protected void finished(Description description) {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    };

    @Before
    public void createDriver() {
        driver = WebDriverFactory.create();
    }

}
