package StepDefinition;

import io.cucumber.java.ru.Когда;

public class TestSteps {
    @Когда("^Пользователь рандомно получает x$")
    public static float getX(){
        float x = (float) Math.random() * 8 + 1;
        System.out.println(x);
        return x;
    }

    @Когда("^Пользователь рандомно получает y$")
    public static float getY(){
        float y = (float) Math.random() * 8 + 1;
        System.out.println(y);
        return y;
    }
    @Когда("^Пользователь рандомно получает знак арифметического действия$")
    public  static String getSign(){
        String[] signs = new String[]{"+","-","/","*"};
        String sign = signs[(int) (Math.random() * 4)];
        System.out.println(sign);
        return sign;
    }
    @Когда("^Получить результат арифметического действия$")
    public  static float getResultOperation(){
        float x = getX();
        float y = getY();
        float result = 0;
        switch(getSign()){
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
        return result;
    }

    @Когда("^Получить результат$")
    public  static void getResult(){
        float result = getResultOperation();
        System.out.println(result);
    }


}
