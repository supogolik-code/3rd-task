package ru.praktikum.stellarburgers.ui.config;

public final class TestConfig {

    public static final String BASE_URL = System.getProperty(
            "base.url", "https://stellarburgers.education-services.ru");
    public static final String API_URL = BASE_URL + "/api";

    private TestConfig() {
    }
}
