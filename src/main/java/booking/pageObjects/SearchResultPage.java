package booking.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    @Step("Get the title of the search result")
    public String getResultSearchTitle() {
        return checkElementVisibleAndEnabled(" //h1[@aria-live='assertive']").getText();
    }

    @Step("Get search results in which hotels are sorted by location")
    public ElementsCollection getResultSearchThatHotelsSortedByLocation() {
        return $$x("//div[@data-testid='property-card']//span[@data-testid='address']");
    }

    @Step("Choose a hotel")
    public void chooseHotel() {
        checkElementVisibleAndEnabled("//div[@data-testid='title']").click();
    }

    @Step("Click on the map")
    public void clickOnMap() {
        $x("//div[@class='b546c9ed2b']//button[@type='button']")
                .shouldBe(Condition.and("Clickable", Condition.visible, Condition.enabled))
                .click();
    }

    @Step("The map opened")
    public boolean isMapOpened() {
        return checkElementVisibleAndEnabled("//input[@type='search']").isDisplayed();
    }

    @Step("Select sort by price in ascending order")
    public void chooseSortByPriceAscAndWaitWhilePricesUpdated() {
        checkElementVisibleAndEnabled("//button[@data-testid='sorters-dropdown-trigger']").click();

        List<String> pricesBeforeRedrawn = getElementsTextList(getPrices());

        checkElementVisibleAndEnabled("//button[@data-id='price']").click();

        waitWhileElementsRedrawn("//span[@data-testid='price-and-discounted-price']", pricesBeforeRedrawn);
    }

    @Step("Get the prices")
    public ElementsCollection getPrices() {
        return $$x("//span[@data-testid='price-and-discounted-price']");
    }

    public List<String> getElementsTextList(ElementsCollection elements) {
        return elements.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    private void waitWhileElementsRedrawn(String newElementsXpath, List<String> oldElementsState) {
        while (true) {
            ElementsCollection newElements = $$x(newElementsXpath);
            for (int i = 0; i < oldElementsState.size(); i++) {
                String oldElementText = oldElementsState.get(i);
                String newElementText = newElements.get(i).getText();
                if (!oldElementText.equals(newElementText)) {
                    return;
                }
            }
        }
    }

    public ElementsCollection getPricesAfterPricesUpdated(List<String> previousPrices) {
        waitWhileElementsRedrawn("//span[@data-testid='price-and-discounted-price']", previousPrices);

        return $$x("//span[@data-testid='price-and-discounted-price']")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}


