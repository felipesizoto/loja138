import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCadastro {

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/loginUsuarios.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeLoginDDT(String email, String senha, boolean resultadoEsperado) {
        // Configura
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo-store8x.motionseed.com/en/");

        // Executa
        driver.findElement(By.xpath("//a[span[text()='Sign in']]")).click();
        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(senha);
        driver.findElement(By.id("submit-login")).click();

        // Valida com tempo de espera
        boolean resultadoAtual;

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement erro = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger"))
            );
            resultadoAtual = false; // Erro apareceu → login falhou
        } catch (Exception e) {
            resultadoAtual = true; // Nenhum erro apareceu → login deu certo
        }

        System.out.println("Email: " + email + " | Senha: " + senha);
        System.out.println("Esperado: " + resultadoEsperado + " | Atual: " + resultadoAtual);

        assertEquals(resultadoEsperado, resultadoAtual);

        // Encerra
        driver.quit();
    }
}
