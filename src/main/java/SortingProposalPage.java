import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SortingProposalPage {

    public void setCityInSearchBar(String city) {
        $x("//div[@data-testid='destination-container']").click();
        $x("//input[@class='eb46370fe1' and @name='ss']").setValue(city);
    }

    public void setDateInSearchBar(String startDate, String endDate) {
        $x("//button[@data-testid='date-display-field-start']").click();
        $x("//span[@data-date='" + startDate + "']").click();
        $x("//span[@data-date='" + endDate + "']").click();
    }

    public void clickOnSubmitSearchButton() {
        $x("//button[@type='submit']").click();
    }

    public void chooseSortByPriceAsc() {
        $x("//button[@data-testid='sorters-dropdown-trigger']").click();

        List<String> pricesBeforeRedrawn = getPrices().stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        $x("//button[@data-id='price']").click();

        waitWhileElementsRedrawn("//span[@data-testid='price-and-discounted-price']", pricesBeforeRedrawn);
    }

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
