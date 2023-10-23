package booking.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    private static final String FIRST_HOTEL_FROM_LIST_BUTTON = "//div[@data-testid='title']";
    private static final String HOTEL_LOCATION_MAP_BUTTON = "//div[@class='b546c9ed2b']//button[@type='button']";
    private static final String SEARCH_RESULT_TITLE_VALUE = "//h1[@aria-live='assertive']";
    private static final String HOTElS_LOCATION_VALUE = "//div[@data-testid='property-card']//span[@data-testid='address']";
    private static final String SORTING_DROPDOWN_BUTTON = "//button[@data-testid='sorters-dropdown-trigger']";
    private static final String MAP_SECTION_INPUT = "//input[@type='search']";
    private static final String SORT_BY_LOWEST_PRICE_ASC_BUTTON = "//button[@data-id='price']";
    private static final String HOTELS_PRICES_VALUE = "//span[@data-testid='price-and-discounted-price']";

    @Step("Get the title of the search result")
    public String getSearchResultTitle() {
        return retryIfIntercepted(() -> $x(SEARCH_RESULT_TITLE_VALUE)
                .shouldBe(Condition.visible)
                .getText());
    }

    @Step("Get search results in which hotels are sorted by location")
    public ElementsCollection getHotelsLocationAfterSearchingByLocationApplied() {
        return $$x(HOTElS_LOCATION_VALUE);
    }

    @Step("Choose a hotel")
    public void chooseHotel() {
        retryIfIntercepted(() -> $x(FIRST_HOTEL_FROM_LIST_BUTTON)
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Click on the map")
    public void clickOnMap() {
        $x(HOTEL_LOCATION_MAP_BUTTON)
                .shouldBe(Condition.and("Clickable", Condition.visible, Condition.enabled))
                .click();
    }

    @Step("The map opened")
    public boolean isMapOpened() {
        return retryIfIntercepted(() -> $x(MAP_SECTION_INPUT)
                .shouldBe(Condition.visible, Duration.ofSeconds(20))
                .isDisplayed());
    }

    @Step("Select sort by price in ascending order")
    public void chooseSortByPriceAscAndWaitWhilePricesUpdated() {
        retryIfIntercepted(() -> $x(SORTING_DROPDOWN_BUTTON)
                .shouldBe(Condition.visible)
                .click());

        List<String> pricesBeforeRedrawn = getElementsTextList(getPrices());

        retryIfIntercepted(() -> $x(SORT_BY_LOWEST_PRICE_ASC_BUTTON)
                .shouldBe(Condition.visible)
                .click());

        waitWhileElementsRedrawn(HOTELS_PRICES_VALUE, pricesBeforeRedrawn);
    }

    @Step("Get the prices")
    public ElementsCollection getPrices() {
        return $$x(HOTELS_PRICES_VALUE);
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
        waitWhileElementsRedrawn(HOTELS_PRICES_VALUE, previousPrices);

        return $$x(HOTELS_PRICES_VALUE).shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}


