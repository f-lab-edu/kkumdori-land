package org.example.kkumdoriland.acceptance.steps;

import static org.example.kkumdoriland.acceptance.domains.DreamSteps.꿈_생성;
import static org.example.kkumdoriland.utils.ResponseUtils.응답의_STATUS_검증;

import io.cucumber.java8.En;
import org.example.kkumdoriland.acceptance.AcceptanceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DreamStepDef implements En {

    @Autowired
    private AcceptanceContext context;

    public DreamStepDef() {
        When("유저는 {string}, {string}, {string}을 이용해 꿈을 생성한다.", (String title, String description, String dueDate) -> {
            context.response = 꿈_생성(context.response.cookie("JSESSIONID"), title, description, dueDate);
        });

        Then("유저는 꿈을 생성에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });
    }
}
