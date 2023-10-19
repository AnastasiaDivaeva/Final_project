package booking.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    @Step("Get the title of the search result")
    public String getResultSearchTitle() {
        return $x(" //h1[@aria-live='assertive']").shouldBe(Condition.visible).getText();
    }

    @Step("Get search results in which hotels are sorted by location")
    public ElementsCollection getResultSearchThatHotelsSortedByLocation() {
        return $$x("//div[@data-testid='property-card']//span[@data-testid='address']");
    }

    @Step("Choose a hotel")
    public void chooseHotel() {
        $x("//div[@data-testid='title']").shouldBe(Condition.visible).click();
    }

    @Step("Click on the save button")
    public void clickOnSaveButton() {
        $x("//span[@data-testid='wishlist-icon']").shouldBe(Condition.visible).click();
    }

    @Step("The item has been added to favorites")
    public boolean itemHasBeenAddedFavorites() {
        return $x("//div[@data-testid='wishlist-popover-content']").shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Click on the map")
    public void clickOnMap() {
        $x("//div[@class='b546c9ed2b']//button[@type='button']").shouldBe(Condition.visible).click();
    }

    @Step("The map opened")
    public boolean mapHasOpen() {
        return $x("//input[@type='search']").shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Select sort by price in ascending order")
    public void chooseSortByPriceAsc() {
        $x("//button[@data-testid='sorters-dropdown-trigger']").shouldBe(Condition.visible).click();

        List<String> pricesBeforeRedrawn = getPrices().stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        $x("//button[@data-id='price']").shouldBe(Condition.visible).click();

        waitWhileElementsRedrawn("//span[@data-testid='price-and-discounted-price']", pricesBeforeRedrawn);
    }

    @Step("Get the prices")
    public ElementsCollection getPrices() {
        return $$x("//span[@data-testid='price-and-discounted-price']");
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
}


