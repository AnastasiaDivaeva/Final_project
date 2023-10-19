package booking.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeisureSearchPageTest {
    @Test(description = "Check search attractions ")
    @Description("Test description: check that the name of the city search matches the name of the city on the page")
    public void checkSearchAttractions() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickLeisure();
        LeisureSearchPage leisureSearchPage = new LeisureSearchPage();
        leisureSearchPage.clickOnLeisureSearch(location);
        Assert.assertTrue(leisureSearchPage.getNameCityLeisureSearch().contains(location));
    }

    @Test(description = "check the name of the entertainment is correct")
    @Description("Test description: make sure the name of the entertainment is correct")
    public void checkThatNameEntertainmentCorrect() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickLeisure();
        LeisureSearchPage leisureSearchPage = new LeisureSearchPage();
        leisureSearchPage.setDate(
                LocalDate.now().plusWeeks(2),
                LocalDate.now().plusWeeks(2).plusDays(4));
        leisureSearchPage.clickOnLeisureSearch(location);
        String expected = leisureSearchPage.getTitleExpected();
        leisureSearchPage.clickOnEntertainment();
        String actual = leisureSearchPage.getTitleActual();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Check the search for entertainment at the lowest price")
    @Description("Test description: check that prices are displayed in ascending order")
    public void checkSearchEntertainmentForLowestPrice() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickLeisure();
        LeisureSearchPage leisureSearchPage = new LeisureSearchPage();
        leisureSearchPage.clickOnLeisureSearch(location);
        leisureSearchPage.clickOnLowestPriceAndWaitForChangesApplied();
        ElementsCollection pricesAfterFilter = leisureSearchPage.getResultSearchEntertainmentForLowestPrice();
        List<Double> prices = new ArrayList<>();
        for (SelenideElement price : pricesAfterFilter) {
            String evaluationPrices = price.getText()
                    .replaceAll(",", "")
                    .replaceAll("UAH", "");
            prices.add(Double.parseDouble(evaluationPrices));
        }
        for (int i = 1; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i - 1));
        }
    }
}
