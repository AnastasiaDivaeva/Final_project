package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.booking.HomePage;
import pageObjects.booking.SearchResultPage;

public class DetailPageTest {

    @AfterMethod
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
    @Test
    public void checkThatPriceWhenBookingIsSame(){
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closePopUPData();
        SearchResultPage searchResultPage = new SearchResultPage();

    }

}
