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

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LeisureSearchPage {
    @Step("Enter the location where the entertainment")
    public void clickOnLeisureSearch(String location) {
        $x("//input[@name='query']").shouldBe(Condition.visible).setValue(location);
        $x("//a[@data-testid='search-bar-result']").shouldBe(Condition.visible).click();
        $x("//button[@type='submit']").shouldBe(Condition.visible).click();
    }

    @Step("Set the date in the search bar")
    public void setDate(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        $x("//div[@class='css-ck8kih']").shouldBe(Condition.visible).click();
        $x("//span[@data-date='" + startDateString + "']").shouldBe(Condition.visible).click();
        $x("//span[@data-date='" + endDateString + "']").shouldBe(Condition.visible).click();
    }

    @Step("Get name city")
    public String getNameCityLeisureSearch() {
        return $x("//h1[@class='af8fbdf136 css-1uk1gs8']").shouldBe(Condition.visible).getText();
    }

    @Step("Click on the lowest price and wait until the changes are applied")
    public void clickOnLowestPriceAndWaitForChangesApplied() {
        List<String> pricesBeforeFilter = getResultSearchEntertainmentForLowestPrice()
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        $x("//label[@for=':r1l:-lowest_price']").shouldBe(Condition.visible).click();

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

    public void clickOnEntertainment() {
        $x("//h4[@data-testid='card-title']").shouldBe(Condition.visible).click();
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
