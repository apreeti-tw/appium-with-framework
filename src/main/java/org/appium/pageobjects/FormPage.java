package org.appium.pageobjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utilities.Base;
import org.appium.utilities.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends Base {
    Utilities utils = null;

    public FormPage(AndroidDriver<AndroidElement> driver, Utilities utils){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.utils = utils;
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    WebElement selectCountry;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))")
    WebElement countryDropdown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    WebElement nameInput;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    WebElement genderFemale;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement letsShop;

    public void fillForm(String name, String country) throws InterruptedException {
        selectCountry.click();
        utils.scrollToTextAndSelect(country);

        nameInput.sendKeys(name);
        genderFemale.click();

        utils.tapOnElement(letsShop);
        Thread.sleep(2000);
    }
}


