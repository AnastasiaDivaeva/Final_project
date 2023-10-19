package booking.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CarRentalPageTest {

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    @Test(description = "Check search location on car rental page")
    @Description("Test description: check that the search on the car rental page works correctly")
    public void checkSearchLocation() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickCarRentalButton();
        CarRentalPage carRentalPage = new CarRentalPage();
        carRentalPage.sendPickupLocation(location);
        carRentalPage.clickSearchButton();
        String actualSearchTitle = carRentalPage.getResultSearchTitle();
        Assert.assertTrue(actualSearchTitle.contains(location));
    }

    @Test(description = "Check the search for cars at the lowest price")
    @Description("Test description: check that prices are displayed in ascending order")
    public void checkSearchCarsForLowestPrice() {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickCarRentalButton();
        CarRentalPage carRentalPage = new CarRentalPage();
        carRentalPage.sendPickupLocation(location);

        carRentalPage.clickSearchButton();
        carRentalPage.clickOnLowestPrice();
        ElementsCollection pricesAfterFilter = carRentalPage.getResultSearchCarsForLowestPrice();
        List<Double> prices = new ArrayList<>();
        for (SelenideElement price : pricesAfterFilter) {
            String evaluationPrices = price.getText()
                    .replaceAll(",", ".")
                    .replaceAll("UAH", "")
                    .replaceAll(" ", "")
                    .replaceAll("грн", "");
            prices.add(Double.parseDouble(evaluationPrices));
        }
        for (int i = 1; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i - 1));
        }
    }
}
