package ru.praktikum.stellarburgers.ui.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Step;
import ru.praktikum.stellarburgers.ui.config.TestConfig;
import ru.praktikum.stellarburgers.ui.model.TestUser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserApiHelper {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    @Step("Create test user via API")
    public String create(TestUser user) {
        return sendForToken("/auth/register", user);
    }

    @Step("Login test user via API")
    public String login(TestUser user) {
        return sendForToken("/auth/login", user);
    }

    @Step("Delete test user via API")
    public void delete(String accessToken) {
        if (accessToken == null) {
            return;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TestConfig.API_URL + "/auth/user"))
                .header("Authorization", accessToken)
                .DELETE()
                .build();
        HttpResponse<String> response = send(request);
        if (response.statusCode() != 202) {
            throw new IllegalStateException("Unable to delete test user: " + response.statusCode());
        }
    }

    private String sendForToken(String path, TestUser user) {
        String body = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TestConfig.API_URL + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = send(request);
        if (response.statusCode() != 200) {
            throw new IllegalStateException("User API request failed: " + response.statusCode() + " " + response.body());
        }
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        return json.get("accessToken").getAsString();
    }

    private HttpResponse<String> send(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException exception) {
            throw new IllegalStateException("User API is unavailable", exception);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("User API request was interrupted", exception);
        }
    }
}
