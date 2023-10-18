package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class UserProfilePage {
    @Step("Choose personal details")
    public void choosePersonalDetails() {
        $x("//li[@data-ga-label='Category: personal_details']").click();
    }

    @Step("Change user name")
    public void changeUsername(String newName, String newLastName) {
        $x("//div[@data-test-id='mysettings-row-name']").click();
        SelenideElement username = $x("//input[@name='first']").shouldBe(Condition.visible);
        username.doubleClick();
        username.sendKeys(newName);
        SelenideElement lastName = $x("//input[@name='last']").shouldBe(Condition.visible);
        lastName.doubleClick();
        lastName.sendKeys(newLastName);
        $x("//button[@data-test-id='mysettings-btn-save']").click();
        $x("//input[@autocomplete='given-name']").shouldBe(Condition.hidden);
    }

    @Step("Get text after changed user name")
    public String getTextAfterChangeUserName() {
        return $x("//div[@data-test-id='mysettings-row-name']//div[@class='comp-container__element']").getText();
    }
}
