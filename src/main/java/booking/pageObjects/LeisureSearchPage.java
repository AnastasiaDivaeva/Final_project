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

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LeisureSearchPage {
    @Step("Enter the location where the entertainment")
    public void clickOnLeisureSearch(String location) {
        checkElementVisibleAndEnabled("//input[@name='query']").setValue(location);
        checkElementVisibleAndEnabled("//a[@data-testid='search-bar-result']").click();
        checkElementVisibleAndEnabled("//button[@type='submit']").click();
    }

    @Step("Set the date in the search bar")
    public void setDate(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        checkElementVisibleAndEnabled("//div[@class='css-ck8kih']").click();
        checkElementVisibleAndEnabled("//span[@data-date='" + startDateString + "']").click();
        checkElementVisibleAndEnabled("//span[@data-date='" + endDateString + "']").click();
    }

    @Step("Get name city")
    public String getNameCityLeisureSearch() {
        return checkElementVisibleAndEnabled("//h1[@class='af8fbdf136 css-1uk1gs8']").getText();
    }

    @Step("Click on the lowest price and wait until the changes are applied")
    public void clickOnLowestPriceAndWaitForChangesApplied() {
        List<String> pricesBeforeFilter = getResultSearchEntertainmentForLowestPrice()
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        checkElementVisibleAndEnabled("//label[@for=':r1l:-lowest_price']").click();

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
        checkElementVisibleAndEnabled("//h4[@data-testid='card-title']").click();
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
