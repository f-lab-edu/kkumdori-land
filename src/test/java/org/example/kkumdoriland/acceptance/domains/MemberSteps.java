package org.example.kkumdoriland.acceptance.domains;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;

public class MemberSteps {
    public static ExtractableResponse<Response> 유저_생성(String name, String email, String password) {
        final Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);

        return RestAssured.given().log().all()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/user/join")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 유저_로그인(String email, String password) {
        final Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        return RestAssured.given().log().all()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/user/login")
            .then().log().all()
            .extract();
    }
}
