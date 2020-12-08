package org.appium;

import io.appium.java_client.android.AndroidDriver;
import org.appium.pageobjects.CheckoutPage;
import org.appium.pageobjects.FormPage;
import org.appium.pageobjects.GooglePage;
import org.appium.pageobjects.ProductsPage;
import org.appium.utilities.Base;
import org.appium.utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class ECommerceCheckoutTest extends Base {
    AndroidDriver driver;
    Utilities utils;
    FormPage formPage;
    ProductsPage productsPage;
    CheckoutPage checkoutPage;
    GooglePage googlePage;

    @BeforeClass
    public void setup() throws InterruptedException, IOException {
        startEmulator();
        startServer();
        driver = initialiseDriver("GeneralStoreApp", "AndroidDeviceName");
        driver.hideKeyboard();
        utils = new Utilities(driver);
        formPage = new FormPage(driver, utils);
        productsPage = new ProductsPage(driver);
        checkoutPage = new CheckoutPage(driver, utils);
        googlePage = new GooglePage(driver, utils);
        Thread.sleep(4000);
    }

    @Parameters({"username", "country", "searchText"})
    @Test (testName = "checkoutTest")
    public void checkoutTest(String username, String country, String searchText) {
        try {
            formPage.fillForm(username, country);
            productsPage.addProductToCart(0);
            productsPage.addProductToCart(0);
            productsPage.proceed();

            Assert.assertEquals(checkoutPage.getSumTotal(), checkoutPage.getTotalOfProducts());

            checkoutPage.viewTermsAndConditions();
            checkoutPage.proceed();

            googlePage.enterGoogleSearchText(searchText);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @AfterMethod
    public void resetApp(){
        driver.resetApp();
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {
        driver.closeApp();
        driver.quit();
        stopEmulator();
        stopServer();
    }
}
