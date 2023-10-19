package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegistrationPageTest {

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }
    @Test(description = "Register a new user")
    @Description("Test description: to check that we can create a new user on the site")
    public void registrationNewUser() {
        String randomEmail = UUID.randomUUID() + "@gmail.com";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.signUpToTheSite(randomEmail, "123456789Er");
        Assert.assertTrue(registrationPage.isDisplayedHeaderProfile());
    }

    @Test(description = "Checking registration with an incorrect email address")
    @Description("Test description: check if the message appears when the login is entered incorrectly ")
    public void verificationOfRegistrationWithAnIncorrectEmailAddress() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.enterIncorrectlyLogin();
        Assert.assertTrue(registrationPage.notificationIncorrectLogin());
    }

    @Test(description = "Checking registration with an incorrect password")
    @Description("Test description: check if the message appears when the password is entered incorrectly")
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

    @Test(description = "Checking registration with an incorrect password confirmation")
    @Description("Test description: check if the message about incorrect password confirmation appears")
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
}
