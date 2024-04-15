package org.example.kkumdoriland.acceptance.steps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.kkumdoriland.acceptance.domains.UserSteps.유저_생성;
import static org.example.kkumdoriland.utils.ResponseUtils.응답에서_id_조회;
import static org.example.kkumdoriland.utils.ResponseUtils.응답의_STATUS_검증;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import java.util.Map;
import java.util.List;
import org.example.kkumdoriland.acceptance.AcceptanceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class UserStepDef implements En {

    @Autowired
    private AcceptanceContext context;

    public UserStepDef() {
        Given("유저 정보를 생성하고", (DataTable table) -> {
            List<Map<String, String>> maps = table.asMaps();

            maps.forEach(it -> context.store.put(
                it.get("name"),
                응답에서_id_조회(유저_생성(it.get("name"), it.get("email"), it.get("password")))
            ));
        });

        When("유저는 {string}, {string}, {string}을 이용해 회원가입한다.", (String name, String email, String password) -> {
           context.response = 유저_생성(name, email, password);
        });

        Then("유저는 회원가입에 성공한다.", () -> {
            응답의_STATUS_검증(context.response, HttpStatus.CREATED);
        });

        Then("유저는 회원가입에 {int}으로 실패한다.", (Integer statusCode) -> {
            응답의_STATUS_검증(context.response, HttpStatus.valueOf(statusCode));
        });
    }
}
