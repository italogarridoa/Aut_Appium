package config;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;

public class BaseTest {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    protected AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return DRIVER.get();
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = CapabilitiesManager.createAndroidDriver();
        DRIVER.set(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }

    public String tomarCaptura(String nombre) {
        try {
            File source = driver.getScreenshotAs(OutputType.FILE);
            String carpeta = System.getProperty("user.dir") + "/target/reports/screenshots/";
            new File(carpeta).mkdirs();
            String rutaCompleta = carpeta + nombre + ".png";
            FileUtils.copyFile(source, new File(rutaCompleta));
            return "screenshots/" + nombre + ".png";
        } catch (Exception e) {
            throw new RuntimeException("Error al tomar captura: " + e.getMessage());
        }
    }
}
