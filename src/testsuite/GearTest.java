package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utility;

import java.time.Duration;

public class GearTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessfullyToShoppingCart(){
        WebElement gear = driver.findElement(By.xpath("//span[contains(text(),'Gear')]"));
        //WebElement bags = driver.findElement(By.xpath("//span[contains(text(),'Bags')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gear).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Bags')]")));
            element.click();
        }catch (ElementNotInteractableException e){
            System.out.println(e.getMessage());
        }
        clickOnElement(By.xpath("//a[contains(text(),'Overnight Duffle')]"));

        String aProduct = getTextFromElement(By.xpath("//span[contains(text(),'Overnight Duffle')]"));
        String eProduct = "Overnight Duffle";
        Assert.assertEquals(eProduct,aProduct);

        driver.findElement(By.id("qty")).clear();
        sendTextToElement(By.id("qty"),"3");
        clickOnElement(By.xpath("//span[contains(text(),'Add to Cart')]"));

        String aMsg = getTextFromElement(By.xpath("//div[contains(text(),'Overnight')]"));
        String eMsg = "You added Overnight Duffle to your shopping cart.";
        Assert.assertEquals(eMsg,aMsg);
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));

        String eName = "Overnight Duffle";
        String aName = getTextFromElement(By.xpath("//a[text() = 'Overnight Duffle']"));
        Assert.assertEquals(eName,aName);

        WebElement Qty = driver.findElement(By.xpath("//input[@title = 'Qty']"));
        String aQty = Qty.getAttribute("value");
        String eQty = "3";
        Assert.assertEquals(eQty,aQty);

        String aPrice = getTextFromElement(By.xpath("//span[contains(text(),'$135.00')]"));
        String ePrice = "$135.00";
        Assert.assertEquals(ePrice,aPrice);

        Qty.clear();
        Qty.sendKeys("5");
        clickOnElement(By.xpath("//span[contains(text(),'Update Shopping Cart')]"));

        String aTotal = getTextFromElement(By.xpath("//span[contains(text(),'$225.00')]"));
        String eTotal = "$225.00";
        Assert.assertEquals(eTotal,aTotal);

    }

    @After
    public void tearDown() {

        closeBrowser();
    }
}
