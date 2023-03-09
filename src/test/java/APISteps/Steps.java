package APISteps;

import io.qameta.allure.Allure;
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
    public static String charId, characterRace, characterLocation, personRace, personLocation, characterName, personName;
    public static int lastEpisode, idLastPerson;
    public static Map<String, String> cookies;



    static RequestSpecification reqRickMorty = new RequestSpecBuilder()
            .setBaseUri("https://rickandmortyapi.com/api")
            .build();


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

        Allure.addAttachment("ID персанажа", charId);
        Allure.addAttachment("Имя персанажа", characterName);
        Allure.addAttachment("Раса персанажа", characterRace);
        Allure.addAttachment("Локация персанажа", characterLocation);


    }

    @Step("Получение последнего эпизод с участием выбранного персонажа")
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

        Allure.addAttachment("Последний эпизод где присутствовал "+ characterName +": ", "" + lastEpisode);
    }

    @Step("Получение последнего персонажа в эпизоде")
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

        Allure.addAttachment("ID последнего персонажа в эпизоде", "" + idLastPerson);
    }

    @Step("Получение информации о последнем персонаже")
    public static void getPersonLast(){
        Response gettingParametersPerson = given()
                .spec(reqRickMorty)
                .when()
                .get("/character/" + idLastPerson)
                .then()
                .extract()
                .response();
        personName = new JSONObject(gettingParametersPerson.getBody().asString()).get("name").toString();
        personRace = new JSONObject(gettingParametersPerson.getBody().asString()).get("species").toString();
        personLocation = new JSONObject(gettingParametersPerson.getBody().asString()).getJSONObject("location").get("name").toString();
        System.out.println("Данные персонажа: " + personRace + ", " + personLocation);
        System.out.println("Данные " + characterName + ": " + characterRace +  ", " + characterLocation);

        Allure.addAttachment("Раса " + personName + ": ", personRace);
        Allure.addAttachment("Локация " + personName + ": ", personLocation);
    }

    @Step("Сравнение совпадение расы и локаций")
    public static void checkData(){
        Allure.addAttachment("Раса " + personName + ": ", personRace);
        Allure.addAttachment("Локация " + personName + ": ", personLocation);

        Allure.addAttachment("Раса " + characterName + ": ", characterRace);
        Allure.addAttachment("Локация " + characterName + ": ", characterLocation);

        Assert.assertEquals("Расы отличаются => ",personRace, characterRace);
        Assert.assertEquals("Места нахождения отличаются => ",personLocation, characterLocation);
    }

    @Step("Отправление запроса и сравнение результатов c {name}, {job}")
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
        String idAccount = new JSONObject(postJson.getBody().asString()).get("id").toString();
        String timeCreate = new JSONObject(postJson.getBody().asString()).get("createdAt").toString();
        System.out.println("ID созданного пользователя: " + idAccount);
        System.out.println("Время создания профиля: " + timeCreate);

        Allure.addAttachment("Отправленный json с данными", body.toString());
        Allure.addAttachment("ID созданного пользователя", idAccount);
        Allure.addAttachment("Время создания профиля", timeCreate);
    }

    @Step("Авторизация на Jira")
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
        String authorizationJson = new JSONObject(postJsonJira.getBody().asString()).toString();
        System.out.println("Признаки авторизации:");
        System.out.println(authorizationJson);
        cookies = postJsonJira.getCookies();
        System.out.println("-------------------------------------------");
        Allure.addAttachment("Данные для авторизации", body.toString());
        Allure.addAttachment("Признаки авторизации", authorizationJson);
    }

    @Step("Вывод информации по пользователю")
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
        String infoAccount = new JSONObject(sessionJira.getBody().asString()).toString();
        System.out.println(infoAccount);
        System.out.println("-----------------------------------------------");

        Allure.addAttachment("Информация о пользователе", infoAccount);
    }

    @Step("Выход пользователя")
    public static void exitPerson() {
        Response exitJira = given()
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
        //String exitAccount = new JSONObject(exitJira.getBody().asString()).toString();
        Allure.addAttachment("Статус запроса на выход пользователя", exitJira.getStatusCode() + "");
    }
}

