package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class UserProfilePage {

    public void choosePersonalDetails() {
        $x("//li[@data-ga-label='Category: personal_details']").click();
    }

    public void changeUsername(String newName, String newLastName) {
        $x("//button[@data-ga-label='Edit section: name']").click();
        SelenideElement username = $x("//input[@autocomplete='given-name']").shouldBe(Condition.visible);
        username.doubleClick();
        username.sendKeys(newName);
        SelenideElement lastName = $x("//input[@autocomplete='family-name']").shouldBe(Condition.visible);
        lastName.doubleClick();
        lastName.sendKeys(newLastName);
        $x("//button[@data-test-id='mysettings-btn-save']").click();
        $x("//input[@autocomplete='given-name']").shouldBe(Condition.hidden);
    }

    public String getTextAfterChangeUserName() {
        return $x("//div[@data-test-id='mysettings-row-name']//div[@class='comp-container__element']").getText();
    }
}
