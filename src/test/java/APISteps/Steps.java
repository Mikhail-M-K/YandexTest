package APISteps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.RestAssured.given;

public  class Steps {
    public static String charId, characterRace, characterLocation, personRace, personLocation, characterName;
    public static int lastEpisode, idLastPerson;
    public static Map<String, String> cookies;



    static RequestSpecification reqRickMorty = new RequestSpecBuilder()
            .setBaseUri("https://rickandmortyapi.com/api")
            .build();


    @Дано ("^Получить инфо про персонажа по ID '(.*)'$")
    @Step("Получение информации по  персонажу {id}")
    public static void getCharacter(String id){
        Response gettingCharacter = given()
                .spec(reqRickMorty)
                .when()
                .get("/character/" + id)
                .then()
                .extract()
                .response();
        charId = new JSONObject(gettingCharacter.getBody().asString()).get("id").toString();
        characterName = new JSONObject(gettingCharacter.getBody().asString()).get("name").toString();
        characterRace = new JSONObject(gettingCharacter.getBody().asString()).get("species").toString();
        characterLocation = new JSONObject(gettingCharacter.getBody().asString()).getJSONObject("location").get("name").toString();
        System.out.println("Персонаж под ID " + charId + " : " + characterName);
    }

    @Step("Получение последнего эпизод с участием выбранного персонажа")
    @Затем("^Получить последний эпизод с участием выбранного персонажа$")
    public static void getEpisode(){
        Response gettingLastEpisode = given()
                .spec(reqRickMorty)
                .when()
                .get("/character/" + charId)
                .then()
                .extract()
                .response();
        int episode = (new JSONObject(gettingLastEpisode.getBody().asString()).getJSONArray("episode").length()-1);
        lastEpisode = Integer.parseInt(new JSONObject(gettingLastEpisode.getBody().asString())
                .getJSONArray("episode").get(episode).toString().replaceAll("[^0-9]",""));
        System.out.println("Последний эпизод где присутствовал "+ characterName +": " + lastEpisode);
    }

    @Step("Получение последнего персонажа в эпизоде")
    @Затем("^Получить последнего персонажа в эпизоде$")
    public static void getPerson() {
        Response gettingLastPerson = given()
                .spec(reqRickMorty)
                .when()
                .get("/episode/" + lastEpisode)
                .then()
                .extract()
                .response();
        int person = (new JSONObject(gettingLastPerson.getBody().asString()).getJSONArray("characters").length() - 1);
        idLastPerson = Integer.parseInt(new JSONObject(gettingLastPerson.getBody().asString())
                .getJSONArray("characters").get(person).toString().replaceAll("[^0-9]", ""));
        System.out.println("ID последнего персонажа в эпизоде: " + idLastPerson);
    }

    @Step("Получение информации о последнем персонаже")
    @Затем("^Получить информацию о последнем персонаже$")
    public static void getPersonLast(){
        Response gettingParametersPerson = given()
                .spec(reqRickMorty)
                .when()
                .get("/character/" + idLastPerson)
                .then()
                .extract()
                .response();
        personRace = new JSONObject(gettingParametersPerson.getBody().asString()).get("species").toString();
        personLocation = new JSONObject(gettingParametersPerson.getBody().asString()).getJSONObject("location").get("name").toString();
        System.out.println("Данные персонажа: " + personRace + ", " + personLocation);
        System.out.println("Данные " + characterName + ": " + characterRace +  ", " + characterLocation);
    }

    @Step("Сравнение совпадение расы и локаций")
    @И("^Сравнить совпадение расы и локаций$")
    public static void checkData(){
        Assert.assertEquals("Расы отличаются => ",personRace, characterRace);
        Assert.assertEquals("Места нахождения отличаются => ",personLocation, characterLocation);
    }

    @Step("Отправление запроса и сравнение результатов c {name}, {job}")
    @Затем ("^Отправить запрос, сравнив результаты c '(.*)', '(.*)'$")
    public static void createPersonAndCheck(String name, String job) throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/1.json"))));
        body.put("name", "Tomato");
        body.put("job", "Eat market");
        Response postJson = given()
                .header("Content-type", "application/json")
                .baseUri("https://reqres.in/api")
                .body(body.toString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .response();
        Assertions.assertEquals((new JSONObject(postJson.getBody().asString()).get("name")), (name), "Fail");
        Assertions.assertEquals((new JSONObject(postJson.getBody().asString()).get("job")), (job), "Fail");
        System.out.println("ID созданного пользователя: " + (new JSONObject(postJson.getBody().asString()).get("id")));
        System.out.println("Время создания профиля: " + (new JSONObject(postJson.getBody().asString()).get("createdAt")));
    }

    @Step("Авторизация на Jira")
    @Затем ("^Авторизация на Jira$")
    public static void authorizationJira() throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/2.json"))));
        Response postJsonJira = given()
                .header("Content-type", "application/json")
                .baseUri("https://edujira.ifellow.ru")
                .body(body.toString())
                .when()
                .post("/rest/auth/1/session")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println("Признаки авторизации:");
        System.out.println(new JSONObject(postJsonJira.getBody().asString()));
        cookies = postJsonJira.getCookies();
        System.out.println("-------------------------------------------");
    }

    @Step("Вывод информации по пользователю")
    @Затем ("^Вывод информации по пользователю$")
    public static void getPersonInfo() {
        Response sessionJira = given()
                .cookies(cookies)
                .baseUri("https://edujira.ifellow.ru")
                .when()
                .get("/rest/auth/1/session")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(new JSONObject(sessionJira.getBody().asString()));
        System.out.println("-----------------------------------------------");
    }

    @Step("Выход пользователя")
    @Затем ("^Выход пользователя$")
    public static void exitPerson() {
        Response sessionJira = given()
                .cookies(cookies)
                .baseUri("https://edujira.ifellow.ru")
                .when()
                .delete("/rest/auth/1/session")
                .then()
                .log().all()
                .statusCode(204)
                .extract()
                .response();
        System.out.println("Выход произошел");
    }
}

