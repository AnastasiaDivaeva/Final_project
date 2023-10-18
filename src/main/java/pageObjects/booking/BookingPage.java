package pageObjects.booking;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class BookingPage {
    public boolean bookingInformation() {
        return $x("//div[@class='bui-date-range bui-date-range--large bp-date-range']")
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    public double getPrice() {
        String priceText = $x("//span[@data-animate-price-group-name='bp_user_total_price']").getText();
        String cleanPrice = priceText
                .replaceAll("UAH", "")
                .replaceAll(" ", "")
                .replaceAll(",",".");
        return Double.parseDouble(cleanPrice);
    }
}


