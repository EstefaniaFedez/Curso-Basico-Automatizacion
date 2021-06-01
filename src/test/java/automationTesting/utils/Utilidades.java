package automationTesting.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilidades {

    public static void typeInField(WebElement elem, String value){
        String val = value;
        WebElement element = elem;
        element.click();
        element.clear();
        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            waitOwn(100);
            element.sendKeys(s);
        }
    }

    public static void waitOwn(long milliseg){



        try {
            Thread.sleep(milliseg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickElement(WebDriver driver, String locator, String type){
        switch (type){
            case "id":
                driver.findElement(By.id(locator)).click();
                break;
            case "xpath":
                driver.findElement(By.xpath(locator)).click();
                break;
            case "className":
                driver.findElement(By.className(locator)).click();
                break;
            default:
                driver.findElement(By.id(locator)).click();
        }
    }


}
