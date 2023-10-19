package booking.pageObjects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class BookingPageTest {
    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test(description = "Availability of information about the apartment")
    @Description("Test description: check that the information about the apartment is specified when booking")
    public void availabilityInformationAboutApartment() {
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
        detailPage.selectionApartments();
        detailPage.clickOnSubmitButton();
        BookingPage bookingPage = new BookingPage();
        Assert.assertTrue(bookingPage.bookingInformation());
    }

    @Test(description = "Checking the display of the correct price when booking an apartment")
    @Description("Test description: check that the price when booking a hotel is the same as on the details page ")
    public void checkingTheDisplayOfCorrectPriceWhenBookingAnApartment() {
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
        detailPage.selectionApartments();
        int getPriceExpected = detailPage.getPrice();
        detailPage.clickOnSubmitButton();
        BookingPage bookingPage = new BookingPage();
        long getPriceActual = Math.round(bookingPage.getPrice());

        Assert.assertEquals(getPriceExpected, getPriceActual);
    }
}
