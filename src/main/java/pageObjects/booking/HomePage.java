package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    public void clickOnLoginButton() {
        $x("//a[@data-testid='header-sign-in-button']").click();
    }

    public void clickOnDataContainer() {
        $x("//div[@data-testid='searchbox-dates-container']").click();
    }

    public void openHomePage() {
        Configuration.browser = CustomChromeDriver.class.getName();
        Selenide.open("https://www.booking.com/uk/");
    }

    public void clickOnRegisterButton() {
        $x("//a[@data-testid='header-sign-up-button']").shouldBe(Condition.visible).click();
    }

    public void closePopUp() {
        try {
            $x("//button[@aria-label='Закрити інформацію про вхід в акаунт.']")
                    .shouldBe(Condition.visible, Duration.ofSeconds(20))
                    .click();
        } catch (ElementNotFound ignored) {
        }
    }

    public void closeCalendar() {
        $x("//div[@data-testid='searchbox-dates-container']").click();
    }


    public void clickOnSearchForDestination() {
        $x("//div[@data-testid='destination-container'] ").click();
    }

    public boolean findPopUpsWithPopularDestinations() {
        return $x("//ul[@data-testid='autocomplete-results-options']").shouldBe(Condition.visible).isDisplayed();
    }

    public void searchCity(String city) {
        $x("//div[@data-testid='destination-container']").shouldBe(Condition.visible).click();
        $x("//input[@class='eb46370fe1']").setValue(city);
    }

    public void searchCityAfterLogin(String city) {
        $x("//label[@class='sb-destination-label-sr']").shouldBe(Condition.visible).click();
        $x("//input[@type='search']").setValue(city);
    }

    public void setDateInSearchBar(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        $x("//button[@data-testid='date-display-field-start']").click();
        $x("//span[@data-date='" + startDateString + "']").click();
        $x("//span[@data-date='" + endDateString + "']").click();
    }

    public void setDateInSearchBarAfterLogin(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        $x("//div[@class='xp__dates-inner']").click();
        $x("//td[@data-date='" + startDateString + "']").click();
        $x("//td[@data-date='" + endDateString + "']").click();
    }

    public void clickSearchButton() {
        $x("//button[@type='submit']").click();
    }

    public void clickSearchButtonAfterLogin() {
        $x("//button[@data-sb-id='main']").click();
    }

    public void clickCarRentalButton() {
        $x("//a[@id='cars']").shouldBe(Condition.visible).click();
    }

    public void chooseAnotherLanguage() {
        $x("//button[@data-testid='header-language-picker-trigger']").click();
        $x("//span[text()='English (US)'][1]").click();
    }

    public String getTextAfterChangeLanguage() {
        return $x("//span[@data-testid='herobanner-title1']").getText();
    }

    public void clickOnHeaderProfile() {
        $x("//div[@data-active-button-classname='bui-button--active']").shouldBe(Condition.visible).click();
    }


    public void chooseAccountManagement() {
        $x("//span[contains(text(),'Керувати акаунтом')]").click();
    }

    public void clickLeisureSearch() {
        $x("//a[@id='attractions']").shouldBe(Condition.visible).click();
    }
}
