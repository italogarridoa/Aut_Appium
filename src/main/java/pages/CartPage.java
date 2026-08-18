package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By tituloProducto = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Sauce Labs Backpack\")");
    private final By btnCheckout = AppiumBy.accessibilityId("test-CHECKOUT");

    public String obtenerTituloProducto() {
        return getText(tituloProducto);
    }

    public boolean productoVisible(String nombreProducto) {
        By producto = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + nombreProducto + "\")");
        return isDisplayed(producto);
    }

    public void clickCheckout() {
        click(btnCheckout);
    }

    public boolean checkoutVisible() {
        return isDisplayed(btnCheckout);
    }
}
