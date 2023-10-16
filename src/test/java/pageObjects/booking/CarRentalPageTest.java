package pageObjects.booking;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.booking.CarRentalPage;
import pageObjects.booking.HomePage;

public class CarRentalPageTest {

    @AfterMethod
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
    @Test
    public void checkSearchCarPickupLocation() throws InterruptedException {
        String location = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickCarRentalButton();
        CarRentalPage carRentalPage = new CarRentalPage();
        carRentalPage.sendPickupLocation(location);

        carRentalPage.clickSearchButton();
        Thread.sleep(5000);
        String resultSearchTitle = carRentalPage.getResultSearchTitle();
        Assert.assertTrue(resultSearchTitle.contains(location));
    }
}
