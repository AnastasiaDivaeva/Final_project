package booking.pageObjects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class HomePageTest {

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test(description = "Check that when you click on the route search, a pop-up with popular destinations appears ")
    @Description("Test description: check if a pop-up window with popular destinations appears when you click on search ")
    public void checkOutThePopUpsThatAppearWithPopularDestinations() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnSearchForDestination();
        Assert.assertTrue(homePage.findPopUpsWithPopularDestinations());
    }

    @Test(description = "Change the language of the site")
    @Description("Test description: check that when you click on the change language button, the language on the site changes")
    public void changeWebsiteLanguage() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.chooseAnotherLanguage();
        String actualTitle = homePage.getTextAfterChangeLanguage();
        Assert.assertEquals(actualTitle, "Find your next stay");
    }
}
