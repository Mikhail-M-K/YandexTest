import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class RunTest {
    @Before
    public void beforeTest() {
        open("http://auto.drom.ru/");
        $(By.xpath("//input[@placeholder='Марка']")).click();
        $(By.xpath("//input[@placeholder='Марка']")).setValue("Toyota");
        String inBrand = $(By.cssSelector("div[class='css-u25ii9 e154wmfa0']> div > div:nth-child(3)")).getText();
        $(By.cssSelector("div[class='css-u25ii9 e154wmfa0']> div > div:nth-child(3)")).click();
        sleep(1000);
        String brand = $(By.cssSelector("div[class='css-75hx9m e1a8pcii0']>input")).getAttribute("placeholder");
        System.out.println(brand);
        Assert.assertEquals(inBrand, brand); //Проверим что ввелась Toyota
        sleep(1000);
    }
    @Test
    public void parametersOneCar() {
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
        $(By.cssSelector("div[class='css-u25ii9 e154wmfa0']> div > div:nth-child(3)")).click();
        $(By.xpath("//button[@data-ftid='sales__filter_submit-button']")).click();
        String searchNissan = $(By.cssSelector("div[class='css-17lk78h e3f4v4l2']")).getText();
        Assert.assertEquals("Ошибка в поиске марки Nissan:", searchNissan ,"Nissan");
    }

    @After
    public void afterTest() {
        $(By.xpath("//div[@class='css-14wh0pm e1lm3vns0']/div/a[contains(text(),'2')]")).click();
        $$(By.cssSelector("a[class='css-xb5nz8 e1huvdhj1']")).get(2).click();
        //вывод заголовка страницы о продаже машины
        System.out.println($(By.cssSelector("h1[class='css-1tjirrw e18vbajn0']")).getText());
    }



}
