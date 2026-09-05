package ru.praktikum.stellarburgers.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.praktikum.stellarburgers.ui.model.TestUser;

public class LoginPage extends BasePage {

    private static final By TITLE = By.xpath("//h2[normalize-space()='Вход']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[normalize-space()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage waitUntilOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public void login(TestUser user) {
        type(inputByLabel("Email"), user.getEmail());
        type(inputByLabel("Пароль"), user.getPassword());
        click(SUBMIT_BUTTON);
        wait.until(ExpectedConditions.urlToBe(ru.praktikum.stellarburgers.ui.config.TestConfig.BASE_URL + "/"));
    }
}
