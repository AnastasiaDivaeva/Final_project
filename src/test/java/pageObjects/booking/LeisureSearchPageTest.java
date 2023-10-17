package pageObjects.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LeisureSearchPageTest {
    @Test
    public void checkSearchCarPickupLocation() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.clickLeisureSearch();
        LeisureSearchPage leisureSearchPage = new LeisureSearchPage();
        leisureSearchPage.clickOnLeisureSearch(location);
        Assert.assertTrue(leisureSearchPage.getNameCityLeisureSearch().contains(location));
    }

    @Test
    public void checkThatNameEntertainmentCorrect() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.clickLeisureSearch();
        LeisureSearchPage leisureSearchPage = new LeisureSearchPage();
        leisureSearchPage.setDate("2023-11-26", "2023-11-27");
        leisureSearchPage.clickOnLeisureSearch(location);
        String expected = leisureSearchPage.getTitleExpected();
        leisureSearchPage.clickOnEntertainment();
        String actual = leisureSearchPage.getTitleActual();
        Assert.assertEquals(actual,expected);

    }

    @Test
    public void checkSearchEntertainmentForLowestPrice() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.clickLeisureSearch();
        LeisureSearchPage leisureSearchPage = new LeisureSearchPage();
        leisureSearchPage.clickOnLeisureSearch(location);
        leisureSearchPage.clickOnLowestPrice();
        ElementsCollection pricesAfterFilter = leisureSearchPage.getResultSearchEntertainmentForLowestPrice();
        List<Double> prices = new ArrayList<>();
        for (SelenideElement price : pricesAfterFilter) {
            String evaluationPrices = price.getText().replaceAll(",", "").replaceAll("UAH", "");
            prices.add(Double.parseDouble(evaluationPrices));
            for (int i = 1; i < prices.size(); i++) {
                Assert.assertTrue(prices.get(i) >= prices.get(i - 1));
            }
        }
    }
}
