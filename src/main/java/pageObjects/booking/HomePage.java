package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Exist;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

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
        $x("//a[@data-testid='header-sign-up-button']").click();
    }

    public void closePopUp(WebDriver driver) {
        new Thread(() -> {
            while(true) {
                try {
                    WebDriverRunner.setWebDriver(driver);
                    $x("//button[@aria-label='Закрити інформацію про вхід в акаунт.']")
                            .shouldBe(Exist.exist, Duration.ofSeconds(1))
                            .click();
                    return;
                } catch (ElementNotFound ignored) {

                }
            }
        }).start();
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

    public void setDateInSearchBar(String startDate, String endDate) {
        $x("//button[@data-testid='date-display-field-start']").click();
        $x("//span[@data-date='" + startDate + "']").click();
        $x("//span[@data-date='" + endDate + "']").click();
    }

    public void clickSearchButton() {
        $x("//button[@type='submit']").click();
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
