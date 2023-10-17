package pageObjects.booking;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class DetailPage {
    public void selectionApartments() {
        Selenide.switchTo().window(1);
        $x("//select[contains(@class, 'hprt-nos-select')]").click();
        $x("//option[@value='1'][1]").click();
    }

    public void clickOnSubmitButton() {
        $x("//button[@data-tooltip-class='submit_holder_button_tooltip']").click();
    }

    public int getPrice() {
        String priceText = $x("//div[@class='hprt-reservation-total-price" +
                " bui-price-display__value prco-inline-block-maker-helper']").getText();
        String cleanPrice = priceText.replaceAll("UAH", "").replaceAll(" ", "");
        return Integer.parseInt(cleanPrice);
    }

    public void clickOnFacilitiesInformationButton() {
        Selenide.switchTo().window(1);
        $x("//a[@data-scroll='a[name=HotelFacilities]']").click();
    }

    public boolean informationAboutServiceIsDisplayed() {
        return $x("//div[@data-testid='property-section--content']" +
                "//div[@data-testid='property-most-popular-facilities-wrapper']")
                .isDisplayed();
    }
}

