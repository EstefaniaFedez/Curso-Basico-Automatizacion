package automationTesting.ventanas;

import automationTesting.utils.Utilidades;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static automationTesting.utils.Utilidades.clickElement;
import static automationTesting.utils.Utilidades.waitOwn;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class TestVentanas {

    public static WebDriver driver;


    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();

    }

    public void starPage(){
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Windows.html");

    }

    @Test
    public void testNewTab(){
        starPage();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana inicial: " + ventanaInicial);
        System.out.println("titulo ventana: "+ driver.getTitle());


        clickElement(driver, "//a[@href='#Tabbed']", "xpath");
        clickElement(driver, "btn-info", "className");

        for (String manejadorVentana : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentana)){
                driver.switchTo().window(manejadorVentana);
                break;
            }
        }

        System.out.println("ID ventana 2: " + driver.getWindowHandle());
        System.out.println("titulo ventana 2: "+ driver.getTitle());

        driver.close();
        driver.switchTo().window(ventanaInicial);

        String textMsgVentanaInicialExpect = "If you set the target attribute to \"_blank\" , the link will open in a new browser window or a new tab";
        String messageLabel = driver.findElement(By.id("Tabbed")).findElement(By.tagName("p")).getText();
        Assert.assertEquals(textMsgVentanaInicialExpect,messageLabel);
    }

    @Test
    public void testNewWindow(){
        starPage();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana inicial: " + ventanaInicial);
        System.out.println("titulo ventana: "+ driver.getTitle());


        clickElement(driver, "//a[@href='#Seperate']", "xpath");
        clickElement(driver, "btn-primary", "className");

        for (String manejadorVentana : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentana)){
                driver.switchTo().window(manejadorVentana);
                break;
            }
        }

        System.out.println("ID ventana 2: " + driver.getWindowHandle());
        System.out.println("titulo ventana 2: "+ driver.getTitle());

        /*
         *Se automatiza segunda página
         */

        js.executeScript("window.scrollBy(0,320)");

        List <WebElement> contenedorCursos = driver.findElement(By.className("getting-started-topic-container")).findElements(By.tagName("h3"));
        List<String> cursos = new ArrayList();
        for (int i=0; i<= contenedorCursos.size()-1; i++){
            cursos.add(contenedorCursos.get(i).getText());
        }

        List<String> cursosEsperados= new ArrayList();
        cursosEsperados.add("Selenium WebDriver");
        cursosEsperados.add("Selenium IDE");
        cursosEsperados.add("Selenium Grid");

        Collections.sort(cursos);
        Collections.sort(cursosEsperados);

        Assert.assertArrayEquals(cursos.toArray(), cursosEsperados.toArray());

        driver.close();
        driver.switchTo().window(ventanaInicial);
        String messageAcceptExpect = "click the button to open a new window with some specifications";
        String messageAccept = driver.findElement(By.id("Seperate")).findElement(By.tagName("p")).getText().trim();
        Assert.assertEquals(messageAcceptExpect, messageAccept);

    }



    @After
    public void close(){
        driver.quit();
    }
}
