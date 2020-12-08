package org.appium.resources;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "wifiNames")
    public Object[][] getDataCollection(){
        Object[][] obj = new Object[][]{
                {"sanpre"},
                {"%^&^^*"}
        };
        return obj;
    }
}
