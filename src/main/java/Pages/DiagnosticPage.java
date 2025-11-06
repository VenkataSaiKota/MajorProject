package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class DiagnosticPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public static List<String> cityNames = new ArrayList<>();

    // Constructor
    public DiagnosticPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

   
    @FindBy(xpath = "(//div[text()='Lab Tests'])[1]")
    private WebElement labTestsButton;

    @FindBy(xpath = "//div[@class='u-d__inline city-selector__city u-marginb--std-half u-pointer']")
    private List<WebElement> cityElements;

   
    public void clickLabTests() throws InterruptedException {
    	Thread.sleep(10000);
    	labTestsButton.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Lab Tests'])[1]"))).click();
        
    }

    public void getCityNames() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> cities = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='u-d__inline city-selector__city u-marginb--std-half u-pointer']")));

        for (WebElement city : cities) {
            cityNames.add(city.getText());
        }
        driver.navigate().back();
    }
}