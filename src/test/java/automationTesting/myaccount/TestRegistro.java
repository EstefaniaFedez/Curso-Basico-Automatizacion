package automationTesting.myaccount;

import static automationTesting.utils.Utilidades.typeInField;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRegistro {

    public static WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();

    }

    public void starPage(){
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/my-account/");
    }

    @Test
    public void registroExitoso(){
        starPage();

        String correo = "acastro1@yopmail.com";
        String [] partesCorreo = correo.split("@");
        String user = partesCorreo[0];
        String passw = "Ultrasecreto135++P";


        driver.findElement(By.id("reg_email")).click();
        driver.findElement(By.id("reg_email")).sendKeys(correo);

        driver.findElement(By.id("reg_password")).click();
        driver.findElement(By.id("reg_password")).clear();
        typeInField(driver.findElement(By.id("reg_password")), passw);

        driver.findElement(By.name("register")).click();

        String expectedValue = "Hello " + user +" (not " + user +"? Sign out)";
        WebElement labelResult = driver.findElement(By.xpath("//div[@class='woocommerce-MyAccount-content']/p[1]"));

        assertEquals(expectedValue, labelResult.getText());

    }

    @After
    public void close(){
        driver.quit();
    }

}
