package booking.pageObjects;

import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;

public class RegistrationPage {
    private static final String LOGIN = "leraaaaaaSSS@gmail.com";
    private static final String PASSWORD = "123456789Qw";
    private static final String INCORRECTLY_LOGIN = "qwertt1";
    private static final String INCORRECTLY_PASSWORD = "qazwsx";


    @Step("Enter the correct login in the field")
    public void enterLoginInField() {
        checkElementVisibleAndEnabled("//input[@name='username']")
                .setValue(LOGIN).pressEnter();
    }

    @Step("Enter the correct password in the field")
    public void enterPasswordInField() {
        checkElementVisibleAndEnabled("//input[@name='new_password']").setValue(PASSWORD);
    }

    @Step("Enter your login incorrectly")
    public void enterIncorrectlyLogin() {
        checkElementVisibleAndEnabled("//input[@name='username']").setValue(INCORRECTLY_LOGIN).pressEnter();
    }

    @Step("Receive a message about an incorrect login")
    public boolean notificationIncorrectLogin() {
        return checkElementVisibleAndEnabled("//div[@id='username-note']").isDisplayed();
    }

    @Step("Enter an incorrect password")
    public void enterIncorrectlyPassword() {
        checkElementVisibleAndEnabled("//input[@name='new_password']").setValue(INCORRECTLY_PASSWORD).pressEnter();
    }

    @Step("Receive a message about an incorrect password")
    public boolean notificationIncorrectPassword() {
        return checkElementVisibleAndEnabled("//div[@id='new_password-note']").isDisplayed();
    }

    @Step("Enter incorrect password confirmation")
    public void enterIncorrectlyPasswordConfirmation() {
        checkElementVisibleAndEnabled("//input[@name='confirmed_password']")
                .setValue(INCORRECTLY_PASSWORD).pressEnter();
    }

    @Step("Notifications about incorrectly confirmed passwords")
    public boolean notificationIncorrectlyOfPasswords() {
        return checkElementVisibleAndEnabled("//div[@id='confirmed_password-note']").isDisplayed();
    }
}
