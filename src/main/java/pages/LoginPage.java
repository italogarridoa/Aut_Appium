package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By username = AppiumBy.accessibilityId("test-Username");
    private final By password = AppiumBy.accessibilityId("test-Password");
    private final By btnLogin = AppiumBy.accessibilityId("test-LOGIN");
    private final By imgMochila = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.ImageView\").instance(6)");
    private final By mensajeError = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Sorry, this user has been locked out.\")");

    public void ingresarUsuario(String usuario) {
        waitForElementVisible(username).sendKeys(usuario);
    }

    public void ingresarPassword(String pass) {
        waitForElementVisible(password).sendKeys(pass);
    }

    public void clickIngresar() {
        click(btnLogin);
    }

    public void clickImgMochila() {
        click(imgMochila);
    }

    public void login(String usuario, String pass) {
        ingresarUsuario(usuario);
        ingresarPassword(pass);
        clickIngresar();
    }

    public String obtenerMensajeError() {
        return getText(mensajeError);
    }

    public boolean mensajeErrorVisible() {
        return isDisplayed(mensajeError);
    }
}
