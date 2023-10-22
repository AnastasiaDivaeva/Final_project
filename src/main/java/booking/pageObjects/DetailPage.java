package booking.pageObjects;

import booking.utils.StringUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class DetailPage {
    @Step("Choose an apartment")
    public void selectionApartments() {
        Selenide.switchTo().window(1);
        retryIfIntercepted(() -> $x("//select[contains(@class, 'hprt-nos-select')]")
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x("//option[@value='1'][1]").shouldBe(Condition.visible).click());
    }

    @Step("Click on submit button")
    public void clickOnSubmitButton() {
        retryIfIntercepted(() -> $x("//button[@data-tooltip-class='submit_holder_button_tooltip']")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Get the expected price")
    public int getPrice() {
        String rawPriceValue;

        SelenideElement priceWithDiscount = $x("//div[@class='hprt-reservation-total-price" +
                " bui-price-display__value prco-inline-block-maker-helper']");
        SelenideElement priceWithoutDiscount = $x("//div[@class='hprt-reservation-total-price bui-price-display__value']");

        if (priceWithDiscount.is(Condition.exist)) {
            rawPriceValue = retryIfIntercepted(priceWithDiscount::getText);
        } else {
            rawPriceValue = retryIfIntercepted(priceWithoutDiscount::getText);
        }
        String cleanPrice = rawPriceValue
                .replaceAll(StringUtils.UAH, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.WHITE_SPACE, StringUtils.EMPTY_STRING);
        return Integer.parseInt(cleanPrice);
    }

    @Step("Click on the button Information about the object")
    public void clickOnFacilitiesInformationButton() {
        Selenide.switchTo().window(1);
        retryIfIntercepted(() -> $x("//a[@data-scroll='a[name=HotelFacilities]']")
                .shouldBe(Condition.visible)
                .click());
    }

    public boolean informationAboutServiceIsDisplayed() {
        return retryIfIntercepted(() -> $x("//div[@data-testid='property-section--content']" +
                "//div[@data-testid='property-most-popular-facilities-wrapper']")
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Click on reviews button")
    public void clickOnReviewsButton() {
        Selenide.switchTo().window(1);
        SelenideElement reviewButton = $x("//a[@data-target='hp-reviews-sliding']");
        if (reviewButton.is(Condition.hidden)) {
            retryIfIntercepted(() -> $x("//li[@class='d37611a2e0 b4dfbcc93e ac2309e98f' and @role='presentation']//button")
                    .shouldBe(Condition.visible)
                    .click());
            retryIfIntercepted(() -> $x("//a[@data-testid='Property-Header-Nav-Tab-Trigger-reviews' and @role='button']")
                    .shouldBe(Condition.visible)
                    .click());
        } else {
            reviewButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        }
    }

    @Step("Window with reviews the opened")
    public boolean windowWithReviewsOpened() {
        return retryIfIntercepted(() -> $x("//div[@class='sliding-panel-widget-content review_list_block one_col']")
                .shouldBe(Condition.visible)
                .isDisplayed());
    }
}

