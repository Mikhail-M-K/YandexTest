import Jira.ConfProperties;
import Jira.hooks.WebHooks;
import org.junit.Assert;
import org.junit.Test;

import static Jira.pageElements.BoardPage.*;
import static Jira.pageElements.NavigationBar.*;
import static Jira.pageElements.PopupCreate.*;

public class RunTest extends WebHooks {

    @Test
    public void transitionalToTheTasks(){
        openAllTasks();
        changeViewList();
        System.out.println("Всего задач: " + valueTaskAll.getText());
        changeView();
    }

    @Test
    public void checkTestSelenium(){
        openAllTasks();
        searchTest(ConfProperties.getProperty("nameTest"));
        Assert.assertEquals(header.getText(),"TestSelenium");
        System.out.println("Статус TestSelenium: " + statusText.getText());
        Assert.assertEquals("Поле \"Затронуты версии:\" изменен", versionText.getText(), "Version 2.0");
    }

    @Test
    public void createBag(){
        btnCreate.click();
        inputTheme.sendKeys("Наложения текста на текст \"О сайте\" при большом размере генерируемого пароля");
        inputDescription.sendKeys("При генерации пароля происходит наложение текста на элемент описания");
        btnText.scrollTo().click();
        selectVersion.click();
        forScroll.scrollTo();
        inputEnvironment.sendKeys("Супер мега компьютер на celeron -5000");
        btnCreateError.click();
    }

    @Test
    public void statusChange(){
        openSiteProfile();
        openMyBag();
        btnInWork.click();
        selectExecuted();
        selectConfirm();
        btnDone.click();
    }
}
