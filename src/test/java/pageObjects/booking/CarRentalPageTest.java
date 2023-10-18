package pageObjects.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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

    @Test
    public void checkSearchCarPickupLocation() {
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

    @Test
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
                    .replaceAll("грн","");
            prices.add(Double.parseDouble(evaluationPrices));
        }
        for (int i = 1; i < prices.size(); i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i - 1));
        }
    }
}
