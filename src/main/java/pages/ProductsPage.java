package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private final By tituloProducts = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");
    private final By btnAddToCart = AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)");
    private final By iconoCarrito = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.ImageView\").instance(3)");
    private final By btnCarrito = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.ViewGroup\").instance(13)");
    private final By filtro = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.ImageView\").instance(5)");
    private final By opcionHighToLow = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Price (high to low)\")");
    private final By precios = AppiumBy.accessibilityId("test-Price");

    public String obtenerTituloProducts() {
        return getText(tituloProducts);
    }

    public void agregarProductoAlCarrito() {
        click(btnAddToCart);
    }

    public void tocarIconoCarrito() {
        click(iconoCarrito);
    }

    public void clickCarrito() {
        click(btnCarrito);
    }

    public void abrirFiltro() {
        click(filtro);
    }

    public void seleccionarPriceHighToLow() {
        click(opcionHighToLow);
    }

    public void ordenarPorPrecioMayorAMenor() {
        abrirFiltro();
        seleccionarPriceHighToLow();
    }

    public List<Double> obtenerPrecios() {
        List<WebElement> elementos = driver.findElements(precios);
        return elementos.stream()
                .map(WebElement::getText)
                .map(texto -> texto.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public double obtenerPrimerPrecio() {
        List<Double> preciosVisibles = obtenerPrecios();
        if (preciosVisibles.isEmpty()) {
            throw new IllegalStateException("No se encontraron precios en la lista de productos.");
        }
        return preciosVisibles.get(0);
    }

    public double obtenerUltimoPrecio() {
        List<Double> preciosVisibles = obtenerPrecios();
        if (preciosVisibles.isEmpty()) {
            throw new IllegalStateException("No se encontraron precios en la lista de productos.");
        }
        return preciosVisibles.get(preciosVisibles.size() - 1);
    }
}
