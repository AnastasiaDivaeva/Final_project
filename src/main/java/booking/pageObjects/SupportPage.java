package booking.pageObjects;

import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;

public class SupportPage {
    public boolean supportPageOpens(){
        return checkElementVisibleAndEnabled("//div[@class='c82ae084fa']").isDisplayed();
    }
    @Step("Click on continue without account")
    public void clickOnContinueWithoutAccount(){
        checkElementVisibleAndEnabled("//button[@type='button']//span[@class='e4adce92df']").click();
    }
}
