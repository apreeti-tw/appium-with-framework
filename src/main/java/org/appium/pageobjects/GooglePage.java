package org.appium.pageobjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utilities.Utilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {
    Utilities utils;

    public GooglePage(AndroidDriver driver, Utilities utils){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.utils = utils;
    }

    @FindBy(name = "q")
    WebElement googleSearch;

    public void enterGoogleSearchText(String text){
        utils.switchContext("WEBVIEW_com.androidsample.generalstore");
        googleSearch.sendKeys(text);
        googleSearch.sendKeys(Keys.ENTER);
        utils.pressAndroidKey(AndroidKey.BACK);
        utils.switchContext("NATIVE_APP");
    }
}
