package org.example.kkumdoriland.acceptance.domains;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;

public class DreamSteps {

    public static ExtractableResponse<Response> 꿈_생성(String cookie, String title, String description, String dueDate) {
        final Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("description", description);
        params.put("dueDate", dueDate);

        return RestAssured.given().log().all()
            .cookie("JSESSIONID", cookie)
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/dream")
            .then().log().all()
            .extract();
    }
}
