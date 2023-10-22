package booking.pageObjects;

import booking.utils.StringUtils;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class BookingPage {
    @Step("Check if the reservation information is available")
    public boolean bookingInformation() {
        return retryIfIntercepted(() ->
                $x("//div[@class='bui-date-range bui-date-range--large bp-date-range']").shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Get the actual price")
    public double getPrice() {
        String priceText = retryIfIntercepted(() ->$x("//span[@data-animate-price-group-name='bp_user_total_price']")
                .shouldBe(Condition.visible)
                .getText());
        String cleanPrice = priceText
                .replaceAll(StringUtils.UAH, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.WHITE_SPACE, StringUtils.EMPTY_STRING)
                .replaceAll(StringUtils.COMMA, StringUtils.DOT);
        return Double.parseDouble(cleanPrice);
    }
}


