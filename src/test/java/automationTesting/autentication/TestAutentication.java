package automationTesting.autentication;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutentication {

    public static WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void autenticacionExitosa() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/");

        driver.findElement(By.id("menu-item-50")).click();

        //Thread.sleep(5000);

        //assertEquals("Login", driver.findElement(By.xpath("//*[@class='u-column1 col-1']/h2")).getText());

        final String expectedValue = "Login";
        final WebElement loginElement = driver.findElement(By.cssSelector("div.u-column1")).findElement(By.tagName("h2"));
        assertEquals(expectedValue, loginElement.getText());

        boolean loginClass = driver.findElements(By.tagName("form")).get(1).getAttribute("class").equals("login");
        assertTrue(loginClass);

        boolean methodForm = driver.findElements(By.tagName("form")).get(1).getAttribute("method").equals("post");
        assertTrue(methodForm);

        assers

    }

    @After
    public void close(){
        driver.quit();
    }

}
