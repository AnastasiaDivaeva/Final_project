package booking.pageObjects;

import booking.utils.StringUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;
import static com.codeborne.selenide.Selenide.$x;

public class DetailPage {
    @Step("Choose an apartment")
    public void selectionApartments() {
        Selenide.switchTo().window(1);
        checkElementVisibleAndEnabled("//select[contains(@class, 'hprt-nos-select')]").click();
        checkElementVisibleAndEnabled("//option[@value='1'][1]").click();
    }

    @Step("Click on submit button")
    public void clickOnSubmitButton() {
        checkElementVisibleAndEnabled("//button[@data-tooltip-class='submit_holder_button_tooltip']").click();
    }

    @Step("Get the expected price")
    public int getPrice() {
        String rawPriceValue = "";

        SelenideElement priceWithDiscount = $x("//div[@class='hprt-reservation-total-price" +
                " bui-price-display__value prco-inline-block-maker-helper']");
        SelenideElement priceWithoutDiscount = $x("//div[@class='hprt-reservation-total-price bui-price-display__value']");

        if (priceWithDiscount.is(Condition.exist)) {
            rawPriceValue = priceWithDiscount.getText();
        } else {
            rawPriceValue = priceWithoutDiscount.getText();
        }
        String cleanPrice = rawPriceValue
                .replaceAll(StringUtils.UAH, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.WHITE_SPACE, StringUtils.EMPTY_STRING);
        return Integer.parseInt(cleanPrice);
    }

    @Step("Click on the button Information about the object")
    public void clickOnFacilitiesInformationButton() {
        Selenide.switchTo().window(1);
        checkElementVisibleAndEnabled("//a[@data-scroll='a[name=HotelFacilities]']").click();
    }

    public boolean informationAboutServiceIsDisplayed() {
        return $x("//div[@data-testid='property-section--content']" +
                "//div[@data-testid='property-most-popular-facilities-wrapper']").isDisplayed();
    }

    @Step("Click on reviews button")
    public void clickOnReviewsButton() {
        Selenide.switchTo().window(1);
        SelenideElement reviewButton = $x("//a[@data-target='hp-reviews-sliding']");
        if (reviewButton.is(Condition.hidden)) {
            checkElementVisibleAndEnabled("//li[@class='d37611a2e0 b4dfbcc93e ac2309e98f' and @role='presentation']//button").click();
            checkElementVisibleAndEnabled("//a[@data-testid='Property-Header-Nav-Tab-Trigger-reviews' and @role='button']").click();
        } else {
            reviewButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        }
    }

    @Step("Window with reviews the opened")
    public boolean windowWithReviewsOpened() {
        return checkElementVisibleAndEnabled("//div[@class='sliding-panel-widget-content review_list_block one_col']")
                .isDisplayed();
    }
}

