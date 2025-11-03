package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import ExcelFile.ExcelWriter;

public class HospitalSearchPage extends HomePage {

    public  static final List<String> hospitalNames = new ArrayList<>();
    public static List<WebElement> filtered = new ArrayList<>();
    //public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   
    @Test(priority = 0, groups="hospital")
    public void setLocationAndSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement locElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search location']")));
        locElement.click();

        WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon-ic_cross_solid']")));
        clearButton.click();

        WebElement locationInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search location']")));
        locationInput.sendKeys(props.getProperty("Location"));

        WebElement locationSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Bangalore']")));
        locationSelect.click();

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search doctors, clinics, hospitals, etc.']")));
        searchInput.sendKeys(props.getProperty("searchBar"));

        WebElement hospitalOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Hospital']")));
        hospitalOption.click();
    }
    
    @Test(priority = 1, groups="hospital")
    public void filterHospitals() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='c-estb-card']")));
        for (WebElement ele : elements) {
            WebElement name = ele.findElement(By.xpath("div[2]/div/div/span[2]/span[1]"));
            WebElement rating = ele.findElement(By.xpath("div[3]/div/div/span[1]"));

            double num = Double.parseDouble(rating.getText());
            if (name.getText().equals("Open 24x7") && num >= 3.5) {
                filtered.add(ele);
            }
        }
       
    }
   @Test(priority = 2, groups="hospital")
    public  void extractHospitalNames() throws InterruptedException {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
            Actions actions = new Actions(driver);
            WebElement readMore = driver.findElement(By.xpath("//span[text()='Read more info']"));
            actions.moveToElement(readMore).perform();
            readMore.click();

            WebElement parking = driver.findElement(By.xpath("//span[text()='Parking']"));
            boolean hasParking = wait.until(ExpectedConditions.textToBePresentInElement(parking, "Parking"));

            if (hasParking) {
            	 driver.close();
                 driver.switchTo().window(parentWindow);
                 hospitalNames.add(hName.getText());
            }

           
        }
    }
		
        @Test(priority = 3, groups="hospital")
		public void searchHospitals() throws InterruptedException {
		    
		    ExcelWriter.writeHospitalNamesToSheet(hospitalNames);
		}
}
