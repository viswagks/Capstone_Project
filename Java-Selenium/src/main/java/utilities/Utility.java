package utilities;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

public class Utility {
    private final WebDriver driver;
    public Utility()
    {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
        this.driver = new FirefoxDriver();
    }

    public void configureDriver()
    {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    public void navigateTo(String url)
    {
        this.driver.get(url);
    }

    public WebElement getElement(By identifier)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

      /*wait.ignoring(NoSuchElementException.class).pollingEvery(Duration.ofMillis(
      500)) .until(ExpectedConditions.visibilityOfElementLocated(identifier));

      Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
      .withTimeout(Duration.ofSeconds(30)) .pollingEvery(Duration.ofSeconds(5))
      .ignoring(NoSuchElementException.class); WebElement obj =
      fluentWait.until(ExpectedConditions.elementToBeClickable(identifier));
     */

        return wait.until(ExpectedConditions.elementToBeClickable(identifier));

    }

    public void clickElement(By identifier)
    {
        this.getElement(identifier).click();
    }

    public void setText(By identifier, String text)
    {
        WebElement element = this.getElement(identifier);
        element.click();
        element.sendKeys(text);
    }

    public void selectFromDropdown(By identifier, String value) {
        Select select = new Select(this.getDriver().findElement(identifier));
        select.selectByVisibleText(value);
    }

    public String getValueFromDropdown(By identifier, String value) {
        Select select = new Select(this.getDriver().findElement(identifier));
        return select.getFirstSelectedOption().getText();
    }

}
