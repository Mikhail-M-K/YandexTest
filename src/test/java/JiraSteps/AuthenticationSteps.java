package JiraSteps;

import static pageElements.LoginPage.*;

public class AuthenticationSteps{
    public static void authentication(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        btnEnter.click();
    }
}
