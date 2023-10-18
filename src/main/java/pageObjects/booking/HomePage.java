package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    @Step("Open the home page")
    public void openHomePage() {
        Configuration.browser = CustomChromeDriver.class.getName();
        Selenide.open("https://www.booking.com/uk/");
    }

    @Step("Click on the registration button")
    public void clickOnRegisterButton() {
        $x("//a[@data-testid='header-sign-up-button']").shouldBe(Condition.visible).click();
    }

    @Step("Close pop-up")
    public void closePopUp() {
        try {
            $x("//button[@aria-label='Закрити інформацію про вхід в акаунт.']")
                    .shouldBe(Condition.visible, Duration.ofSeconds(20))
                    .click();
        } catch (ElementNotFound ignored) {
        }
    }

    @Step("Close the calendar")
    public void closeCalendar() {
        $x("//div[@data-testid='searchbox-dates-container']").click();
    }

    @Step("Click on the search destination button")
    public void clickOnSearchForDestination() {
        $x("//div[@data-testid='destination-container'] ").click();
    }

    @Step("Find pop-ups with popular destinations")
    public boolean findPopUpsWithPopularDestinations() {
        return $x("//ul[@data-testid='autocomplete-results-options']").shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Search for a city")
    public void searchCity(String city) {
        $x("//div[@data-testid='destination-container']").shouldBe(Condition.visible).click();
        $x("//input[@class='eb46370fe1']").setValue(city);
    }

    @Step("Search for a city after logging in")
    public void searchCityAfterLogin(String city) {
        $x("//label[@class='sb-destination-label-sr']").shouldBe(Condition.visible).click();
        $x("//input[@type='search']").setValue(city);
    }

    @Step("Set the date in the search bar")
    public void setDateInSearchBar(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        $x("//button[@data-testid='date-display-field-start']").click();
        $x("//span[@data-date='" + startDateString + "']").click();
        $x("//span[@data-date='" + endDateString + "']").click();
    }

    @Step("Set the date in the search bar")
    public void setDateInSearchBarAfterLogin(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        $x("//div[@class='xp__dates-inner']").click();
        $x("//td[@data-date='" + startDateString + "']").click();
        $x("//td[@data-date='" + endDateString + "']").click();
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        $x("//button[@type='submit']").click();
    }

    @Step("Click on search button")
    public void clickSearchButtonAfterLogin() {
        $x("//button[@data-sb-id='main']").click();
    }

    @Step("Click on car rental button")
    public void clickCarRentalButton() {
        $x("//a[@id='cars']").shouldBe(Condition.visible).click();
    }

    @Step("Select another language")
    public void chooseAnotherLanguage() {
        $x("//button[@data-testid='header-language-picker-trigger']").click();
        $x("//span[text()='English (US)'][1]").click();
    }

    @Step("Get text after changing the language")
    public String getTextAfterChangeLanguage() {
        return $x("//span[@data-testid='herobanner-title1']")
                .shouldHave(Condition.exactText("Find your next stay"))
                .getText();
    }

    @Step("Click on header profile")
    public void clickOnHeaderProfile() {
        $x("//div[@data-active-button-classname='bui-button--active']").shouldBe(Condition.visible).click();
    }

    @Step("Choose account management")
    public void chooseAccountManagement() {
        $x("//span[contains(text(),'Керувати акаунтом')]").click();
    }

    @Step("Click on the leisure")
    public void clickLeisure() {
        $x("//a[@id='attractions']").shouldBe(Condition.visible).click();
    }
}
