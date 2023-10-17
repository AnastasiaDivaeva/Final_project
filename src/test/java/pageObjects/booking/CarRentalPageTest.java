package pageObjects.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
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
    public void checkSearchCarPickupLocation() throws InterruptedException {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.clickCarRentalButton();
        CarRentalPage carRentalPage = new CarRentalPage();
        carRentalPage.sendPickupLocation(location);

        carRentalPage.clickSearchButton();
        Thread.sleep(5000);
        String resultSearchTitle = carRentalPage.getResultSearchTitle();
        Assert.assertTrue(resultSearchTitle.contains(location));
    }

    @Test
    public void checkSearchCarsForLowestPrice() throws InterruptedException {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        homePage.clickCarRentalButton();
        CarRentalPage carRentalPage = new CarRentalPage();
        carRentalPage.sendPickupLocation(location);

        carRentalPage.clickSearchButton();
        Thread.sleep(5000);
        carRentalPage.clickOnLowestPrice();
        ElementsCollection pricesAfterFilter = carRentalPage.getResultSearchCarsForLowestPrice();
        List<Double> prices = new ArrayList<>();
        for (SelenideElement price : pricesAfterFilter) {
            String evaluationPrices = price.getText().replaceAll(",", ".").replaceAll("UAH", "").replaceAll("грн","");
            prices.add(Double.parseDouble(evaluationPrices));
            for (int i = 1; i < prices.size(); i++) {
                Assert.assertTrue(prices.get(i) >= prices.get(i - 1));
            }

        }
    }
}
