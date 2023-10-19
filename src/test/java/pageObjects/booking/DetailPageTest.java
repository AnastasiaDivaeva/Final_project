package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$x;

public class DetailPageTest {
    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test(description = "Check if there is information about the hotel's services")
    @Description("Test description: check if there is information about the hotel's services")
    public void checkIsInformationAboutHotelService() {
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
        DetailPage detailPage = new DetailPage();
        detailPage.clickOnFacilitiesInformationButton();

        Assert.assertTrue(detailPage.informationAboutServiceIsDisplayed());
    }

    @Test(description = "Check for comments on the page")
    @Description("Test description: check that a window with reviews opens ")
    public void checkForCommentsOnThePage() {
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
        DetailPage detailPage = new DetailPage();
        detailPage.clickOnReviewsButton();

        Assert.assertTrue(detailPage.windowWithReviewsOpened());
    }


}



