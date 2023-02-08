import hooks.WebHooks;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class RunTest extends WebHooks {

    @Test
    public void parametersOneCar()  {

        $(By.xpath("//input[@placeholder='Модель']")).click();
        sleep(1000);
        $(By.xpath("//input[@placeholder='Модель']")).setValue("Camry");
        $(By.cssSelector("div[class='css-u25ii9 e154wmfa0']> div > div:nth-child(3)")).click();
        $(By.xpath("//button[@data-ftid='sales__filter_submit-button']")).click();
        sleep(2000);
        //вывод информации первой машины
        System.out.println($(By.cssSelector("div[class='css-1fe6w6s e162wx9x0']")).getText());
    }

    @Test
    public void errorTest(){
        $(By.xpath("//input[@placeholder='Модель']")).click();
        sleep(1000);
        $(By.xpath("//input[@placeholder='Модель']")).setValue("Camry");
        $(By.cssSelector("div[class='css-u25ii9 e154wmfa0'] > div > div:nth-child(3)")).click();
        $(By.xpath("//button[@data-ftid='sales__filter_submit-button']")).click();
        String searchNissan = $(By.cssSelector("div[class='css-17lk78h e3f4v4l2']")).getText();
        Assert.assertEquals("Ошибка в поиске марки Nissan:", searchNissan ,"Nissan");
    }

}
