package booking.pageObjects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SupportPageTest {
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
