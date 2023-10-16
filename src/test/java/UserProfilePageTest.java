import org.testng.annotations.Test;

public class UserProfilePageTest {
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
