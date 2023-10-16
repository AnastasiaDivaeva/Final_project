package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.booking.HomePage;
import pageObjects.booking.RegistrationPage;

public class UserProfilePageTest {

    @AfterMethod
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
    @Test
    public void editUserProfileName(){
        HomePage homePage=new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnLoginButton();
        RegistrationPage registrationPage=new RegistrationPage();
        registrationPage.enterLogin();
        registrationPage.enterPassword();
    }
}
