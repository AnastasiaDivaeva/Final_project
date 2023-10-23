package booking.pageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {
    private static final String LOGIN = "leraaaaaaSSS@gmail.com";
    private static final String PASSWORD = "123456789Qw";
    private static final String INCORRECT_LOGIN = "qwertt1";
    private static final String INCORRECT_PASSWORD = "qazwsx";
    private static final String LOGIN_INPUT = "//input[@name='username']";
    private static final String PASSWORD_INPUT = "//input[@name='new_password']";
    private static final String INCORRECT_LOGIN_INPUT = "//input[@name='username']";
    private static final String INCORRECT_PASSWORD_INPUT = "//input[@name='new_password']";
    private static final String INCORRECT_PASSWORD_CONFIRMATION_INPUT = "//input[@name='confirmed_password']";
    private static final String INCORRECT_PASSWORD_NOTIFICATION = "//div[@id='new_password-note']";
    private static final String INCORRECT_CONFIRMED_PASSWORD_NOTIFICATION = "//div[@id='confirmed_password-note']";
    private static final String INCORRECT_USERNAME_NOTIFICATION = "//div[@id='username-note']";


    @Step("Enter the correct login in the field")
    public void enterLoginInField() {
        retryIfIntercepted(() -> $x(LOGIN_INPUT)
                .shouldBe(Condition.visible)
                .setValue(LOGIN).pressEnter());
    }

    @Step("Enter the correct password in the field")
    public void enterPasswordInField() {
        retryIfIntercepted(() -> $x(PASSWORD_INPUT)
                .shouldBe(Condition.visible)
                .setValue(PASSWORD));
    }

    @Step("Enter your login incorrectly")
    public void enterIncorrectlyLogin() {
        retryIfIntercepted(() -> $x(INCORRECT_LOGIN_INPUT)
                .shouldBe(Condition.visible)
                .setValue(INCORRECT_LOGIN)
                .pressEnter());
    }

    @Step("Receive a message about an incorrect login")
    public boolean notificationAboutLoginIncorrectness() {
        return retryIfIntercepted(() -> $x(INCORRECT_USERNAME_NOTIFICATION)
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Enter an incorrect password")
    public void enterIncorrectlyPassword() {
        retryIfIntercepted(() -> $x(INCORRECT_PASSWORD_INPUT)
                .shouldBe(Condition.visible)
                .setValue(INCORRECT_PASSWORD)
                .pressEnter());
    }

    @Step("Receive a message about an incorrect password")
    public boolean notificationAboutPasswordIncorrectness() {
        return retryIfIntercepted(() -> $x(INCORRECT_PASSWORD_NOTIFICATION)
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Enter incorrect password confirmation")
    public void enterIncorrectlyPasswordConfirmation() {
        retryIfIntercepted(() -> $x(INCORRECT_PASSWORD_CONFIRMATION_INPUT)
                .shouldBe(Condition.visible)
                .setValue(INCORRECT_PASSWORD)
                .pressEnter());
    }

    @Step("Notifications about incorrectly confirmed passwords")
    public boolean notificationIncorrectlyOfPasswords() {
        return retryIfIntercepted(() -> $x(INCORRECT_CONFIRMED_PASSWORD_NOTIFICATION)
                .shouldBe(Condition.visible)
                .isDisplayed());
    }
}
