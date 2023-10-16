package pageObjects.booking;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Exist;

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

    public void closePopUp() {
        $x("//button[@aria-label='Закрити інформацію про вхід в акаунт.']").shouldBe(Exist.exist, Duration.ofSeconds(10)).click();
    }

    public void closePopUPData() {
        $x("//div[@data-testid='searchbox-dates-container']").click();
    }


    public void clickOnSearchForDestination() {
        $x("//div[@data-testid='destination-container'] ").click();
    }

    public boolean findPopUpsWithPopularDestinations() {
        return $x("//ul[@data-testid='autocomplete-results-options']").shouldBe(Condition.visible).isDisplayed();
    }

    public void searchCity(String city) {
        $x("//div[@data-testid='destination-container']").click();
        $x("//input[@class='eb46370fe1']").setValue(city);
    }

    public void clickSearchButton() {
        $x("//button[@type='submit']").click();
    }

    public void clickCarRentalButton() {
        $x("//a[@id='cars']").click();
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
}
