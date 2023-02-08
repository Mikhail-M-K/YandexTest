package hooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class WebHooks {
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

    @After
    public void afterTest() {
        $(By.xpath("//div[@class='css-14wh0pm e1lm3vns0']/div/a[contains(text(),'2')]")).click();
        $$(By.cssSelector("a[class='css-xb5nz8 e1huvdhj1']")).get(2).click();
        //вывод заголовка страницы о продаже машины
        System.out.println($(By.cssSelector("h1[class='css-1tjirrw e18vbajn0']")).getText());
    }
}
