package booking.pageObjects;

import booking.utils.StringUtils;
import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.checkElementVisibleAndEnabled;

public class BookingPage {
    @Step("Check if the reservation information is available")
    public boolean bookingInformation() {
        return checkElementVisibleAndEnabled("//div[@class='bui-date-range bui-date-range--large bp-date-range']")
                .isDisplayed();
    }

    @Step("Get the actual price")
    public double getPrice() {
        String priceText = checkElementVisibleAndEnabled("//span[@data-animate-price-group-name='bp_user_total_price']")
                .getText();
        String cleanPrice = priceText
                .replaceAll(StringUtils.UAH, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.WHITE_SPACE, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.COMMA, StringUtils.DOT);
        return Double.parseDouble(cleanPrice);
    }
}


