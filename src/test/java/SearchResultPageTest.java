import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPageTest {
    @Test
    public void checkThatTitleContainsNameCityFromSearching() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closePopUPData();
        SearchResultPage searchResultPage = new SearchResultPage();
        String searchResultTitleValue = searchResultPage.getResultSearchTitle();
        Assert.assertTrue(searchResultTitleValue.contains(city));

    }

    @Test
    public void checkThatDisplaysHotelsThatMachTheCity() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closePopUPData();
        SearchResultPage searchResultPage = new SearchResultPage();
        ElementsCollection addressElements = searchResultPage.getResultSearchThatHotelsSortedByLocation();
        for (SelenideElement hotelAddress : addressElements) {
            String addressText = hotelAddress.getText();
            Assert.assertTrue(addressText.contains(city));
        }
    }

    @Test
    public void checkFilteringAtLowestPrice() {
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closePopUPData();
        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.chooseFilterLowestPropertyEvaluation();
        ElementsCollection setEstimatesAfterFilter = searchResultPage.getResultAfterChooseFilter();
        List<Double> estimates = new ArrayList<>();
        for (SelenideElement evaluation : setEstimatesAfterFilter) {
            String evaluationText = evaluation.getText().replace(",", ".");
            estimates.add(Double.parseDouble(evaluationText));
        }
        for (int i = 1; i < estimates.size(); i++) {
            Assert.assertTrue(estimates.get(i - 1) <= estimates.get(i));
        }
    }
}

