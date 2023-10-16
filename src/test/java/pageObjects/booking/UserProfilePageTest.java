package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class UserProfilePageTest {

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test
    public void editUserProfileName() {
        String newFirstName = UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", "") + "Username";
        String newLastName = UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", "") + "LastName";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.logInToTheSite("leraaa@gmail.com", "123456789Er");
        homePage.clickOnHeaderProfile();
        homePage.chooseAccountManagement();
        UserProfilePage userProfilePage = new UserProfilePage();
        userProfilePage.choosePersonalDetails();
        userProfilePage.changeUsername(newFirstName, newLastName);
        String updatedName = newFirstName + " " + newLastName;
        String foundUpdatedUsername = userProfilePage.getTextAfterChangeUserName();

        Assert.assertTrue(foundUpdatedUsername.contains(updatedName));
    }
}
