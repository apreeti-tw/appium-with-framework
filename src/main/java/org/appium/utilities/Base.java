package org.appium.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    static AndroidDriver<AndroidElement> driver;
    static AppiumDriverLocalService service;
    static Process process;

    @Value("${AdbPath}")
    public static String adbPath;

    public static void startServer(){
      service = AppiumDriverLocalService.buildDefaultService();
      service.start();
    }

    public static void startEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(System.getProperty("user.dir") + "/src/main/java/org/appium/resources/startEmulator.sh");
        Thread.sleep(5000);
    }

    public static void stopServer(){
        service.stop();
    }

    public static void stopEmulator() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/appium/application.properties");
        Properties prop = new Properties();
        prop.load(fis);

        String[] aCommand = new String[] { prop.getProperty("AdbPath"), "emu", "kill" };
        process = new ProcessBuilder(aCommand).start();
        process.waitFor(1, TimeUnit.SECONDS);
        System.out.println("Emulator closed successfully!");
    }

    public static void getScreenshot(String fileName) throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/src/screenshots/"+fileName+".png"));
    }

    public static AndroidDriver initialiseDriver(String apk, String deviceName){
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/appium/application.properties");
            Properties properties = new Properties();
            properties.load(fis);

            if (deviceName.isEmpty()){
                deviceName = System.getProperty("deviceName");
            }

            File filePath = new File(System.getProperty("user.dir")+"/src");
            File apkPath = new File(filePath, properties.get(apk).toString());
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(MobileCapabilityType.DEVICE_NAME, properties.get(deviceName).toString());
            dc.setCapability(MobileCapabilityType.APP, apkPath.getAbsolutePath());


            driver = new AndroidDriver(new URL(properties.get("SeleniumHubURL").toString()), dc);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return driver;
    }
}
