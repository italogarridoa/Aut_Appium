# swag-labs-appium

Automatización móvil de **Sauce Labs Demo App** con Appium, Java y TestNG (Page Object Model).

## Estructura

```
swag-labs-appium/
├── pom.xml
├── .gitignore
├── apps/
│   └── Android.SauceLabs.Mobile.Sample.app.apk
├── src/
│   ├── main/java/
│   │   ├── config/
│   │   │   ├── BaseTest.java
│   │   │   └── CapabilitiesManager.java
│   │   └── pages/
│   │       ├── LoginPage.java
│   │       ├── ProductsPage.java
│   │       ├── CartPage.java
│   │       └── CheckoutPage.java
│   └── test/java/
│       └── tests/
│           ├── LoginTest.java
│           ├── ProductsTest.java
│           └── PurchaseFlowTest.java
├── testng.xml
└── README.md
```

## Requisitos

Antes de ejecutar los tests, asegúrate de tener:

| Requisito | Detalle |
|-----------|---------|
| **JDK** | Java 17 o superior |
| **Maven** | 3.8+ (`mvn -v`) |
| **Appium Server** | Corriendo en `http://127.0.0.1:4723` |
| **Android SDK** | Emulador o dispositivo físico conectado |
| **APK** | Colocar `Android.SauceLabs.Mobile.Sample.app.apk` en la carpeta `apps/` |

### Variables de entorno sugeridas

- `ANDROID_HOME` / `ANDROID_SDK_ROOT` apuntando al Android SDK
- Emulador activo (ej. `emulator-5554`) o dispositivo con depuración USB

## Instrucciones

1. Clonar el repositorio (o abrir el proyecto local).
2. Colocar el APK en `apps/Android.SauceLabs.Mobile.Sample.app.apk`.
3. Iniciar Appium Server:
   ```bash
   appium
   ```
4. Encender el emulador o conectar el dispositivo.
5. Ejecutar la suite de pruebas:
   ```bash
   mvn clean test
   ```

## Ejecutar tests

Suite completa (limpia, compila y ejecuta):

```bash
mvn clean test
```

Un test específico:

```bash
mvn clean test -Dtest=tests.PurchaseFlowTest#checkoutCompleto
```

Otros ejemplos:

```bash
mvn clean test -Dtest=tests.LoginTest
mvn clean test -Dtest=tests.ProductsTest#ordenarPreciosMayorAMenor
```

## Reportes

Tras la ejecución, el reporte Extent queda en:

```
target/reports/ExtentReport.html
```

Las capturas de pantalla se guardan en `target/reports/screenshots/`.

## Tests incluidos

| Clase | Escenarios |
|-------|------------|
| `LoginTest` | Login exitoso, usuario bloqueado (`locked_out_user`) |
| `ProductsTest` | Título PRODUCTS, orden Price (high to low) |
| `PurchaseFlowTest` | Agregar producto al carrito, checkout completo |

## Autor

QA Automation - Swag Labs Appium
