package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

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

    public void clickOnLowestPrice() {
        $x("//label[@for=':r1l:-lowest_price']").click();
    }

    public ElementsCollection getResultSearchEntertainmentForLowestPrice() {
        return $$x("//div[@class='e1eebb6a1e css-13pzcpe']");
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


}
