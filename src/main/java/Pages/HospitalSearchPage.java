package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;


public class HospitalSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public static List<String> hospitalNames = new ArrayList<>();
    public static List<WebElement> filtered = new ArrayList<>();

 
    public HospitalSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath = "//input[@placeholder='Search location']")
    private WebElement locationInput;

    @FindBy(xpath = "//i[@class='icon-ic_cross_solid']")
    private WebElement clearButton;

    @FindBy(xpath = "//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[normalize-space()='Hospital']")
    private WebElement hospitalOption;

    @FindBy(xpath = "//span[text()='Read more info']")
    private WebElement readMore;

    @FindBy(xpath = "//span[text()='Parking']")
    private WebElement parking;

    
    public void setLocation(String loc) {
        wait.until(ExpectedConditions.elementToBeClickable(locationInput)).click();
        wait.until(ExpectedConditions.elementToBeClickable(clearButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(locationInput)).sendKeys(loc);

        WebElement locationSelect = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[normalize-space()='" + loc + "']")));
        locationSelect.click();
    }

    public void searchHospital(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(hospitalOption)).click();
    }

    public void filterHospitals() {
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='c-estb-card']")));

        for (WebElement ele : elements) {
            WebElement name = ele.findElement(By.xpath("div[2]/div/div/span[2]/span[1]"));
            WebElement rating = ele.findElement(By.xpath("div[3]/div/div/span[1]"));

            double num = Double.parseDouble(rating.getText());
            if (name.getText().equals("Open 24x7") && num >= 3.5) {
                filtered.add(ele);
            }
        }
    }

    public void extractHospitalNames() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();

        for (WebElement e : filtered) {
            WebElement hName = e.findElement(By.xpath("div[2]/div/a/h2"));
            hName.click();

            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(parentWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            Thread.sleep(2000);
            new Actions(driver).moveToElement(readMore).perform();
            readMore.click();

            boolean hasParking = wait.until(ExpectedConditions.textToBePresentInElement(parking, "Parking"));

            if (hasParking) {
                driver.close();
                driver.switchTo().window(parentWindow);
                hospitalNames.add(hName.getText());
            }
        }
        
        
    }

	public void navi() {
		
		   driver.navigate().back();
	}

}