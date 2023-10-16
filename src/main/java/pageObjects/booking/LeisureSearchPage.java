package pageObjects.booking;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class LeisureSearchPage {
    public void clickOnLeisureSearch(String location) {
        $x(" //input[@name='query']").setValue(location);
        $x("//a[@data-testid='search-bar-result']").click();
        $x("//button[@type='submit']").click();
    }

    public String getNameCityLeisureSearch() {
        return $x("//h1[@class='af8fbdf136 css-1uk1gs8']").shouldBe(Condition.visible).getText();
    }

}
