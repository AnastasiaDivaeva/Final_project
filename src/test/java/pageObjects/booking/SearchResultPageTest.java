package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPageTest {

    @AfterMethod
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
    @Test
    public void checkThatTitleContainsNameCityFromSearching() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closeCalendar();
        SearchResultPage searchResultPage = new SearchResultPage();
        String searchResultTitleValue = searchResultPage.getResultSearchTitle();
        Assert.assertTrue(searchResultTitleValue.contains(city));

    }

    @Test
    public void checkThatDisplaysHotelsThatMachTheCity() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
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
    @Test
    public void checkThatCardOpens() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.searchCity(city);
        homePage.setDateInSearchBar("2023-11-16", "2023-11-17");
        homePage.clickSearchButton();
        SearchResultPage searchResultPage=new SearchResultPage();
        searchResultPage.clickOnCard();

        Assert.assertTrue(searchResultPage.cardHasOpen());
    }
    @Test
    public void checkWishList(){
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.searchCity(city);
        homePage.setDateInSearchBar("2023-11-16", "2023-11-17");
        homePage.clickSearchButton();
        SearchResultPage searchResultPage=new SearchResultPage();
        searchResultPage.clickOnSaveButton();

        Assert.assertTrue(searchResultPage.itemHasBeenAddedFavorites());
    }


//    @Test
//    public void checkFilteringAtLowestPrice() {
//        String city = "Львів";
//        HomePage homePage = new HomePage();
//        homePage.openHomePage();
//        homePage.closePopUp();
//        homePage.searchCity(city);
//        homePage.clickSearchButton();
//        homePage.closePopUPData();
//        SearchResultPage searchResultPage = new SearchResultPage();
//        searchResultPage.chooseFilterLowestPropertyEvaluation();
//        ElementsCollection setEstimatesAfterFilter = searchResultPage.getResultAfterChooseFilter();
//        List<Double> estimates = new ArrayList<>();
//        for (SelenideElement evaluation : setEstimatesAfterFilter) {
//            String evaluationText = evaluation.getText().replace(",", ".");
//            estimates.add(Double.parseDouble(evaluationText));
//        }
//        for (int i = 1; i < estimates.size(); i++) {
//            Assert.assertTrue(estimates.get(i - 1) <= estimates.get(i));
//        }
//    }
}

