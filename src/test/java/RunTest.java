import Jira.ConfProperties;
import Jira.hooks.WebHooks;
import org.junit.Assert;
import org.junit.Test;

import static Jira.pageElements.BoardPage.*;
import static Jira.steps.BoardSteps.*;
import static Jira.steps.Navigation.*;

public class RunTest extends WebHooks {

    @Test
    public void transitionalToTheTasks(){
        openAllTasks();
        changeViewList();
        String numTasks = valueTaskAll.getText();
        System.out.println("Всего задач: " + valueTaskAll.getText());
        Assert.assertEquals(numTasks, valueTaskAll.getText());
        changeView();
    }

    @Test
    public void checkTestSelenium(){
        openAllTasks();
        searchTest(ConfProperties.getProperty("nameTest"));
        Assert.assertEquals(header.getText(),"TestSelenium");
        System.out.println("Статус TestSelenium: " + statusText.getText());
        System.out.println(versionText.getText().equals("Version 2.0") ? "Version 2.0":"Привязка версии отличается от Version 2.0");

    }

    @Test
    public void createBug(){
        String theme = "Наложения текста на текст \"О сайте\" при большом размере генерируемого пароля";
        String description = "При генерации пароля происходит наложение текста на элемент описания";
        String environment = "Супер мега компьютер на celeron -5000";
        createBugNavigation(theme, description, environment);
        checkCreateBug(theme, description, environment);
    }


    @Test
    public void statusChange(){
        openMyBug();
        checkStatusBag("СДЕЛАТЬ");
        btnInWork.click();
        checkStatusBag("В РАБОТЕ");
        selectExecuted();
        checkStatusBag("РЕШЕННЫЕ");
        selectConfirm();
        checkStatusBag("ГОТОВО");
    }


}
