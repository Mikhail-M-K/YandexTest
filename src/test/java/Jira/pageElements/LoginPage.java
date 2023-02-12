package Jira.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    public static SelenideElement loginInput = $x("//input[@id='login-form-username']").as("Ввод логина");
    public static SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("Ввод пароля");
    public static SelenideElement btnEnter = $x("//input[@value='Войти']").as("Кнопка войти");
}
