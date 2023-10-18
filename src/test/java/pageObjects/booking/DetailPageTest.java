package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class DetailPageTest {
    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test
    public void checkIsInformationAboutHotelService(){
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.setDateInSearchBar(
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(3));
        homePage.clickSearchButton();
        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.chooseHotel();
       DetailPage detailPage=new DetailPage();
       detailPage.clickOnFacilitiesInformationButton();

        Assert.assertTrue(detailPage.informationAboutServiceIsDisplayed());
    }



}



