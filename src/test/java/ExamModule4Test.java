import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IExpectedExceptionsHolder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExamModule4Test {

    WebDriver driver;


    @BeforeMethod
    public void setUpBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://shop.pragmatic.bg/");

    }

    @Test
    public void userAccountFunctionalities(){
        WebDriverWait wait = new WebDriverWait(driver, 15) ;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-user")));
        driver.findElement(By.cssSelector(".fa-user")).click();

        driver.findElement(By.xpath("//a[@href='http://shop.pragmatic.bg/index.php?route=account/register']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));
        String firstName= RandomStringUtils.randomAlphabetic(5);
        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        String lastName = RandomStringUtils.randomAlphabetic(6);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-email")).sendKeys(RandomStringUtils.randomAlphabetic(7) + "@alabala.com");
        driver.findElement(By.id("input-telephone")).sendKeys(RandomStringUtils.randomNumeric(10));
        String password = RandomStringUtils.randomAlphanumeric(9);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        driver.findElement(By.xpath("//*[@name='newsletter'][@value='0']")).click();
        driver.findElement(By.xpath("//*[@name='agree'][@value='1']")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();


        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://shop.pragmatic.bg/index.php?route=account/address']"))));
        driver.findElement(By.xpath("//a[@href='http://shop.pragmatic.bg/index.php?route=account/address']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));
        driver.findElement(By.cssSelector(".btn-primary")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));
        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-address-1")).sendKeys("Adding some new address");
        driver.findElement(By.id("input-city")).sendKeys("Sofia");
        driver.findElement(By.id("input-postcode")).sendKeys("1000");

        Select countryDropDown = new Select(driver.findElement(By.id("input-country")));
        countryDropDown.selectByValue("33");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("input-zone"), "Yambol"));

        Select regionDropDown = new Select(driver.findElement(By.id("input-zone")));
        regionDropDown.selectByValue("498");

        driver.findElement(By.xpath("//*[@name='default'][@value='0']")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();

        String actualMessage = driver.findElement(By.xpath("//*[.=' Your address has been successfully added']")).getText();

        Assert.assertTrue(actualMessage.contains("Your address has been successfully added"));


    }


   @AfterMethod
   public void dearDown(){ driver.quit();}
    }





