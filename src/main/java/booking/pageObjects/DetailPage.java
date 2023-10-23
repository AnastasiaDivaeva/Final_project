package booking.pageObjects;

import booking.utils.CurrenciesUtils;
import booking.utils.StringUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class DetailPage {
    private static final String ROOMS_SELECT = "//select[contains(@class, 'hprt-nos-select')]";
    private static final String SUBMIT_BUTTON = "//button[@data-tooltip-class='submit_holder_button_tooltip']";
    private static final String FACILITIES_INFORMATION_BUTTON = "//a[@data-scroll='a[name=HotelFacilities]']";
    private static final String REVIEWS_BUTTON = "//a[@data-target='hp-reviews-sliding']";
    private static final String ROOM_OPTION_TO_SELECT = "//option[@value='1'][1]";
    private static final String PRICE_WITH_DISCOUNT = "//div[@class='hprt-reservation-total-price" +
            " bui-price-display__value prco-inline-block-maker-helper']";
    private static final String PRICE_WITHOUT_DISCOUNT = "//div[@class='hprt-reservation-total-price bui-price-display__value']";
    private static final String INFORMATION_ABOUT_SERVICE = "//div[@data-testid='property-section--content']" +
            "//div[@data-testid='property-most-popular-facilities-wrapper']";
    private static final String REVIEWS_SECTION = "//div[@class='sliding-panel-widget-content review_list_block one_col']";

    @Step("Choose an apartment")
    public void selectionApartments() {
        Selenide.switchTo().window(1);
        retryIfIntercepted(() -> $x(ROOMS_SELECT)
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x(ROOM_OPTION_TO_SELECT).shouldBe(Condition.visible).click());
    }

    @Step("Click on submit button")
    public void clickOnSubmitButton() {
        retryIfIntercepted(() -> $x(SUBMIT_BUTTON)
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Get the expected price")
    public int getPrice() {
        String rawPriceValue;

        SelenideElement priceWithDiscount = $x(PRICE_WITH_DISCOUNT);
        SelenideElement priceWithoutDiscount = $x(PRICE_WITHOUT_DISCOUNT);

        if (priceWithDiscount.is(Condition.exist)) {
            rawPriceValue = retryIfIntercepted(priceWithDiscount::getText);
        } else {
            rawPriceValue = retryIfIntercepted(priceWithoutDiscount::getText);
        }
        String cleanPrice = rawPriceValue
                .replaceAll(CurrenciesUtils.UAH, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.WHITE_SPACE, StringUtils.EMPTY_STRING);
        return Integer.parseInt(cleanPrice);
    }

    @Step("Click on the button Information about the object")
    public void clickOnFacilitiesInformationButton() {
        Selenide.switchTo().window(1);
        retryIfIntercepted(() -> $x(FACILITIES_INFORMATION_BUTTON)
                .shouldBe(Condition.visible)
                .click());
    }

    public boolean informationAboutServiceIsDisplayed() {
        return retryIfIntercepted(() -> $x(INFORMATION_ABOUT_SERVICE)
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Click on reviews button")
    public void clickOnReviewsButton() {
        Selenide.switchTo().window(1);
        SelenideElement reviewButton = $x(REVIEWS_BUTTON);
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

    @Step("Reviews' section is opened")
    public boolean reviewsSectionIsOpened() {
        return retryIfIntercepted(() -> $x(REVIEWS_SECTION)
                .shouldBe(Condition.visible)
                .isDisplayed());
    }
}

