package ru.praktikum.stellarburgers.ui.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
    @Step("Prepare unique test user")
    public void prepareUser() {
        userApi = new UserApiHelper();
        user = TestUser.unique();
    }

    @After
    @Step("Delete registered user")
    public void deleteRegisteredUser() {
        if (userWasRegistered) {
            userApi.delete(userApi.login(user));
        }
    }

    @Test
    @DisplayName("User can register with valid data")
    @Description("Checks that registration with valid name, email, and password opens the login page.")
    public void userCanRegisterWithValidData() {
        new RegisterPage(driver).open().register(user);
        userWasRegistered = true;

        new LoginPage(driver).waitUntilOpen();
        assertTrue("After registration the login page should open",
                driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    @DisplayName("Short password is rejected during registration")
    @Description("Checks that a password shorter than six characters keeps the user on the registration page.")
    public void passwordShorterThanSixCharactersIsRejected() {
        RegisterPage registerPage = new RegisterPage(driver).open();

        registerPage.registerWithPassword(user, "12345");

        assertTrue("Registration page should remain open for an invalid password", registerPage.isOpen());
    }
}
