package Pages;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ExcelFile.ExcelWriter;
public class DiagnosticPage extends HomePage {
	
public static List<String> cityNames = new ArrayList<>();
    
    @Test(priority = 0, groups = "diagnostic")
    private void clickLabTests() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement lab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[text()='Lab Tests'])[1]")));
        lab.click();
    }
    
   @Test(priority = 1,groups="diagnostic")
    private void getCityNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> cities = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
         By.xpath("//div[@class='u-d__inline city-selector__city u-marginb--std-half u-pointer']")));
  
        for (WebElement city : cities) {
            cityNames.add(city.getText());
        }
        
    }
   
   @Test(priority = 2,groups = "diagnostic")
   public void diagnostic() {
       ExcelWriter.writeCityNamesToSheet(cityNames);
       ExcelWriter.saveWorkbook("C:/Users/2440823/eclipse-workspace/MajorProject/Finding_Hospital.xlsx");
   }
}