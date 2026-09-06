package ru.praktikum.stellarburgers.ui.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.ui.api.UserApiHelper;
import ru.praktikum.stellarburgers.ui.model.TestUser;
import ru.praktikum.stellarburgers.ui.page.ForgotPasswordPage;
import ru.praktikum.stellarburgers.ui.page.HeaderPage;
import ru.praktikum.stellarburgers.ui.page.HomePage;
import ru.praktikum.stellarburgers.ui.page.LoginPage;
import ru.praktikum.stellarburgers.ui.page.RegisterPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
@Epic("Stellar Burgers UI")
@Feature("Login")
public class LoginTest extends BaseUiTest {

    private final LoginEntryPoint entryPoint;
    private UserApiHelper userApi;
    private TestUser user;
    private String accessToken;

    public LoginTest(LoginEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Parameterized.Parameters(name = "login via {0}")
    public static Collection<Object[]> entryPoints() {
        return Arrays.asList(new Object[][]{
                {LoginEntryPoint.HOME_BUTTON},
                {LoginEntryPoint.ACCOUNT_LINK},
                {LoginEntryPoint.REGISTRATION_FORM},
                {LoginEntryPoint.FORGOT_PASSWORD_FORM}
        });
    }

    @Before
    @Step("Prepare test user")
    public void createUser() {
        userApi = new UserApiHelper();
        user = TestUser.unique();
        accessToken = userApi.create(user);
    }

    @After
    @Step("Delete test user")
    public void deleteUser() {
        if (userApi != null && accessToken != null) {
            userApi.delete(accessToken);
        }
    }

    @Test
    @DisplayName("User can log in from selected entry point")
    @Description("Checks user login from the home button, account link, registration form, and forgot password form.")
    public void userCanLoginFromSelectedEntryPoint() {
        openLoginPage();
        new LoginPage(driver).waitUntilOpen().login(user);

        assertTrue("Order button should be visible after login",
                new HomePage(driver).isOrderButtonVisible());
    }

    @Step("Open login page from selected entry point")
    private void openLoginPage() {
        switch (entryPoint) {
            case HOME_BUTTON:
                new HomePage(driver).open().clickLoginButton();
                break;
            case ACCOUNT_LINK:
                new HomePage(driver).open();
                new HeaderPage(driver).clickAccount();
                break;
            case REGISTRATION_FORM:
                new RegisterPage(driver).open().clickLoginLink();
                break;
            case FORGOT_PASSWORD_FORM:
                new ForgotPasswordPage(driver).open().clickLoginLink();
                break;
            default:
                throw new IllegalStateException("Unknown entry point: " + entryPoint);
        }
    }

    public enum LoginEntryPoint {
        HOME_BUTTON,
        ACCOUNT_LINK,
        REGISTRATION_FORM,
        FORGOT_PASSWORD_FORM
    }
}
