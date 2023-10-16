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
    public void chooseFilterLowestPropertyEvaluation(){
        $x("//button[@data-testid='sorters-dropdown-trigger']").click();
        $x("//button[@data-id='class_asc']").click();
    }
    public ElementsCollection getResultAfterChooseFilter(){
        return $$x("//div[@class='a3b8729ab1 d86cee9b25']").shouldBe();
    }
    public void chooseHotel(){
        $x("//div[text()='Potocki Apartments']").click();
    }

}

