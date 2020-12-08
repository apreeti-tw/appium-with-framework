package org.appium.resources;

import org.appium.utilities.Base;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class Listeners implements ITestListener {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            Method method = iTestResult.getMethod().getConstructorOrMethod().getMethod();
            Test test = method.getAnnotation(Test.class);
            System.out.println("Take failing screenshot " + test.testName());
            Base.getScreenshot("failed_"+test.testName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
