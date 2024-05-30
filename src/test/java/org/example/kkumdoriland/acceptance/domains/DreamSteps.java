package org.example.kkumdoriland.acceptance.domains;

import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.net.URI;
import org.example.kkumdoriland.utils.AcceptanceStepBuilder;

public class DreamSteps {

    public static ExtractableResponse<Response> 꿈_생성(String cookie, String title, String description, String dueDate) {
        return AcceptanceStepBuilder.builder()
            .cookie("JSESSIONID", cookie)
            .param("title", title)
            .param("description", description)
            .param("dueDate", dueDate)
            .uri(URI.create("/api/dream"))
            .method(Method.POST)
            .build();
    }

    public static ExtractableResponse<Response> 마일스톤_생성(String cookie, Long dreamId, String title, String description, int totalScoreToEarn, int minimumStepSize) {
        return AcceptanceStepBuilder.builder()
            .cookie("JSESSIONID", cookie)
            .param("title", title)
            .param("description", description)
            .param("totalScoreToEarn", Integer.toString(totalScoreToEarn))
            .param("minimumStepSize", Integer.toString(minimumStepSize))
            .param("dreamId", Long.toString(dreamId))
            .uri(URI.create("/api/dream/milestone"))
            .method(Method.POST)
            .build();
    }

    public static ExtractableResponse<Response> 하루기록_생성(String cookie, long milestoneId, String contentText, int stepSize) {
        return AcceptanceStepBuilder.builder()
            .cookie("JSESSIONID", cookie)
            .param("contentText", contentText)
            .param("stepSize", Integer.toString(stepSize))
            .param("mileStoneId", Long.toString(milestoneId))
            .uri(URI.create("/api/dream/milestone/dailyHistory"))
            .method(Method.POST)
            .build();
    }

    public static ExtractableResponse<Response> 꿈_조회(String cookie) {
        return AcceptanceStepBuilder.builder()
            .cookie("JSESSIONID", cookie)
            .uri(URI.create("/api/dream"))
            .method(Method.GET)
            .build();
    }
}
