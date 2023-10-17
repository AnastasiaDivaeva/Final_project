package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

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
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.searchCity(city);
        homePage.setDateInSearchBar("2023-11-16", "2023-11-16");
        homePage.clickSearchButton();
        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.chooseHotel();
       DetailPage detailPage=new DetailPage();
       detailPage.clickOnFacilitiesInformationButton();

        Assert.assertTrue(detailPage.informationAboutServiceIsDisplayed());
    }



}



