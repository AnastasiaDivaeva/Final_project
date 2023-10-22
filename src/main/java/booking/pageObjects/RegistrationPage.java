package booking.pageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {
    private static final String LOGIN = "leraaaaaaSSS@gmail.com";
    private static final String PASSWORD = "123456789Qw";
    private static final String INCORRECTLY_LOGIN = "qwertt1";
    private static final String INCORRECTLY_PASSWORD = "qazwsx";


    @Step("Enter the correct login in the field")
    public void enterLoginInField() {
        retryIfIntercepted(() -> $x("//input[@name='username']")
                .shouldBe(Condition.visible)
                .setValue(LOGIN).pressEnter());
    }

    @Step("Enter the correct password in the field")
    public void enterPasswordInField() {
        retryIfIntercepted(() -> $x("//input[@name='new_password']")
                .shouldBe(Condition.visible)
                .setValue(PASSWORD));
    }

    @Step("Enter your login incorrectly")
    public void enterIncorrectlyLogin() {
        retryIfIntercepted(() -> $x("//input[@name='username']")
                .shouldBe(Condition.visible)
                .setValue(INCORRECTLY_LOGIN)
                .pressEnter());
    }

    @Step("Receive a message about an incorrect login")
    public boolean notificationIncorrectLogin() {
        return retryIfIntercepted(() -> $x("//div[@id='username-note']")
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Enter an incorrect password")
    public void enterIncorrectlyPassword() {
        retryIfIntercepted(() -> $x("//input[@name='new_password']")
                .shouldBe(Condition.visible)
                .setValue(INCORRECTLY_PASSWORD)
                .pressEnter());
    }

    @Step("Receive a message about an incorrect password")
    public boolean notificationIncorrectPassword() {
        return retryIfIntercepted(() -> $x("//div[@id='new_password-note']")
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Enter incorrect password confirmation")
    public void enterIncorrectlyPasswordConfirmation() {
        retryIfIntercepted(() -> $x("//input[@name='confirmed_password']")
                .shouldBe(Condition.visible)
                .setValue(INCORRECTLY_PASSWORD)
                .pressEnter());
    }

    @Step("Notifications about incorrectly confirmed passwords")
    public boolean notificationIncorrectlyOfPasswords() {
        return retryIfIntercepted(() -> $x("//div[@id='confirmed_password-note']")
                .shouldBe(Condition.visible)
                .isDisplayed());
    }
}
