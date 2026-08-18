package tests;

import config.BaseTest;
import config.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginExitoso() {
        ExtentTest test = ExtentReportManager.getReport().createTest(
                "Script #1: Login exitoso + Agregar producto al carrito");
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        test.info("Paso 1: Iniciar la app Swag Labs",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("01_app_abierta")).build());

        loginPage.ingresarUsuario("standard_user");
        loginPage.ingresarPassword("secret_sauce");
        test.pass("Paso 2: Ingresar standard_user / secret_sauce",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("02_credenciales_ingresadas")).build());

        loginPage.clickIngresar();
        test.pass("Paso 3: Tocar Login",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("03_login_presionado")).build());

        Assert.assertTrue(productsPage.tituloProductsVisible(),
                "La pantalla de productos no está visible");
        Assert.assertEquals(productsPage.obtenerTituloProducts(), "PRODUCTS");
        test.pass("Paso 4: Verificar pantalla de productos visible (título PRODUCTS)",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("04_titulo_products")).build());

        productsPage.agregarProductoAlCarrito();
        test.pass("Paso 5: Agregar el primer producto tocando ADD TO CART",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("05_add_to_cart")).build());

        productsPage.tocarIconoCarrito();
        test.pass("Paso 6: Tocar ícono del carrito",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("06_icono_carrito")).build());

        Assert.assertTrue(cartPage.productoVisible("Sauce Labs Backpack"),
                "El producto no aparece en el carrito");
        Assert.assertEquals(cartPage.obtenerTituloProducto(), "Sauce Labs Backpack");
        test.pass("Paso 7: Verificar que el producto aparece en el carrito",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("07_producto_en_carrito")).build());

        ExtentReportManager.getReport().flush();
    }

    @Test
    public void loginInvalidoUsuarioBloqueado() {
        ExtentTest test = ExtentReportManager.getReport().createTest("Login inválido - usuario bloqueado");
        LoginPage loginPage = new LoginPage();

        test.info("Paso 1: La app abrió correctamente",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("01_app_abierta")).build());

        loginPage.ingresarUsuario("locked_out_user");
        test.pass("Paso 2: Usuario locked_out_user ingresado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("02_usuario_bloqueado")).build());

        loginPage.ingresarPassword("secret_sauce");
        test.pass("Paso 3: Password ingresada",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("03_password_ingresado")).build());

        loginPage.clickIngresar();
        test.pass("Paso 4: Botón login presionado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("04_login_presionado")).build());

        Assert.assertTrue(loginPage.mensajeErrorVisible());
        Assert.assertEquals(loginPage.obtenerMensajeError(), "Sorry, this user has been locked out.");
        test.pass("Paso 5: Mensaje de usuario bloqueado validado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("05_mensaje_error")).build());

        ExtentReportManager.getReport().flush();
    }
}
