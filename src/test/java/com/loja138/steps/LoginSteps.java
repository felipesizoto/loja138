package com.loja138.steps;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {

    private WebDriver driver;
    private boolean resultadoAtual;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("que o usuário está na página de login")
    public void paginaDeLogin() {
        driver.get("https://demo-store8x.motionseed.com/en/");
        driver.findElement(By.xpath("//a[span[text()='Sign in']]")).click();
    }

    @When("ele insere o email {string} e a senha {string}")
    public void insereEmailESenha(String email, String senha) {
        driver.findElement(By.id("field-email")).sendKeys(email);
        driver.findElement(By.id("field-password")).sendKeys(senha);
    }

    @When("clica no botão de login")
    public void clicaNoBotaoLogin() {
        driver.findElement(By.id("submit-login")).click();
    }

    @Then("o resultado deve ser {string}")
    public void validaResultado(String resultadoEsperado) {
        try {
            WebElement erro = driver.findElement(By.cssSelector(".alert.alert-danger"));
            resultadoAtual = false;  // apareceu erro, login falhou
        } catch (Exception e) {
            resultadoAtual = true;   // não apareceu erro, login deu certo
        }

        if (resultadoEsperado.equalsIgnoreCase("sucesso")) {
            assertTrue(resultadoAtual);
        } else {
            assertFalse(resultadoAtual);
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
