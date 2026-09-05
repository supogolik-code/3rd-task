package ru.praktikum.stellarburgers.ui.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.stellarburgers.ui.api.UserApiHelper;
import ru.praktikum.stellarburgers.ui.model.TestUser;
import ru.praktikum.stellarburgers.ui.page.LoginPage;
import ru.praktikum.stellarburgers.ui.page.RegisterPage;

import static org.junit.Assert.assertTrue;

@Epic("Stellar Burgers UI")
@Feature("Registration")
public class RegistrationTest extends BaseUiTest {

    private UserApiHelper userApi;
    private TestUser user;
    private boolean userWasRegistered;

    @Before
    public void prepareUser() {
        userApi = new UserApiHelper();
        user = TestUser.unique();
    }

    @After
    public void deleteRegisteredUser() {
        if (userWasRegistered) {
            userApi.delete(userApi.login(user));
        }
    }

    @Test
    public void userCanRegisterWithValidData() {
        new RegisterPage(driver).open().register(user);
        userWasRegistered = true;

        new LoginPage(driver).waitUntilOpen();
        assertTrue("After registration the login page should open",
                driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void passwordShorterThanSixCharactersIsRejected() {
        RegisterPage registerPage = new RegisterPage(driver).open();

        registerPage.registerWithPassword(user, "12345");

        assertTrue("Registration page should remain open for an invalid password", registerPage.isOpen());
    }
}
