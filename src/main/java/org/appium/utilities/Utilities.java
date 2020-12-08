package org.appium.utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class Utilities {
    AndroidDriver<AndroidElement> driver;
    TouchAction touchAction;

    public Utilities(AndroidDriver driver){
        this.driver = driver;
        touchAction = new TouchAction(driver);
    }

    public void scrollToTextAndSelect(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))").click();
    }

    public void tapOnElement(WebElement element){
        touchAction.tap(tapOptions().withElement(element(element))).perform();
    }

    public void longPressOnElement(WebElement element){
        touchAction.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).release().perform();
    }

    public void switchContext(String context){
        driver.context(context);
    }

    public void pressAndroidKey(AndroidKey androidKey){
        driver.pressKey(new KeyEvent(androidKey));
    }
}
