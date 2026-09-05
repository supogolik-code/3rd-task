package ru.praktikum.stellarburgers.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.praktikum.stellarburgers.ui.config.TestConfig;

public class HomePage extends BasePage {

    private static final By TITLE = By.xpath("//h1[normalize-space()='Соберите бургер']");
    private static final By LOGIN_BUTTON = By.xpath("//button[normalize-space()='Войти в аккаунт']");
    private static final By ORDER_BUTTON = By.xpath("//button[normalize-space()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(TestConfig.BASE_URL + "/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    public boolean isOrderButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_BUTTON)).isDisplayed();
    }

    public void selectSection(String sectionName) {
        click(sectionTab(sectionName));
    }

    public boolean isSectionSelected(String sectionName) {
        return wait.until(driver -> {
            String classes = driver.findElement(sectionTab(sectionName)).getAttribute("class");
            return classes != null && classes.contains("tab_tab_type_current");
        });
    }

    private By sectionTab(String sectionName) {
        return By.xpath("//span[normalize-space()='" + sectionName
                + "']/parent::div[contains(@class,'tab_tab')]");
    }
}
