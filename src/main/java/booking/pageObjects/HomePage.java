package booking.pageObjects;

import booking.config.CustomChromeDriver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static booking.utils.SelenideElementUtils.retryIfIntercepted;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    @Step("Open the home page")
    public void openHomePage() {
        Configuration.browser = CustomChromeDriver.class.getName();
        Selenide.open("https://www.booking.com/uk/");
    }

    @Step("Click on the registration button")
    public void clickOnRegisterButton() {
        retryIfIntercepted(() -> $x("//a[@data-testid='header-sign-up-button']")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Close pop-up")
    /**
     * This method is required, because PopUp appears on the page in random time. It is also may not appear.
     * So, there is a need to track pop up in separate thread and close it as far as it appears.
     */
    public void closePopUp() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        new Thread(() -> {
            WebDriverRunner.setWebDriver(driver);
            while (true) {
                try {
                    driver.getTitle();
                } catch (WebDriverException e) {
                    break;
                }
                try {
                    $x("//button[@aria-label='Закрити інформацію про вхід в акаунт.']").click();
                    return;
                } catch (ElementNotFound | StaleElementReferenceException ignored) {
                }
            }
        }).start();
    }

    @Step("Close the calendar")
    public void closeCalendar() {
        retryIfIntercepted(() -> $x("//div[@data-testid='searchbox-dates-container']")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Click on the search destination button")
    public void clickOnSearchForDestination() {
        retryIfIntercepted(() -> $x("//div[@data-testid='destination-container'] ")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Find pop-ups with popular destinations")
    public boolean findPopUpsWithPopularDestinations() {
        return retryIfIntercepted(() -> $x("//ul[@data-testid='autocomplete-results-options']")
                .shouldBe(Condition.visible)
                .isDisplayed());
    }

    @Step("Search for a city")
    public void searchCity(String city) {
        retryIfIntercepted(() -> $x("//div[@data-testid='destination-container']")
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x("//input[@class='eb46370fe1']")
                .shouldBe(Condition.visible)
                .setValue(city));
    }

    @Step("Set the date in the search bar")
    public void setDateInSearchBar(LocalDate startDate, LocalDate endDate) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(startDate);
        String endDateString = DateTimeFormatter.ISO_LOCAL_DATE.format(endDate);
        SelenideElement buttonToOpenCalendar = retryIfIntercepted(() ->
                $x("//button[@data-testid='date-display-field-start']")
                        .shouldBe(Condition.visible));
        SelenideElement startDateButton = $x("//span[@data-date='" + startDateString + "']");
        SelenideElement endDateButton = $x("//span[@data-date='" + endDateString + "']");

        if (startDateButton.is(Condition.hidden)) {
            buttonToOpenCalendar.click();
        }
        retryIfIntercepted(() -> startDateButton.shouldBe(Condition.visible).click());
        retryIfIntercepted(() -> endDateButton.shouldBe(Condition.visible).click());
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        retryIfIntercepted(() -> $x("//button[@type='submit']")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Click on car rental button")
    public void clickCarRentalButton() {
        retryIfIntercepted(() ->
                $x("//a[@id='cars']")
                        .shouldBe(Condition.visible)
                        .click());
    }

    @Step("Select another language")
    public void chooseAnotherLanguage() {
        retryIfIntercepted(() -> $x("//button[@data-testid='header-language-picker-trigger']")
                .shouldBe(Condition.visible)
                .click());
        retryIfIntercepted(() -> $x("//button[.//text()[contains(., 'English (US)')]][1]")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Get text after changing the language")
    public String getTextAfterChangeLanguage() {
        return $x("//span[@data-testid='herobanner-title1']")
                .shouldHave(Condition.exactText("Find your next stay"), Duration.ofSeconds(15))
                .getText();
    }

    @Step("Click on the leisure")
    public void clickLeisure() {
        retryIfIntercepted(() -> $x("//a[@id='attractions']")
                .shouldBe(Condition.visible)
                .click());
    }

    @Step("Click on support button")
    public void clickOnSupportButton() {
        retryIfIntercepted(() ->
                $x("//a[@data-ga-track='click|Click|Action: index|hc_entrypoint_footer_navigation']")
                        .shouldBe(Condition.visible)
                        .click());
    }

    @Step("Select your currency")
    public void selectYourCurrency() {
        retryIfIntercepted(() ->
                $x("//button[@data-testid='header-currency-picker-trigger']")
                        .shouldBe(Condition.visible)
                        .click());
        retryIfIntercepted(() ->
                $x("//div[text()='EUR']")
                        .shouldBe(Condition.visible)
                        .click());
    }
}
