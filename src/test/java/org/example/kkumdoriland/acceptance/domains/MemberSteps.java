package org.example.kkumdoriland.acceptance.domains;

import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.net.URI;
import org.example.kkumdoriland.utils.AcceptanceStepBuilder;

public class MemberSteps {
    public static ExtractableResponse<Response> 유저_생성(String name, String email, String password) {
        return AcceptanceStepBuilder.builder()
            .param("name", name)
            .param("email", email)
            .param("password", password)
            .uri(URI.create("/api/user/join"))
            .method(Method.POST)
            .build();
    }

    public static ExtractableResponse<Response> 유저_로그인(String email, String password) {
        return AcceptanceStepBuilder.builder()
            .param("email", email)
            .param("password", password)
            .uri(URI.create("/api/user/login"))
            .method(Method.POST)
            .build();
    }
}
