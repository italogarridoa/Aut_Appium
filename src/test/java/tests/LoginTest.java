package tests;

import config.BaseTest;
import config.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginExitoso() {
        ExtentTest test = ExtentReportManager.getReport().createTest("Login exitoso");
        LoginPage loginPage = new LoginPage();

        test.info("Paso 1: La app abrió correctamente",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("01_app_abierta")).build());

        loginPage.ingresarUsuario("standard_user");
        test.pass("Paso 2: Usuario ingresado correctamente",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("02_usuario_ingresado")).build());

        loginPage.ingresarPassword("secret_sauce");
        test.pass("Paso 3: Password ingresada correctamente",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("03_password_ingresado")).build());

        loginPage.clickIngresar();
        test.pass("Paso 4: Botón login presionado correctamente",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("04_login_presionado")).build());

        loginPage.clickImgMochila();
        test.pass("Paso 5: Imagen Home presionado correctamente",
                MediaEntityBuilder.createScreenCaptureFromPath(tomarCaptura("05_imagen_visible")).build());

        BasePage.scrollDown(400);
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
