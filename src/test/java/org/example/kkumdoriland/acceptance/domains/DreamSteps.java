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

    public static ExtractableResponse<Response> 마일스톤_생성(String cookie, Long dreamId, String title, String description, int totalScoreToEarn, int minimumStepSize) {
        final Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("description", description);
        params.put("totalScoreToEarn", Integer.toString(totalScoreToEarn));
        params.put("minimumStepSize", Integer.toString(minimumStepSize));
        params.put("dreamId", Long.toString(dreamId));

        return RestAssured.given().log().all()
            .cookie("JSESSIONID", cookie)
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/dream/milestone")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 하루기록_생성(String cookie, long milestoneId, String contentText, int stepSize) {
        final Map<String, String> params = new HashMap<>();
        params.put("contentText", contentText);
        params.put("stepSize", Integer.toString(stepSize));
        params.put("mileStoneId", Long.toString(milestoneId));

        return RestAssured.given().log().all()
            .cookie("JSESSIONID", cookie)
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/dream/milestone/dailyHistory")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 꿈_조회(String cookie) {
        return RestAssured.given().log().all()
            .cookie("JSESSIONID", cookie)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/dream")
            .then().log().all()
            .extract();
    }
}
