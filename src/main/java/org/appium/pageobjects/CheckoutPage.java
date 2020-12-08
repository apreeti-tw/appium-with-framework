package org.appium.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utilities.Base;
import org.appium.utilities.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends Base {
    Utilities utils;

    public CheckoutPage(AppiumDriver driver, Utilities utils){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.utils = utils;
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    List<WebElement> productPriceList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    WebElement sumOfAllProducts;

    @AndroidFindBy(className = "android.widget.CheckBox")
    WebElement tnc;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    WebElement tncButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@text=\'CLOSE\']")
    WebElement tncClose;

    @AndroidFindBy(className = "android.widget.Button")
    WebElement proceed;

    public double getTotalOfProducts(){
        double totalAmount = 0.0f;
        for (int i = 0; i < productPriceList.size(); i++) {
            totalAmount+=getAmount(productPriceList.get(i));
        }

        return totalAmount;
    }

    public double getSumTotal(){
        return getAmount(sumOfAllProducts);
    }

    public double getAmount(WebElement element){
        return Double.parseDouble(element.getText().substring(1));
    }

    public void viewTermsAndConditions() throws InterruptedException {
        //Mobile gestures
        utils.tapOnElement(tnc);
        utils.longPressOnElement(tncButton);
        utils.tapOnElement(tncClose);
        Thread.sleep(1000);
    }

    public void proceed() throws InterruptedException {
        proceed.click();
        Thread.sleep(5000);
    }

}
