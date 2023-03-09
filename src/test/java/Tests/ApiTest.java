package Tests;

import Hooks.ApiHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static APISteps.Steps.*;

public class ApiTest extends ApiHooks {
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"2"})
    @DisplayName("Отправка запроса с нужным id и проверка совпадения расы и локации")
    public void apiRickMortyTest(String id) {
        getCharacter(id);
        getEpisode();
        getPerson();
        getPersonLast();
        checkData();
    }

    @DisplayName("Добавление пользователя в Reqres.in")
    @ParameterizedTest
    @CsvSource({"Tomato,Eat market"})
    public void createPersonAndCheckTest(String name, String job) throws IOException {
        createPersonAndCheck(name, job);
    }

    @Test
    @DisplayName("Авторизация и выход из аккаунта в Jira")
    public void authorizationJiraTest() throws IOException {
        authorizationJira();
        getPersonInfo();
        exitPerson();
    }
}
