package org.example.kkumdoriland.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class AcceptanceContext {

    private static final String TOKEN_POSTFIX = "_token";

    public Map<String, Object> store = new HashMap<>();
    public ExtractableResponse<Response> response;

    public void setToken(String userName, String token) {
        store.put(userName + TOKEN_POSTFIX, token);
    }

    public String getToken(String userName) {
        return (String) store.get(userName + TOKEN_POSTFIX);
    }
}
