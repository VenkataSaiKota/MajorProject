package Pages;


import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CorporateFormDemoPage {
    WebDriver driver;
    @FindBy(xpath="//span[contains(text(),'For Corporates')]/following-sibling::span")
    private WebElement downArrow;
    
    @FindBy(xpath="//div[@class='u-d-item']/child::a[contains(@href,'corporate')]")
    private WebElement targetElement;
    
    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(name = "organizationName")
    private WebElement orgField;

    @FindBy(name = "contactNumber")
    private WebElement phoneField;

    @FindBy(name = "officialEmailId")
    private WebElement emailField;

    @FindBy(id = "organizationSize")
    private WebElement orgSizeDropdown;

    @FindBy(id = "interestedIn")
    private WebElement interestDropdown;

    @FindBy(xpath = "//button[contains(text(),'Schedule a demo')]")
    private WebElement scheduleButton;

    public CorporateFormDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String name, String org, String phone, String email, String orgSize, String interest) {
    	waitForVisibility(downArrow).click();
    	waitForVisibility(targetElement).click();
        waitForVisibility(nameField).sendKeys(name);
        waitForVisibility(orgField).sendKeys(org);
        waitForVisibility(phoneField).sendKeys(phone);
        waitForVisibility(emailField).sendKeys(email);

        waitForVisibility(orgSizeDropdown).click();
        orgSizeDropdown.findElement(By.xpath("//option[contains(text(),'" + orgSize + "')]")).click();

        waitForVisibility(interestDropdown).click();
        interestDropdown.findElement(By.xpath("//option[text()='" + interest + "']")).click();
    }

    public WebElement getScheduleButton() {
        return scheduleButton;
    }

    private WebElement waitForVisibility(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }
}

