package com.avira.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.avira.base.setup.BaseSetUpClass;

/**
 * Page Class for Non-Malware site
 * 
 * @author chetanit
 *
 */
public class NonMalewareSitesPage extends BaseSetUpClass {

	protected static String EXPECTED_TITLE_SAFE_PAGE = properties.get("EXPECTED_TITLE_SAFE");

	public NonMalewareSitesPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Verify web page/source of a link is not phishy or dangerous
	 * 
	 * @throws Exception
	 */
	public void verifySafeGreen() throws Exception {
		Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE_SAFE_PAGE);
		Assert.assertTrue(driver.findElement(By.xpath(properties.get("USER_SAFE_VALIDATION"))).isDisplayed());
		log.info("User is on safe page");
	}
}
