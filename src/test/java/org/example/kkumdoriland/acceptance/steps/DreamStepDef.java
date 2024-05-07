package org.example.kkumdoriland.acceptance.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.kkumdoriland.acceptance.domains.DreamSteps.꿈_생성;
import static org.example.kkumdoriland.acceptance.domains.DreamSteps.꿈_조회;
import static org.example.kkumdoriland.acceptance.domains.DreamSteps.마일스톤_생성;
import static org.example.kkumdoriland.acceptance.domains.DreamSteps.하루기록_생성;
import static org.example.kkumdoriland.utils.ResponseUtils.응답에서_id_조회;
import static org.example.kkumdoriland.utils.ResponseUtils.응답의_STATUS_검증;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import java.util.List;
import java.util.Map;
import org.example.kkumdoriland.acceptance.AcceptanceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class DreamStepDef implements En {

    private static final String DREAM_POSTFIX = "_dream";

    @Autowired
    private AcceptanceContext context;

    public DreamStepDef() {
        Given("유저는 꿈을 생성한다.", (DataTable tables) -> {
            List<Map<String, String>> maps = tables.asMaps();

            maps.forEach(it -> saveDream(
                it.get("name"),
                응답에서_id_조회(꿈_생성(
                    context.getToken(it.get("userName")),
                    it.get("title"),
                    it.get("description"),
                    it.get("dueDate")
                ))
            ));
        });

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

        When("{string} 유저는 {string}, {int}을 이용해 하루기록을 생성한다.", (String userName, String contentText, Integer stepSize) -> {
            context.response = 하루기록_생성(
                context.getToken(userName),
                응답에서_id_조회(context.response),
                contentText,
                stepSize
            );
        });

        When("{string} 유저는 자신의 꿈을 조회한다.", (String userName) -> {
            context.response = 꿈_조회(context.getToken(userName));
        });

        Then("유저는 꿈을 생성에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });

        Then("유저는 마일스톤을 생성에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });

        Then("유저는 하루기록을 생성에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });

        Then("{int}개 꿈이 조회 된다.", (Integer numberOfDreams) -> {
            assertThat(context.response.jsonPath().getList("").size()).isEqualTo(3);
        });
    }

    public void saveDream(String name, Long id) {
        context.store.put(name + DREAM_POSTFIX, id);
    }

    public Long getDream(String name) {
        return (Long) context.store.get(name + DREAM_POSTFIX);
    }

}
