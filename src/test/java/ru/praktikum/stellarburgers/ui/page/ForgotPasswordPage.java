package ru.praktikum.stellarburgers.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.praktikum.stellarburgers.ui.config.TestConfig;

public class ForgotPasswordPage extends BasePage {

    private static final By TITLE = By.xpath("//h2[normalize-space()='Восстановление пароля']");
    private static final By LOGIN_LINK = By.cssSelector("a[href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public ForgotPasswordPage open() {
        driver.get(TestConfig.BASE_URL + "/forgot-password");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public void clickLoginLink() {
        click(LOGIN_LINK);
    }
}
