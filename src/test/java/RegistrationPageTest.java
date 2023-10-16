import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationPageTest {


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
}
