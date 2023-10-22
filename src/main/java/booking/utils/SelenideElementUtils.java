package booking.utils;

import org.openqa.selenium.ElementClickInterceptedException;

import java.util.function.Supplier;

public class SelenideElementUtils {

    public static void retryIfIntercepted(Action action) {
        for (int i = 0; i < 3; i++) {
            try {
                action.doIt();
                return;
            } catch (ElementClickInterceptedException ignored) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Retry while clicking");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new IllegalStateException("Retry attempts are exhausted");
    }

    public static <T> T retryIfIntercepted(Supplier<T> action) {
        for (int i = 0; i < 3; i++) {
            try {
                return action.get();
            } catch (ElementClickInterceptedException ignored) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Retry while clicking");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new IllegalStateException("Retry attempts are exhausted");
    }
}
