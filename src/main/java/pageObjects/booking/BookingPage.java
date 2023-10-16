package pageObjects.booking;

import static com.codeborne.selenide.Selenide.$x;

public class BookingPage {
    public boolean bookingInformation() {
        return $x(" //div[@class='bui-date-range bui-date-range--large bp-date-range']").isDisplayed();
    }

    public double getPrice() {
//        $x("//span[@data-animate-price-group-name='bp_user_total_price']").getText().replaceAll("UAH", "")
//                .replaceAll(" ", "");

        String priceText = $x("//span[@data-animate-price-group-name='bp_user_total_price']").getText();
        String cleanPrice = priceText
                .replaceAll("UAH", "")
                .replaceAll(" ", "")
                .replaceAll(",",".");
        return Double.parseDouble(cleanPrice);
    }
}


