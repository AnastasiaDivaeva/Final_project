package booking.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SelenideElementUtils {
    public static SelenideElement checkElementVisibleAndEnabled(String xpath) {
        return $x(xpath).shouldBe(Condition.enabled, Condition.visible);
    }

    public static SelenideElement checkElementVisibleAndEnabled(SelenideElement element) {
        return element.shouldBe(Condition.enabled, Condition.visible);
    }
}
