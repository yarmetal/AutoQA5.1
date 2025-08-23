package ru.netology.delivery.test;

import com.codeborne.selenide.Selectors;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDelivery {


    @Test
    void shouldRegisterCardDelivery() {


        var date1 = DataGenerator.generateDate(4);
        var date2 = DataGenerator.generateDate(7);

        open("http://localhost:9999");



            var validUser = DataGenerator.Registration.generateUser("ru");

            $("[data-test-id=city] input").setValue(validUser.getCity());
            $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id=date] input").setValue(date1);
            $("[data-test-id=name] input").setValue(validUser.getName());
            $("[data-test-id=phone] input").setValue(validUser.getPhone());
            $("[data-test-id=agreement]").click();
            $(byText("Запланировать")).click();
            $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
            $("[data-test-id='success-notification'] .notification__content")
                    .shouldHave(exactText("Встреча успешно запланирована на " + date1))
                    .shouldBe(visible);
            $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id=date] input").setValue(date2);
            $(byText("Запланировать")).click();
            $("[data-test-id='replan-notification'] .notification__content")
                    .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                    .shouldBe(visible);
            $("[data-test-id='replan-notification'] button").click();
            $("[data-test-id='success-notification'] .notification__content")
                    .shouldHave(exactText("Встреча успешно запланирована на " + date2))
                    .shouldBe(visible);


    }
}
