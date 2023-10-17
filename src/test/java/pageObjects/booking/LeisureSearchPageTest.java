package pageObjects.booking;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeisureSearchPageTest {
    @Test
    public void checkSearchCarPickupLocation() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.clickLeisureSearch();
        LeisureSearchPage leisureSearchPage=new LeisureSearchPage();
        leisureSearchPage.clickOnLeisureSearch(location);
        Assert.assertTrue(leisureSearchPage.getNameCityLeisureSearch().contains(location));
    }
}
