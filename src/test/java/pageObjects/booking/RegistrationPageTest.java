package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegistrationPageTest {

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test
    public void verificationOfRegistrationWithAnIncorrectEmailAddress() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.enterIncorrectlyLogin();
        Assert.assertTrue(registrationPage.notificationIncorrectLogin());
    }

    @Test
    public void verificationOfRegistrationWithAnIncorrectPassword() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.enterLoginInField();
        registrationPage.enterIncorrectlyPassword();
        Assert.assertTrue(registrationPage.notificationIncorrectPassword());
    }

    @Test
    public void verificationOfRegistrationWithAnIncorrectPasswordConfirmation() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.enterLoginInField();
        registrationPage.enterPasswordInField();
        registrationPage.enterIncorrectlyPasswordConfirmation();
        Assert.assertTrue(registrationPage.notificationIncorrectlyOfPasswords());
    }

    @Test
    public void registrationNewUser() {
        String randomEmail = UUID.randomUUID() + "@gmail.com";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.signUpToTheSite(randomEmail, "123456789Er");
        Assert.assertTrue(registrationPage.isDisplayedHeaderProfile());
    }
}
