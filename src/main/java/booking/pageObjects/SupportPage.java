package booking.pageObjects;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class SupportPage {
    public boolean supportPageOpens(){
        return $x("//div[@class='c82ae084fa']").shouldBe(Condition.visible).isDisplayed();
    }
    public void clickOnContinueWithoutAccount(){
        $x("//button[@type='button']//span[@class='e4adce92df']").shouldBe(Condition.visible).click();
    }

}
