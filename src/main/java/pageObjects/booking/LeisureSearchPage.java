package pageObjects.booking;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LeisureSearchPage {
    public void clickOnLeisureSearch(String location) {
        $x(" //input[@name='query']").setValue(location);
        $x("//a[@data-testid='search-bar-result']").click();
        $x("//button[@type='submit']").click();
    }

    public void setDate(String startDate, String endDate) {
        $x("//div[@class='css-ck8kih']").click();
        $x("//span[@data-date='" + startDate + "']").click();
        $x("//span[@data-date='" + endDate + "']").click();
    }

    public String getNameCityLeisureSearch() {
        return $x("//h1[@class='af8fbdf136 css-1uk1gs8']").shouldBe(Condition.visible).getText();
    }

    public void clickOnLowestPriceAndWaitForChangesApplied() {
        List<String> pricesBeforeFilter = getResultSearchEntertainmentForLowestPrice()
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        $x("//label[@for=':r1l:-lowest_price']").click();

        waitForAnyElementIsChanged(pricesBeforeFilter, getResultSearchEntertainmentForLowestPrice());
    }

    public ElementsCollection getResultSearchEntertainmentForLowestPrice() {
        return $$x("//div[@class='e1eebb6a1e css-13pzcpe']")
                .shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
    }
    public String getTitleExpected(){
        return $x("//h4[@data-testid='card-title']").getText();
    }
    public void clickOnEntertainment(){
        $x("//h4[@data-testid='card-title']").click();
    }
    public String getTitleActual(){
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
