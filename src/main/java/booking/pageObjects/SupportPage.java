package booking.pageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class SupportPage {
    private static final String CONTINUE_WITHOUT_ACCOUNT = "//button[@type='button']//span[@class='e4adce92df']";
    private static final String SUPPORT_PAGE_CONTAINER = "//div[@class='c82ae084fa']";

    public boolean supportPageIsOpened() {
        return retryIfIntercepted(() -> $x(SUPPORT_PAGE_CONTAINER)
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Click on continue without the account")
    public void clickOnContinueWithoutAccount() {
        retryIfIntercepted(() -> $x(CONTINUE_WITHOUT_ACCOUNT)
                .shouldBe(Condition.visible)
                .click());
    }
}
