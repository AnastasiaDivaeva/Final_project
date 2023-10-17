package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    public String getResultSearchTitle() {
        return $x(" //h1[@aria-live='assertive']").getText();
    }

    public ElementsCollection getResultSearchThatHotelsSortedByLocation() {
        return $$x("//div[@data-testid='property-card']//span[@data-testid='address']");
    }

    public void chooseFilterLowestPropertyEvaluation() {
        $x("//button[@data-testid='sorters-dropdown-trigger']").click();
        $x("//button[@data-id='class_asc']").click();
    }

    public ElementsCollection getResultAfterChooseFilter() {
        return $$x("//div[@class='a3b8729ab1 d86cee9b25']").shouldBe();
    }

    public void chooseHotel() {
        $x("//div[@data-testid='title']").shouldBe(Condition.visible).click();
    }
    public void clickOnSaveButton(){
        $x("//span[@data-testid='wishlist-icon']").click();
    }
    public boolean  itemHasBeenAddedFavorites(){
        return $x("//div[@data-testid='wishlist-popover-content']").shouldBe(Condition.visible).isDisplayed();
    }
    public void clickOnCard(){
        $x("//div[@class='b546c9ed2b']//button[@type='button']").click();
    }
    public boolean cardHasOpen() {
        return  $x("//input[@type='search']").shouldBe(Condition.visible).isDisplayed();
    }

}

