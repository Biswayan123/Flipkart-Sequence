package com.flipkart.qa.scenario;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.util.TestUtil;
import com.flipkart.qa.base.TestBase;

public class SearchResultsPage extends TestBase {
	
	@FindBy(xpath="//div[text()='Price -- High to Low']")
	WebElement highToLowFilter;
		
	@FindBy(css="._24_Dny._3tCU7L")
	WebElement flipkartAssuredFilter;
	
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void applyFilters(String brand, boolean flipkartAssured) throws InterruptedException {
        WebElement brandFilter = driver.findElement(By.xpath("//div[text()='"+brand+"']//preceding-sibling::div[@class='_24_Dny']"));
        brandFilter.click();
        Thread.sleep(3000);

        if (flipkartAssured) {
            flipkartAssuredFilter.click();
        }
	}
	
	public boolean validateFilter(String brand) {
		WebElement brandfilterText = driver.findElement(By.xpath("//div[text()='"+brand+"' and @class='_3sckoD']"));
		WebElement flipkartAssuredText = driver.findElement(By.xpath("//div[text()='Plus (FAssured)' and @class='_3sckoD']"));
		return brandfilterText.isDisplayed() && flipkartAssuredText.isDisplayed();
	}
	
	public void sortByPriceHighToLow() {
		highToLowFilter.click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
    }
	
	public List<WebElement> getProductNames() {
        return driver.findElements(By.xpath("//div[@class='_4rR01T']"));
    }

    public List<WebElement> getDisplayPrices() {
        return driver.findElements(By.xpath("//div[@class='_30jeq3 _1_WHN1']"));
    }

    public List<WebElement> getProductLinks() {
        return driver.findElements(By.xpath("//a[@class='_1fQZEK']"));
    }
}

