package pageObjects.booking;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class CustomChromeDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        Map<String, Object> userAgentParams = new HashMap<>();
        userAgentParams.put("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.5938.149 Safari/537.36");

        Map<String, Object> removeAutomationProperties = new HashMap<>();
        removeAutomationProperties.put("source",
                "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Array;\n" +
                "delete window.cdc_adoQpoasnfa76pfcZLmcfl_JSON;\n" +
                "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Proxy;\n" +
                "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Object;\n" +
                "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Promise;\n" +
                "delete navigator.__proto__.webdriver;\n" +
                "delete window.cdc_adoQpoasnfa76pfcZLmcfl_Symbol;");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-blink-features=AutomationControlled");
//        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--window-size=1920,1080");
//        chromeOptions.addArguments("no-sandbox");
//        chromeOptions.addArguments("start-fullscreen");
//        chromeOptions.addArguments("single-process");
//        chromeOptions.addArguments("disable-dev-shm-usage");
//        chromeOptions.addArguments("incognito");
//        chromeOptions.addArguments("disable-infobars");

        capabilities.asMap().forEach(chromeOptions::setCapability);
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        driver.executeCdpCommand("Network.setUserAgentOverride", userAgentParams);
        driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", removeAutomationProperties);
        return driver;
    }
}
