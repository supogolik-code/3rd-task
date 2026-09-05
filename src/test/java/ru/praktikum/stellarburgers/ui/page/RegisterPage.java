package ru.praktikum.stellarburgers.ui.page;

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

    public RegisterPage open() {
        driver.get(TestConfig.BASE_URL + "/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public void register(TestUser user) {
        fill(user);
        submitWithEnter(inputByLabel("Пароль"));
    }

    public void registerWithPassword(TestUser user, String password) {
        type(inputByLabel("Имя"), user.getName());
        type(inputByLabel("Email"), user.getEmail());
        type(inputByLabel("Пароль"), password);
        submitWithEnter(inputByLabel("Пароль"));
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().equals(TestConfig.BASE_URL + "/register")
                && wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).isDisplayed();
    }

    public void clickLoginLink() {
        click(LOGIN_LINK);
    }

    private void fill(TestUser user) {
        type(inputByLabel("Имя"), user.getName());
        type(inputByLabel("Email"), user.getEmail());
        type(inputByLabel("Пароль"), user.getPassword());
    }
}
