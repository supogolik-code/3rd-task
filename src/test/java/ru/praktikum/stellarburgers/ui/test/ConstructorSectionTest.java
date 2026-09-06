package ru.praktikum.stellarburgers.ui.test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.stellarburgers.ui.page.HomePage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
@Epic("Stellar Burgers UI")
@Feature("Constructor sections")
public class ConstructorSectionTest extends BaseUiTest {

    private final String sectionName;

    public ConstructorSectionTest(String sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters(name = "section {0}")
    public static Collection<Object[]> sections() {
        return Arrays.asList(new Object[][]{
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        });
    }

    @Test
    @DisplayName("Constructor section can be selected")
    @Description("Checks that each constructor section tab becomes active after selection.")
    public void constructorSectionCanBeSelected() {
        HomePage homePage = new HomePage(driver).open();
        if ("Булки".equals(sectionName)) {
            homePage.selectSection("Соусы");
            assertTrue(homePage.isSectionSelected("Соусы"));
        }

        homePage.selectSection(sectionName);

        assertTrue("Selected tab should be active", homePage.isSectionSelected(sectionName));
    }
}
