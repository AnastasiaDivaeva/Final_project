package booking.pageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;
import static com.codeborne.selenide.Selenide.$x;

public class SupportPage {
    public boolean supportPageOpens(){
        return checkElementVisibleAndEnabled("//div[@class='c82ae084fa']").isDisplayed();
    }
    @Step("Click on continue without account")
    public void clickOnContinueWithoutAccount(){
        checkElementVisibleAndEnabled("//button[@type='button']//span[@class='e4adce92df']").click();
    }
}
