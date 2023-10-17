package pageObjects.booking;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {
    private static final String LOGIN = "leraaaaaaSSS@gmail.com";
    private static final String PASSWORD = "123456789Qw";
    private static final String INCORRECTLY_LOGIN = "qwertt1";
    private static final String INCORRECTLY_PASSWORD = "qazwsx";

    public void enterLogin() {
        $x("//input[@name='username']").setValue("sekach199855@gmail.com");
        $x("//button[@type='submit']").click();
    }

    public void enterPassword() {
        $x("//input[@name='password']").setValue("123456789Qw").pressEnter();
    }

    public void enterLoginInField() {
        $x("//input[@name='username']").setValue(LOGIN).pressEnter();
    }

    public void enterPasswordInField() {
        $x("//input[@name='new_password']").setValue(PASSWORD);
    }

    public void enterIncorrectlyLogin() {
        $x("//input[@name='username']").setValue(INCORRECTLY_LOGIN).pressEnter();
    }

    public boolean notificationIncorrectLogin() {
        return $x("//div[@id='username-note']").isDisplayed();
    }

    public void enterIncorrectlyPassword() {
        $x("//input[@name='new_password']").setValue(INCORRECTLY_PASSWORD).pressEnter();
    }

    public boolean notificationIncorrectPassword() {
        return $x("//div[@id='new_password-note']").isDisplayed();
    }

    public void enterIncorrectlyPasswordConfirmation() {
        $x("//input[@name='confirmed_password']").setValue(INCORRECTLY_PASSWORD).pressEnter();
    }

    public boolean notificationIncorrectlyOfPasswords() {
        return $x("//div[@id='confirmed_password-note']").isDisplayed();
    }

    public void logInToTheSite(String login, String password) {
        $x("//a[@data-testid='header-sign-up-button']").shouldBe(Condition.visible).click();
        $x("//input[@name='username']").setValue(login).pressEnter();
        $x("//input[@name='password']").setValue(password);
        $x("//button[@type='submit']").click();
    }

    public void signUpToTheSite(String login, String password) {
        $x("//a[@data-testid='header-sign-up-button']").click();
        $x("//input[@name='username']").setValue(login).pressEnter();
        $x("//input[@name='new_password']").setValue(password);
        $x("//input[@name='confirmed_password']").setValue(password);
        $x("//button[@type='submit']").click();
    }

    public boolean isDisplayedHeaderProfile() {
        return $x("//span[@class='bui-avatar-block__title']").shouldBe(Condition.exist).isDisplayed();
    }
}
