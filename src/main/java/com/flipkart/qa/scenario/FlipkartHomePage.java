package com.flipkart.qa.scenario;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;

public class FlipkartHomePage extends TestBase {
	
	@FindBy(xpath="//input[@type='text']")
	WebElement searchBox;
	
	@FindBy(xpath="//a[text()='Mobiles']")
	WebElement mobilesCategory;
	
	
	public FlipkartHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public SearchResultsPage searchForProduct(String productName) {
        searchBox.sendKeys(productName);
        searchBox.submit();
        
        return new SearchResultsPage();
    }
	
	public void goToMobilesCategory() {
        mobilesCategory.click();
    }

}
