import com.codeborne.selenide.Configuration;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeWork {
    private final SoftAssertions softAssert = new SoftAssertions();

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
        File pict = new File("src/test/resources/files/picture.jpeg");

        $(By.id("firstName")).setValue(firstname);
        $(By.id("lastName")).setValue(lastName);
        $(By.id("userEmail")).setValue(email);
        $(By.xpath("//label[text()='Male']")).click();
        $(By.id("userNumber")).scrollTo().setValue(mobileNumber);
        $(By.id("dateOfBirthInput")).click();
        $(By.className("react-datepicker__month-select")).selectOptionContainingText("February");
        $(By.className("react-datepicker__year-select")).selectOptionContainingText("1988");
        $(By.className("react-datepicker__day--027")).click();
        $(By.xpath("//label[text()='Sports']")).click();
        $(By.id("subjectsInput")).scrollTo().setValue(subject).pressEnter();
        $(By.id("uploadPicture")).uploadFile(pict);
        $(By.id("currentAddress")).setValue(address);
        $(By.id("state")).click();
        $(By.id("stateCity-wrapper")).$(byText(state)).click();
        $(By.id("city")).click();
        $(By.id("stateCity-wrapper")).$(byText(city)).click();
        $(By.id("submit")).click();

        softAssert.assertThat($(By.xpath("//td[text()='Student Name']/following-sibling::td")).getText()).contains(firstname + " " + lastName);
        softAssert.assertThat($(By.xpath("//td[text()='Student Email']/following-sibling::td")).getText()).contains(email);
        softAssert.assertThat($(By.xpath("//td[text()='Gender']/following-sibling::td")).getText()).contains("Male");
        softAssert.assertThat($(By.xpath("//td[text()='Mobile']/following-sibling::td")).getText()).contains(mobileNumber);
        softAssert.assertThat($(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).getText()).contains(27 + " February," + 1988);
        softAssert.assertThat($(By.xpath("//td[text()='Subjects']/following-sibling::td")).getText()).contains("Maths");
        softAssert.assertThat($(By.xpath("//td[text()='Hobbies']/following-sibling::td")).getText()).contains("Sports");
        softAssert.assertThat($(By.xpath("//td[text()='Picture']/following-sibling::td")).getText()).contains(pict.getName());
        softAssert.assertThat($(By.xpath("//td[text()='State and City']/following-sibling::td")).getText()).contains(state + " " + city);
    }
}
