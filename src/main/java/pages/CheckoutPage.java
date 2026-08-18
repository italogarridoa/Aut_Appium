package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private final By firstName = AppiumBy.accessibilityId("test-First Name");
    private final By lastName = AppiumBy.accessibilityId("test-Last Name");
    private final By postalCode = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By btnContinue = AppiumBy.accessibilityId("test-CONTINUE");
    private final By total = AppiumBy.androidUIAutomator("new UiSelector().text(\"$29.99\")");
    private final By btnFinish = AppiumBy.accessibilityId("test-FINISH");
    // La app muestra el texto con typo ("YOU" en lugar de "YOUR") según la versión del APK
    private final By mensajeExito = AppiumBy.androidUIAutomator(
            "new UiSelector().textStartsWith(\"THANK YOU FOR YOU\")");

    public void completarDatos(String nombre, String apellido, String codigoPostal) {
        type(firstName, nombre);
        type(lastName, apellido);
        type(postalCode, codigoPostal);
    }

    public void clickContinue() {
        click(btnContinue);
    }

    public String obtenerTotal() {
        return getText(total);
    }

    public boolean totalVisible() {
        return isDisplayed(total);
    }

    public void clickFinish() {
        click(btnFinish);
    }

    public boolean mensajeExitoVisible() {
        return isDisplayed(mensajeExito);
    }

    public String obtenerMensajeExito() {
        return getText(mensajeExito);
    }
}
