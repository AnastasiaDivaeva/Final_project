package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.booking.HomePage;

public class HomePageTest {

    @AfterMethod
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
    @Test
    public void checkOutThePopUpsThatAppearWithPopularDestinations(){
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnSearchForDestination();
        Assert.assertTrue(homePage.findPopUpsWithPopularDestinations());
    }
    @Test
    public void changeWebsiteLanguage(){
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.chooseAnotherLanguage();
        String actualTitle= homePage.getTextAfterChangeLanguage();
        Assert.assertTrue(actualTitle.contains("Find your next stay"));
    }
}
