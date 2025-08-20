package ru.netology.delivery.test;

import com.codeborne.selenide.Selectors;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDelivery {


    @Test
    void shouldRegisterCardDelivery() {


        var date1 = DataGenerator.generateDate(4);
        var date2 = DataGenerator.generateDate(7);

        open("http://localhost:9999");
        val user = DataGenerator.Registration.generateUser("ru");
        val name = user.getName();
        val phone = user.getPhone();
        val city = user.getCity();
        System.out.println(city);

        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input.input__control").sendKeys(
                Keys.chord(Keys.SHIFT, Keys.HOME) + Keys.DELETE);
        $("[data-test-id='date'] input.input__control").setValue(date1);
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(Selectors.byText("Запланировать")).click();
        $(Selectors.withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(Selectors.withText("Встреча успешно запланирована на " + date1));
//        ).shouldBe
//                (visible, Duration.ofSeconds(15));

//        $("[data-test-id='success-notification'].notification__content")
//                .shouldHave(exactText("Встреча успешно запланирована на " + date1))
//                .shouldBe(visible);

        $("[data-test-id='date'] input.input__control").sendKeys(
                Keys.chord(Keys.SHIFT, Keys.HOME) + Keys.DELETE);
        $("[data-test-id='date'] input.input__control").setValue(date2);
        $$("button").find(exactText("Запланировать")).click();
        $(Selectors.withText("У вас уже запланирована встреча на другую дату. Перепланировать?"));


//        $("[data-test-id='success-notification'].notification__content")
//                .shouldHave(text(("У вас уже запланирована встреча на другую дату. Перепланировать?")))
//                .shouldBe(visible);

        $("[data-test-id='replan-notification'] button").click();


//        $("[data-test-id='success-notification'].notification__content")
//                .shouldHave(exactText("Встреча успешно запланирована на " + date2))
//                .shouldBe(visible);


    }
}
