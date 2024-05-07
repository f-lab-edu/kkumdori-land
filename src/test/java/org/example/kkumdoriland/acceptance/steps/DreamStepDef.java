package org.example.kkumdoriland.acceptance.steps;

import static org.example.kkumdoriland.acceptance.domains.DreamSteps.꿈_생성;
import static org.example.kkumdoriland.acceptance.domains.DreamSteps.마일스톤_생성;
import static org.example.kkumdoriland.utils.ResponseUtils.응답에서_id_조회;
import static org.example.kkumdoriland.utils.ResponseUtils.응답의_STATUS_검증;

import io.cucumber.java8.En;
import org.example.kkumdoriland.acceptance.AcceptanceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DreamStepDef implements En {

    @Autowired
    private AcceptanceContext context;

    public DreamStepDef() {
        When("{string} 유저는 {string}, {string}, {string}을 이용해 꿈을 생성한다.", (String userName, String title, String description, String dueDate) -> {
            context.response = 꿈_생성(context.getToken(userName), title, description, dueDate);
        });

        When("{string} 유저는 {string}, {string}, {string}을 이용해 마일스톤을 생성한다.", (String userName, String dreamTitle, String title, String description) -> {
            context.response = 마일스톤_생성(
                context.getToken(userName),
                응답에서_id_조회(context.response),
                title, description, 10, 10
            );
        });

        Then("유저는 꿈을 생성에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });

        Then("유저는 마일스톤을 생성에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });
    }
}
