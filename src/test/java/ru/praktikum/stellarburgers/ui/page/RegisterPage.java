package ru.praktikum.stellarburgers.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.praktikum.stellarburgers.ui.config.TestConfig;
import ru.praktikum.stellarburgers.ui.model.TestUser;

public class RegisterPage extends BasePage {

    private static final By TITLE = By.xpath("//h2[normalize-space()='Регистрация']");
    private static final By LOGIN_LINK = By.cssSelector("a[href='/login']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open registration page")
    public RegisterPage open() {
        driver.get(TestConfig.BASE_URL + "/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Register user with valid data")
    public void register(TestUser user) {
        fill(user);
        submitWithEnter(inputByLabel("Пароль"));
    }

    @Step("Register user with password: {password}")
    public void registerWithPassword(TestUser user, String password) {
        type(inputByLabel("Имя"), user.getName());
        type(inputByLabel("Email"), user.getEmail());
        type(inputByLabel("Пароль"), password);
        submitWithEnter(inputByLabel("Пароль"));
    }

    @Step("Check registration page is open")
    public boolean isOpen() {
        return driver.getCurrentUrl().equals(TestConfig.BASE_URL + "/register")
                && wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).isDisplayed();
    }

    @Step("Click login link on registration page")
    public void clickLoginLink() {
        click(LOGIN_LINK);
    }

    @Step("Fill registration form")
    private void fill(TestUser user) {
        type(inputByLabel("Имя"), user.getName());
        type(inputByLabel("Email"), user.getEmail());
        type(inputByLabel("Пароль"), user.getPassword());
    }
}
