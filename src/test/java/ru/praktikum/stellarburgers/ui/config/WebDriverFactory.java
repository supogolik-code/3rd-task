package ru.praktikum.stellarburgers.ui.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver create() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        ChromeOptions options = commonOptions();

        if ("yandex".equals(browser)) {
            options.setBinary(resolveYandexBinary());
            String driverPath = System.getProperty("yandex.driver");
            if (driverPath != null && !driverPath.isBlank()) {
                File driver = Paths.get(driverPath).toFile();
                if (!driver.isFile()) {
                    throw new IllegalStateException("Yandex ChromeDriver was not found: " + driverPath);
                }
                System.setProperty("webdriver.chrome.driver", driver.getAbsolutePath());
            }
        } else if ("chrome".equals(browser)) {
            options.setBinary(resolveChromeBinary());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser + ". Use chrome or yandex.");
        }

        return new ChromeDriver(options);
    }

    private static ChromeOptions commonOptions() {
        ChromeOptions options = new ChromeOptions();
        String profile = Paths.get(System.getProperty("user.dir"), "target", "browser-profiles", UUID.randomUUID().toString()).toString();
        options.addArguments(
                "--window-size=1440,900",
                "--disable-notifications",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*",
                "--user-data-dir=" + profile);
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static String resolveChromeBinary() {
        String configured = System.getProperty("chrome.binary");
        List<Path> candidates = Arrays.asList(
                configured == null ? Paths.get("") : Paths.get(configured),
                Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"),
                Paths.get("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe")
        );
        return candidates.stream()
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Google Chrome was not found. Set -Dchrome.binary=<path-to-chrome.exe>"));
    }

    private static String resolveYandexBinary() {
        String configured = System.getProperty("yandex.binary");
        if (configured != null && Files.isRegularFile(Paths.get(configured))) {
            return configured;
        }

        String localAppData = System.getenv("LOCALAPPDATA");
        List<Path> candidates = Arrays.asList(
                localAppData == null ? Paths.get("") : Paths.get(localAppData, "Yandex", "YandexBrowser", "Application", "browser.exe"),
                Paths.get("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe"),
                Paths.get("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe")
        );

        return candidates.stream()
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Yandex Browser was not found. Set -Dyandex.binary=<path-to-browser.exe>"));
    }
}
