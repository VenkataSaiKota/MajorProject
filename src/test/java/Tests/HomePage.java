package Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import Report.ReportManager;


public class HomePage {
      public static WebDriver driver;
      public static WebDriverWait wait;
      Properties props;
      
	@BeforeSuite
	 public void setDriver() throws IOException {
		 props=new Properties();
	     FileInputStream fis = new FileInputStream("src/test/java/configFile/config.properties");
		 props.load(fis);
		 driver = new ChromeDriver();
	     driver.get(props.getProperty("baseURL"));
	     driver.manage().window().maximize();
	     ReportManager.initReport();
		}
	 
	 @AfterSuite
	 public void Quit() throws InterruptedException {
		 Thread.sleep(5000);
		 driver.quit();
		 ReportManager.flushReport();
	 }
}
