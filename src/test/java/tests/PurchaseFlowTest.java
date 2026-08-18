package tests;

import config.BaseTest;
import config.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class PurchaseFlowTest extends BaseTest {

    @Test
    public void checkoutCompleto() {
        ExtentTest test = ExtentReportManager.getReport().createTest("Checkout completo hasta confirmación");
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        loginPage.login("standard_user", "secret_sauce");
        test.pass("Paso 1: Login realizado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("01_login")).build());

        productsPage.agregarProductoAlCarrito();
        test.pass("Paso 2: Producto agregado al carrito",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("02_add_to_cart")).build());

        productsPage.tocarIconoCarrito();
        Assert.assertTrue(cartPage.checkoutVisible());
        test.pass("Paso 3: CHECKOUT visible en el carrito",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("03_checkout_visible")).build());

        cartPage.clickCheckout();
        test.pass("Paso 4: Clic en CHECKOUT",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("04_checkout")).build());

        checkoutPage.completarDatos("Test", "User", "12345");
        test.pass("Paso 5: Formulario llenado (Test / User / 12345)",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("05_formulario")).build());

        checkoutPage.clickContinue();
        test.pass("Paso 6: Clic en CONTINUE",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("06_continue")).build());

        BasePage.scrollDown(400);
        Assert.assertTrue(checkoutPage.totalVisible());
        Assert.assertEquals(checkoutPage.obtenerTotal(), "$29.99");
        test.pass("Paso 7: Total $29.99 verificado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("07_total")).build());

        checkoutPage.clickFinish();
        test.pass("Paso 8: Clic en FINISH",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("08_finish")).build());

        String mensaje = checkoutPage.obtenerMensajeExito();
        Assert.assertTrue(mensaje.startsWith("THANK YOU FOR YOU"),
                "Mensaje de confirmación inesperado: " + mensaje);
        test.pass("Paso 9: Mensaje de orden confirmada validado (" + mensaje + ")",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("09_thank_you")).build());

        ExtentReportManager.getReport().flush();
    }
}
