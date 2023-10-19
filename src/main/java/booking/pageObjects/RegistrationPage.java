package booking.pageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {
    private static final String LOGIN = "leraaaaaaSSS@gmail.com";
    private static final String PASSWORD = "123456789Qw";
    private static final String INCORRECTLY_LOGIN = "qwertt1";
    private static final String INCORRECTLY_PASSWORD = "qazwsx";


    @Step("Enter the correct login in the field")
    public void enterLoginInField() {
        $x("//input[@name='username']").setValue(LOGIN).pressEnter();
    }

    @Step("Enter the correct password in the field")
    public void enterPasswordInField() {
        $x("//input[@name='new_password']").setValue(PASSWORD);
    }

    @Step("Enter your login incorrectly")
    public void enterIncorrectlyLogin() {
        $x("//input[@name='username']").setValue(INCORRECTLY_LOGIN).pressEnter();
    }

    @Step("Receive a message about an incorrect login")
    public boolean notificationIncorrectLogin() {
        return $x("//div[@id='username-note']").isDisplayed();
    }

    @Step("Enter an incorrect password")
    public void enterIncorrectlyPassword() {
        $x("//input[@name='new_password']").setValue(INCORRECTLY_PASSWORD).pressEnter();
    }

    @Step("Receive a message about an incorrect password")
    public boolean notificationIncorrectPassword() {
        return $x("//div[@id='new_password-note']").isDisplayed();
    }

    @Step("Enter incorrect password confirmation")
    public void enterIncorrectlyPasswordConfirmation() {
        $x("//input[@name='confirmed_password']").setValue(INCORRECTLY_PASSWORD).pressEnter();
    }

    @Step("Notifications about incorrectly confirmed passwords")
    public boolean notificationIncorrectlyOfPasswords() {
        return $x("//div[@id='confirmed_password-note']").isDisplayed();
    }

    @Step("Log in to the site")
    public void logInToTheSite(String login, String password) {
        $x("//a[@data-testid='header-sign-up-button']").shouldBe(Condition.visible).click();
        $x("//input[@name='username']").setValue(login).pressEnter();
        $x("//input[@name='password']").setValue(password);
        $x("//button[@type='submit']").click();
    }

    @Step("Registration on the website")
    public void signUpToTheSite(String login, String password) {
        $x("//a[@data-testid='header-sign-up-button']").shouldBe(Condition.visible).click();
        $x("//input[@name='username']").setValue(login).pressEnter();
        $x("//input[@name='new_password']").setValue(password);
        $x("//input[@name='confirmed_password']").setValue(password);
        $x("//button[@type='submit']").click();
    }

    @Step("The header profile is displayed")
    public boolean isDisplayedHeaderProfile() {
        return $x("//span[@class='bui-avatar-block__title']").shouldBe(Condition.exist, Duration.ofSeconds(10)).isDisplayed();
    }
}
