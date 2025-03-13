package com.flipkart.qa.testcases;

import java.util.List;

//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.scenario.FlipkartHomePage;
import com.flipkart.qa.scenario.SearchResultsPage;

public class SearchAndFilterTest extends TestBase {
	
	FlipkartHomePage flipkartHomePage;
	SearchResultsPage searchPage;
	
	
	public SearchAndFilterTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		flipkartHomePage = new FlipkartHomePage();
	}
	
	@Test(priority=1)
	public void searchTest() throws InterruptedException {
		searchPage = flipkartHomePage.searchForProduct("Samsung Galaxy S10");
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Samsung Galaxy S10- Buy Products Online at Best Price in India - All Categories | Flipkart.com");
		flipkartHomePage.goToMobilesCategory();

		Thread.sleep(5000);
		searchPage.applyFilters("SAMSUNG", true);
		Thread.sleep(5000);
		boolean flag = searchPage.validateFilter("SAMSUNG");
		Assert.assertTrue(flag);
		
		searchPage.sortByPriceHighToLow();
		Thread.sleep(5000);
		
		List<WebElement> productNames = searchPage.getProductNames();
		List<WebElement> displayPrices = searchPage.getDisplayPrices();
		List<WebElement> productLinks = searchPage.getProductLinks();
		
		for (int i = 0; i < productNames.size(); i++) {
            System.out.println("Product Name: " + (i+1) + " " + productNames.get(i).getText());
            System.out.println("Display Price: " + displayPrices.get(i).getText());
            System.out.println("Link to Product Details Page: " + productLinks.get(i).getAttribute("href"));
            System.out.println();
        }
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
