package org.appium.pageobjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    public MenuPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(uiAutomator = "text(\"Views\")")
    public WebElement views;

    @AndroidFindBy(uiAutomator = "text(\"Expandable Lists\")")
    public WebElement expandableLists;

    @AndroidFindBy(uiAutomator = "text(\"1. Custom Adapter\")")
    public WebElement customAdapter;

    @AndroidFindBy(uiAutomator = "text(\"People Names\")")
    public WebElement peopleNames;

    @AndroidFindBy(className = "android.widget.ListView")
    public WebElement menu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
    public WebElement preferences;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
    public WebElement preferenceDependencies;

    @AndroidFindBy(id = "android:id/checkbox")
    public WebElement wifiCheckbox;

    @AndroidFindBy(uiAutomator = "text(\"WiFi settings\")")
    public WebElement wifiSettings;

    @AndroidFindBy(id = "android:id/edit")
    public WebElement wifiName;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement okButton;

    @AndroidFindBy(uiAutomator = "text=\"Accessibility\")")
    public WebElement accessibility;
}
