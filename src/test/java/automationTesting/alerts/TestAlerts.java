package automationTesting.alerts;

import static automationTesting.utils.Utilidades.waitOwn;
import static automationTesting.utils.Utilidades.clickElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAlerts {

    public static WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();

    }

    public void starPage(){
        driver.manage().window().maximize();
        driver.get("http://demo.automationtesting.in/Alerts.html");
    }

    @Test
    public void testAlertOK(){
        starPage();
        clickElement(driver, "//a[@href='#OKTab']", "xpath");
        clickElement(driver, "btn-danger", "className");
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        String messageExpect= "I am an alert box!";
        Assert.assertEquals(messageExpect, textAlert);
        alert.accept();
        String messageRedButtonExpect = "click the button to display an alert box:";
        String messageRedButton = driver.findElement(By.className("btn-danger")).getText();
        Assert.assertEquals(messageRedButtonExpect, messageRedButton);
    }

    @Test
    public void testAlertOKAndCancel(){
        starPage();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        clickElement(driver, "//a[@href='#CancelTab']", "xpath");
        clickElement(driver, "btn-primary", "className");
        wait.until(ExpectedConditions.alertIsPresent()); //explicitas
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        String messageExpect= "Press a Button !";
        Assert.assertEquals(messageExpect, textAlert);
        alert.dismiss();
        String messageCancelExpect = "You Pressed Cancel";
        String messageCancel = driver.findElement(By.id("demo")).getText().trim();
        Assert.assertEquals(messageCancelExpect, messageCancel);
        clickElement(driver, "btn-primary", "className");
        wait.until(alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();
        String messageAcceptExpect = "You pressed Ok";
        String messageAccept = driver.findElement(By.id("demo")).getText().trim();
        waitOwn(3000); //implicitas
        Assert.assertEquals(messageAcceptExpect, messageAccept);

    }

    @Test
    public void testAlertWithTextBox(){
        starPage();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        clickElement(driver, "//a[@href='#Textbox']", "xpath");
        clickElement(driver, "btn-info", "className");
        wait.until(ExpectedConditions.alertIsPresent()); //explicitas
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Juana la cubana");
        alert.accept();
        String messageExpect = "Hello Juana la cubana How are you today";
        String message = driver.findElement(By.id("demo1")).getText().trim();
        Assert.assertEquals(messageExpect, message);
    }


    @After
    public void close(){
        driver.quit();
    }

}
