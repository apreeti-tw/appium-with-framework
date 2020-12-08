package org.appium;

import io.appium.java_client.android.AndroidDriver;
import org.appium.pageobjects.MenuPage;
import org.appium.resources.TestData;
import org.appium.utilities.Base;
import org.appium.utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class APIActionsTest extends Base {
    AndroidDriver driver;
    Utilities utils;
    MenuPage menuPage;

    @BeforeClass
    public void setup () throws IOException, InterruptedException {
        startEmulator();
        startServer();
        driver = initialiseDriver("APIDemoApp","AndroidDeviceName");
        utils = new Utilities(driver);
        menuPage = new MenuPage(driver);
    }

    @Test (testName = "menuTest")
    public void menuTest(){
        utils.tapOnElement(menuPage.views);
        utils.tapOnElement(menuPage.expandableLists);
        utils.tapOnElement(menuPage.customAdapter);
        utils.longPressOnElement(menuPage.peopleNames);
        Assert.assertTrue(menuPage.menu.isDisplayed());
    }

    @Test (testName = "enterValueTest", dataProvider = "wifiNames", dataProviderClass = TestData.class)
    public void enterValueTest(String input){
        menuPage.preferences.click();
        menuPage.preferenceDependencies.click();
        utils.tapOnElement(menuPage.wifiCheckbox);
        menuPage.wifiSettings.click();
        menuPage.wifiName.sendKeys(input);
        menuPage.okButton.click();
    }

    @AfterMethod
    public void resetApp(){
        driver.resetApp();
    }

    @AfterClass
    public void tearDown () throws IOException, InterruptedException {
        driver.closeApp();
        driver.quit();
        stopServer();
        stopEmulator();
    }
}
