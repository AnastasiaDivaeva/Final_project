package booking.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class CarRentalPage {
    private static final String LOCATION_CONTAINER = "//div[@data-testid='sbc-fl-text-input__container']";
    private static final String LOCATION_INPUT = "//input[@id='searchbox-toolbox-fts-pickup']";
    private static final String LOCATION_SUGGESTION_DROPDOWN_ELEMENT = "//li[@id='searchbox-toolbox-fts-pickup-suggestion-0']";
    private static final String SEARCH_BUTTON = "//button[@data-testid='searchbox-toolbox-submit-button']";
    private static final String LOWEST_PRICE_SORT_BUTTON = "//label[contains(@for, ':-PRICE')]";
    private static final String RESULT_SEARCH_TITLE = "//h1[@data-testid='page-title']";
    private static final String SEARCH_RESULT_PRICES = "//div[@class='SM_7d1e8d72 SM_2fdb9657']";

    @Step("Select a car rental city")
    public void sendPickupLocation(String location) {
        retryIfIntercepted(() -> $x(LOCATION_CONTAINER)
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x(LOCATION_INPUT)
                .shouldBe(Condition.visible)
                .sendKeys(location));
        retryIfIntercepted(() -> $x(LOCATION_SUGGESTION_DROPDOWN_ELEMENT)
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        SelenideElement submitButton = $x(SEARCH_BUTTON);
        actions().moveToElement(submitButton).click(submitButton).perform();
    }

    @Step("Get result search title")
    public String getResultSearchTitle() {
        return $x(RESULT_SEARCH_TITLE).shouldBe(Condition.visible, Duration.ofSeconds(20)).getText();
    }

    @Step("Click on the lowest price")
    public void clickOnLowestPrice() {
        $x(LOWEST_PRICE_SORT_BUTTON).shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
    }

    @Step("Get a car search result at the lowest price")
    public ElementsCollection getSearchResultPrices() {
        return $$x(SEARCH_RESULT_PRICES);
    }
}
