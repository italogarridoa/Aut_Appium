package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class CapabilitiesManager {

    public static UiAutomator2Options getAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554");
        options.setApp(Paths.get(System.getProperty("user.dir"), "apps", "Android.SauceLabs.Mobile.Sample.app.apk").toString());
        options.setAppWaitActivity("*");
        return options;
    }

    public static AndroidDriver createAndroidDriver() throws MalformedURLException {
        return new AndroidDriver(new URL("http://127.0.0.1:4723"), getAndroidOptions());
    }
}
