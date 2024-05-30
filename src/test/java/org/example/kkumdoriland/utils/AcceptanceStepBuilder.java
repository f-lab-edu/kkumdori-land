package org.example.kkumdoriland.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

public class AcceptanceStepBuilder {
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private MediaType contentType = MediaType.APPLICATION_JSON;
    private Method method;
    private URI uri;

    private AcceptanceStepBuilder() {
    }

    public AcceptanceStepBuilder param(String key, String value) {
        params.put(key, value);
        return this;
    }

    public AcceptanceStepBuilder cookie(String key, String value) {
        cookies.put(key, value);
        return this;
    }

    public AcceptanceStepBuilder contentType(MediaType contentType) {
        this.contentType = contentType;
        return this;
    }

    public AcceptanceStepBuilder method(Method method) {
        this.method = method;
        return this;
    }

    public AcceptanceStepBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public static AcceptanceStepBuilder builder() {
        return new AcceptanceStepBuilder();
    }

    public ExtractableResponse<Response> build() {
        Assert.notNull(method, "method must not be null");
        Assert.notNull(uri, "uri must not be null");

        if(method == Method.GET) {
            return RestAssured
                .given().log().all()
                    .params(params)
                    .cookies(cookies)
                    .contentType(contentType.toString())
                .when()
                    .get(uri)
                .then().log().all()
                .extract();
        }

        return RestAssured
            .given().log().all()
                .body(params)
                .cookies(cookies)
                .contentType(contentType.toString())
            .when()
                .request(method, uri)
            .then().log().all()
            .extract();
    }
}
