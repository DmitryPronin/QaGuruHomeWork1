import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.google.common.reflect.ClassPath;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.AssertionMode.SOFT;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.*;

@ExtendWith({SoftAssertsExtension.class})
public class HomeWork {

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.assertionMode = SOFT;
    }

    @Test
    @DisplayName("Успешное создание пользователя")
    void forTest(){
        open("/automation-practice-form");
        String firstname = "Dmitriy";
        String lastName = "Pronin";
        String email = "dmit.pronin@gmail.com";
        String mobileNumber = "9274454680";
        String address = "Tatarstan republic, Kazan";
        String state = "NCR";
        String city = "Delhi";
        String subject = "Math";

        $(id("firstName")).setValue(firstname);
        $(id("lastName")).setValue(lastName);
        $(id("userEmail")).setValue(email);
        $(byText("Male")).click();
        $(id("userNumber")).scrollTo().setValue(mobileNumber);
        $(id("dateOfBirthInput")).click();
        $(className("react-datepicker__month-select")).selectOptionContainingText("February");
        $(className("react-datepicker__year-select")).selectOptionContainingText("1988");
        $(className("react-datepicker__day--027")).click();
        $(xpath("//label[text()='Sports']")).click();
        $(id("subjectsInput")).scrollTo().setValue(subject).pressEnter();
        $(id("uploadPicture")).uploadFromClasspath("files/picture.jpeg");
        $(id("currentAddress")).setValue(address);
        $(id("state")).click();
        $(id("stateCity-wrapper")).$(byText(state)).click();
        $(id("city")).click();
        $(id("stateCity-wrapper")).$(byText(city)).click();
        $(id("submit")).click();

        $(xpath("//td[text()='Student Name']/following-sibling::td")).shouldHave(text(firstname + " " + lastName));
        $(xpath("//td[text()='Student Email']/following-sibling::td")).shouldHave(text(email));
        $(xpath("//td[text()='Gender']/following-sibling::td")).shouldHave(text("Male"));
        $(xpath("//td[text()='Mobile']/following-sibling::td")).shouldHave(text(mobileNumber));
        $(xpath("//td[text()='Date of Birth']/following-sibling::td")).shouldHave(text(27 + " February," + 1988));
        $(xpath("//td[text()='Subjects']/following-sibling::td")).shouldHave(text("Maths"));
        $(xpath("//td[text()='Hobbies']/following-sibling::td")).shouldHave(text("Sports"));
        $(xpath("//td[text()='Picture']/following-sibling::td")).shouldHave(text("picture.jpeg"));
        $(xpath("//td[text()='State and City']/following-sibling::td")).shouldHave(text(state + " " + city));
    }
}
