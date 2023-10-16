import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest {
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
