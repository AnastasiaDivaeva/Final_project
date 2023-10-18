package pageObjects.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPageTest {

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test(description = "Make sure that the hotels match the city you are searching for")
    @Description("Test description: verify name contains the city from the search")
    public void checkThatTitleContainsNameCityFromSearching() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closeCalendar();
        SearchResultPage searchResultPage = new SearchResultPage();
        String searchResultTitleValue = searchResultPage.getResultSearchTitle();
        Assert.assertTrue(searchResultTitleValue.contains(city));
    }

    @Test(description = "Make sure you see hotels that match your search city")
    @Description("Test description: check that the hotel name contains the city from the search")
    public void checkThatDisplaysHotelsThatMatchTheCity() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closeCalendar();
        SearchResultPage searchResultPage = new SearchResultPage();
        ElementsCollection addressElements = searchResultPage.getResultSearchThatHotelsSortedByLocation();
        for (SelenideElement hotelAddress : addressElements) {
            String addressText = hotelAddress.getText();
            Assert.assertTrue(addressText.contains(city));
        }
    }

    @Test(description = "check if the map is open")
    @Description("Test description: check that the map is opening")
    public void checkThatMapIsOpened() {
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
        searchResultPage.clickOnMap();

        Assert.assertTrue(searchResultPage.mapHasOpen());
    }

    @Test(description = "Check the wish list")
    @Description("Test description: check what hotels can be saved to your wish list")
    public void checkWishList() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.logInToTheSite("leraaa@gmail.com", "123456789Er");
        homePage.searchCityAfterLogin(city);
        homePage.setDateInSearchBarAfterLogin(
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(3));
        homePage.clickSearchButtonAfterLogin();
        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.clickOnSaveButton();

        Assert.assertTrue(searchResultPage.itemHasBeenAddedFavorites());
    }

    @Test(description = "Check filtering at the lowest price")
    @Description("Test description: check the filtering of hotels at the lowest price")
    public void sortHotelsByLowestPrice() {
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
        searchResultPage.chooseSortByPriceAsc();
        ElementsCollection setEstimatesAfterFilter = searchResultPage.getPrices();
        List<Integer> pricesAscOrder = setEstimatesAfterFilter.stream()
                .map(priceText -> priceText.getText()
                        .replaceAll("UAH", "")
                        .replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 1; i < pricesAscOrder.size(); i++) {
            Assert.assertTrue(pricesAscOrder.get(i - 1) <= pricesAscOrder.get(i));
        }
    }
}

