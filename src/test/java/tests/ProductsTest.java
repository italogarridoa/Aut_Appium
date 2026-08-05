package tests;

import config.BaseTest;
import config.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {

    @Test
    public void verificarTituloProducts() {
        ExtentTest test = ExtentReportManager.getReport().createTest("Verificar título PRODUCTS");
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();

        loginPage.login("standard_user", "secret_sauce");
        test.pass("Login realizado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("01_login_ok")).build());

        Assert.assertEquals(productsPage.obtenerTituloProducts(), "PRODUCTS");
        test.pass("Título PRODUCTS validado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("02_titulo_products")).build());

        ExtentReportManager.getReport().flush();
    }

    @Test
    public void ordenarPreciosMayorAMenor() {
        ExtentTest test = ExtentReportManager.getReport().createTest("Ordenar precios High to Low");
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = new ProductsPage();

        loginPage.login("standard_user", "secret_sauce");
        test.pass("Login con standard_user",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("01_login_ok")).build());

        Assert.assertEquals(productsPage.obtenerTituloProducts(), "PRODUCTS");

        productsPage.ordenarPorPrecioMayorAMenor();
        test.pass("Filtro Price (high to low) aplicado",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("02_filtro_high_to_low")).build());

        double primerPrecio = productsPage.obtenerPrimerPrecio();
        double ultimoPrecio = productsPage.obtenerUltimoPrecio();

        Assert.assertTrue(primerPrecio > ultimoPrecio,
                "Se esperaba primerPrecio > ultimoPrecio. Obtenido: " + primerPrecio + " vs " + ultimoPrecio);
        test.pass("Validación primerPrecio (" + primerPrecio + ") > ultimoPrecio (" + ultimoPrecio + ")",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("03_precios_ordenados")).build());

        ExtentReportManager.getReport().flush();
    }
}
