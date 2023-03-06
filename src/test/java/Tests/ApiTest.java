package Tests;

import Hooks.ApiHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static APISteps.Steps.*;

public class ApiTest extends ApiHooks {
    @Test
    @DisplayName("Проверка расы и локации персонажей")
    public void testRaceAndLocation() {
        getCharacter("2");
        getEpisode();
        getPerson();
        getPersonLast();
        checkData();
    }

    @Test
    @DisplayName("Создание и проверка пользователя")
    public void testAddAndCheckUser() throws IOException {
        createPersonAndCheck("Tomato", "Eat market");

    }

    @Test
    @DisplayName("Работа с Jira")
    public void testAddAndCheckPerson() throws IOException {
        authorizationJira();
        getPersonInfo();
        exitPerson();

    }


}
