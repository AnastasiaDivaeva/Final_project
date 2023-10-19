package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DetailPage {
    @Step("Choose an apartment")
    public void selectionApartments() {
        Selenide.switchTo().window(1);
        $x("//select[contains(@class, 'hprt-nos-select')]").click();
        $x("//option[@value='1'][1]").click();
    }

    @Step("Click on submit button")
    public void clickOnSubmitButton() {
        $x("//button[@data-tooltip-class='submit_holder_button_tooltip']").click();
    }

    @Step("Get the expected price")
    public int getPrice() {
        String priceText = $x("//div[@class='hprt-reservation-total-price" +
                " bui-price-display__value prco-inline-block-maker-helper']").getText();
        String cleanPrice = priceText.replaceAll("UAH", "").replaceAll(" ", "");
        return Integer.parseInt(cleanPrice);
    }

    @Step("Click on the button Information about the object")
    public void clickOnFacilitiesInformationButton() {
        Selenide.switchTo().window(1);
        $x("//a[@data-scroll='a[name=HotelFacilities]']").click();
    }

    public boolean informationAboutServiceIsDisplayed() {
        return $x("//div[@data-testid='property-section--content']" +
                "//div[@data-testid='property-most-popular-facilities-wrapper']")
                .isDisplayed();
    }

    @Step("Click on reviews button")
    public void clickOnReviewsButton() {
        Selenide.switchTo().window(1);
        $x("//a[@data-target='hp-reviews-sliding']").shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @Step("Window with reviews the opened")
    public boolean windowWithReviewsOpened() {
        return $x("//div[@class='sliding-panel-widget-content review_list_block one_col']").shouldBe(Condition.visible).isDisplayed();
    }
}

