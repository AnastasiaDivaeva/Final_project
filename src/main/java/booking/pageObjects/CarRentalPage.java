package booking.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

public class CarRentalPage {
    @Step("Select a car rental city")
    public void sendPickupLocation(String location) {
        SelenideElement cityInputElement = $x("//div[@data-testid='sbc-fl-text-input__container']");
        actions().moveToElement(cityInputElement).click(cityInputElement).perform();
        SelenideElement input = $x("//input[@id='searchbox-toolbox-fts-pickup']").shouldBe(Condition.interactable);
        actions().sendKeys(input, location).perform();

        ElementsCollection locationSuggestions = $$x("//div[@data-testid='suggestion']");
        actions().moveToElement(locationSuggestions.first()).click(locationSuggestions.first()).perform();
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        SelenideElement submitButton = $x("//button[@data-testid='searchbox-toolbox-submit-button']");
        actions().moveToElement(submitButton).click(submitButton).perform();
    }

    @Step("Get result search title")
    public String getResultSearchTitle() {
        return $x("//h1[@data-testid='page-title']").shouldBe(Condition.visible, Duration.ofSeconds(20)).getText();
    }

    @Step("Click on the lowest price")
    public void clickOnLowestPrice() {
        $x("//label[contains(@for, ':-PRICE')]").shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
    }

    @Step("Get a car search result at the lowest price")
    public ElementsCollection getResultSearchCarsForLowestPrice() {
        return $$x("//div[@class='SM_7d1e8d72 SM_2fdb9657']");
    }
}
