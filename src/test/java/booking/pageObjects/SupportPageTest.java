package booking.pageObjects;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SupportPageTest {
    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }
    @Test
    public void checkThatSupportPageOpens(){
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.clickOnSupportButton();
        SupportPage supportPage=new SupportPage();
        supportPage.clickOnContinueWithoutAccount();

        Assert.assertTrue(supportPage.supportPageOpens());
    }
}
