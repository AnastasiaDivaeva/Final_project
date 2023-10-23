package booking.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LeisureSearchPage {
    private static final String LOCATION_INPUT = "//input[@name='query']";
    private static final String LOCATION_INPUT_SUGGESTION = "//a[@data-testid='search-bar-result']";
    private static final String SUBMIT_BUTTON = "//button[@type='submit']";
    private static final String LOWEST_PRICE_SORT_BUTTON = "//label[@for=':r1l:-lowest_price']";
    private static final String ENTERTAINMENT_ITEM_BUTTON = "//h4[@data-testid='card-title']";

    @Step("Enter the location where the entertainment")
    public void clickOnLeisureSearch(String location) {
        retryIfIntercepted(() -> $x(LOCATION_INPUT)
                .shouldBe(Condition.visible)
                .setValue(location));
        retryIfIntercepted(() -> $x(LOCATION_INPUT_SUGGESTION)
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x(SUBMIT_BUTTON)
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Set the date in the search bar")
    public void setDate(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        retryIfIntercepted(() -> $x("//div[@class='css-ck8kih']")
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x("//span[@data-date='" + startDateString + "']")
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x("//span[@data-date='" + endDateString + "']")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Get name city")
    public String getNameCityLeisureSearch() {
        return retryIfIntercepted(() -> $x("//h1[@class='af8fbdf136 css-1uk1gs8']")
                .shouldBe(Condition.visible)
                .getText());
    }

    @Step("Click on the lowest price and wait until the changes are applied")
    public void clickOnLowestPriceAndWaitForChangesApplied() {
        List<String> pricesBeforeFilter = getResultSearchEntertainmentForLowestPrice()
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        retryIfIntercepted(() -> $x(LOWEST_PRICE_SORT_BUTTON)
                .shouldBe(Condition.visible)
                .click());

        waitForAnyElementIsChanged(pricesBeforeFilter, getResultSearchEntertainmentForLowestPrice());
    }

    @Step("Get an entertainment search result at the lowest price")
    public ElementsCollection getResultSearchEntertainmentForLowestPrice() {
        return $$x("//div[@class='e1eebb6a1e css-13pzcpe']")
                .shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
    }

    @Step("Get the name of the entertainment on the search page")
    public String getTitleExpected() {
        return $x("//h4[@data-testid='card-title']").shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
    }

    @Step("Choose entertainment")
    public void clickOnEntertainment() {
        retryIfIntercepted(() -> $x(ENTERTAINMENT_ITEM_BUTTON)
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Get the name of the entertainment")
    public String getTitleActual() {
        Selenide.switchTo().window(1);
        return $x("//h2[@class='af8fbdf136 css-1uk1gs8']").getText();
    }

    private void waitForAnyElementIsChanged(List<String> previousStates, ElementsCollection actualElements) {
        while (true) {
            for (int i = 0; i < previousStates.size(); i++) {
                String previousState = previousStates.get(i);
                String actualState = actualElements.get(i).getText();
                if (!previousState.equals(actualState)) {
                    return;
                }
            }
        }
    }
}
