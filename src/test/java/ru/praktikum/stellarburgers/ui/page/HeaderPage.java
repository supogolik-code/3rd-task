package ru.praktikum.stellarburgers.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

    private static final By ACCOUNT_LINK = By.cssSelector("a[href='/account']");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void clickAccount() {
        click(ACCOUNT_LINK);
    }
}
