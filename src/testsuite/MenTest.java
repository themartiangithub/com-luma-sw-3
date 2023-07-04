package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class MenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessfullyToShoppingCart(){

        WebElement men = driver.findElement(By.xpath("//span[contains(text(),'Men')]"));
        WebElement bottoms = driver.findElement(By.xpath("//li[@class = 'level1 nav-3-2 category-item last parent ui-menu-item']//span[contains(text(),'Bottoms')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(men).moveToElement(bottoms).build().perform();
        clickOnElement(By.xpath("//li[@class = 'level1 nav-3-2 category-item last parent ui-menu-item']//span[contains(text(),'Pants')]"));

        WebElement pant = driver.findElement(By.xpath("//ol[@class = 'products list items product-items']/li[1]"));
        WebElement size = driver.findElement(By.id("option-label-size-143-item-175"));
        actions.moveToElement(pant).moveToElement(size).click().build().perform();

        WebElement color = driver.findElement(By.id("option-label-color-93-item-49"));
        actions.moveToElement(pant).moveToElement(color).click().build().perform();
        WebElement cart = driver.findElement(By.xpath("//ol[@class = 'products list items product-items']//li[1]//button[@type = 'submit']"));
        actions.moveToElement(pant).moveToElement(cart).click().build().perform();

        String eMsg = "You added Cronus Yoga Pant to your shopping cart.";
        String aMsg = getTextFromElement(By.xpath("//div[contains(text(),'Cronus Yoga Pant ')]"));
        Assert.assertEquals(eMsg,aMsg);

        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        String aTitle = driver.findElement(By.xpath("//h1[@class = 'page-title']")).getText();
        String eTitle = "Shopping Cart";
        Assert.assertEquals(eTitle,aTitle);

        String aProduct = getTextFromElement(By.xpath("//a[text() = 'Cronus Yoga Pant ']"));
        String eProduct = "Cronus Yoga Pant";
        Assert.assertEquals(eProduct,aProduct);

        String aDetails = getTextFromElement(By.xpath("//dl[@class = 'item-options']"));
        String eDetails = "Size\n" + "32\n" + "Color\n" + "Black";
        Assert.assertEquals(eDetails,aDetails);


    }

    @After
    public void tearDown() {

        closeBrowser();
    }
}
