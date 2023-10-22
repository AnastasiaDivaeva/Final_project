package booking.pageObjects;

import booking.utils.StringUtils;
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

import static booking.utils.StringUtils.EMPTY_STRING;
import static booking.utils.StringUtils.UAH;
import static booking.utils.StringUtils.WHITE_SPACE;


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
        homePage.setDateInSearchBar(
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(3));
        homePage.clickSearchButton();
        SearchResultPage searchResultPage = new SearchResultPage();
        ElementsCollection addressElements = searchResultPage.getResultSearchThatHotelsSortedByLocation();
        for (SelenideElement hotelAddress : addressElements) {
            String addressText = hotelAddress.getText();
            Assert.assertTrue(addressText.contains(city),
                    "Found city '" + addressText + "' does not match to search criteria: '" + city + "'");
        }
    }

    @Test(description = "Check if the map is open")
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

        Assert.assertTrue(searchResultPage.isMapOpened());
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
        searchResultPage.chooseSortByPriceAscAndWaitWhilePricesUpdated();
        ElementsCollection setEstimatesAfterFilter = searchResultPage.getPrices();
        List<Integer> pricesAscOrder = setEstimatesAfterFilter.stream()
                .map(priceText -> priceText.getText()
                        .replaceAll(UAH, EMPTY_STRING)
                        .replaceAll(WHITE_SPACE, EMPTY_STRING))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 1; i < pricesAscOrder.size(); i++) {
            Assert.assertTrue(pricesAscOrder.get(i - 1) <= pricesAscOrder.get(i));
        }
    }

    @Test(description = "Check that prices change after choosing currency")
    @Description("Test description:check that all prices have changed for the selected currency")
    public void checkThatPricesChangeAfterChoosingCurrency() {
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
        List<String> previousPrices = searchResultPage.getElementsTextList(searchResultPage.getPrices());
        homePage.selectYourCurrency();
        ElementsCollection pricesAfterCurrencyChanging = searchResultPage.getPricesAfterPricesUpdated(previousPrices);
        List<String> currenciesList = pricesAfterCurrencyChanging.stream()
                .map(priceText -> priceText.getText()
                        .replaceAll("[0-9]", EMPTY_STRING)
                        .replaceAll(WHITE_SPACE, EMPTY_STRING))
                .collect(Collectors.toList());

        currenciesList.forEach(currencyValue -> Assert.assertEquals(currencyValue, StringUtils.EURO));
    }
}

