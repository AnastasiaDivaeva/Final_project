package booking.pageObjects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SupportPageTest {
    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }
    @Test(description = "Check that support page")
    @Description("Test description: check that the support page opens ")
    public void checkThatSupportPageOpens(){
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnSupportButton();
        SupportPage supportPage=new SupportPage();
        supportPage.clickOnContinueWithoutAccount();

        Assert.assertTrue(supportPage.supportPageIsOpened());
    }
}
