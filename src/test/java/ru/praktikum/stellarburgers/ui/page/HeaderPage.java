package ru.praktikum.stellarburgers.ui.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

    private static final By ACCOUNT_LINK = By.cssSelector("a[href='/account']");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click personal account link")
    public void clickAccount() {
        click(ACCOUNT_LINK);
    }
}
