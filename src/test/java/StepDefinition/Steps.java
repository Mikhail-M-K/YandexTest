package StepDefinition;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;

public class Steps {
    private float x;
    private float y;
    private String sign;
    @Дано("^Получение числа x$")
    public void getX(){
        float x = (float) Math.random() * 8 + 1;
        System.out.println("Получено значение х: " + x);
        this.x = x;
    }

    @Дано("^Получение числа y$")
    public void getY(){
        float y = (float) Math.random() * 8 + 1;
        System.out.println("Получено значение y: " + y);
        this.y = y;
    }
    @Дано("^Получение знака арифметического действия$")
    public void getSign(){
        String[] signs = new String[]{"+","-","/","*"};
        String sign = signs[(int) (Math.random() * 4)];
        System.out.println("Получен арифметический знак действия: " + "\"" + sign + "\"");
        this.sign = sign;
    }
    @Тогда("^Результат арифметического действия$")
    public void getResultOperation(){
        float result = 0;
        switch(sign){
            case ("+"):
                result = x + y;
                break;
            case("-"):
                result = x - y;
                break;
            case("*"):
                result = x * y;
                break;
            case("/"):
                result = x / y;
                break;
        }
        System.out.println("Получение результата: " + result + "\n");
    }

    @Дано("Вывод текста {string}")
    public void выводТекста(String line) {
        System.out.println(line);
    }
}
