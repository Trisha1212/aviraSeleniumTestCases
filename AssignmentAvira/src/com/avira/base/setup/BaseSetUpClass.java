package com.avira.base.setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.avira.common.PropertyReader;
import com.avira.listeners.ScreenShotListeners;

/**
 * Provides test class file and TestNG annotation definition
 * 
 * @author chetanit
 */
@Listeners(ScreenShotListeners.class)
public class BaseSetUpClass {
	public static WebDriver driver;
	public WebDriverWait wait;
	public WebElement element;

	// Property reader file location
	public static PropertyReader properties = new PropertyReader(System.getProperty("user.dir")
			+ "\\Resources\\testdata.properties");

	// Logs
	public static Logger log = Logger.getLogger(BaseSetUpClass.class.getName());

	// Fetching chrome driver.exe
	private static String PATH_CHROME_DRIVER = properties.get("DRIVER_PATH") + "chromedriver.exe";

	static File AVIRA_BROWSER_SAFETY = new File(System.getProperty("user.dir")
			+ "\\Resources\\Avira-Browser-Safety_v1.9.2.crx");

	/**
	 * Create a instance of fire fox driver and open fire fox browser
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@BeforeSuite(alwaysRun = true)
	public static void instantiateDriverObject() throws FileNotFoundException, IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(AVIRA_BROWSER_SAFETY);
		options.addArguments("--test-type");
		driver = new ChromeDriver(options);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public static WebDriver getDriver() throws Exception {
		return driver;
	}

	/**
	 * Maximize the browser
	 * 
	 * @throws Exception
	 */
	@BeforeMethod(alwaysRun = true)
	public static void MaximizeBrowser() throws Exception {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Clear the cookies
	 * 
	 * @throws Exception
	 */
	@AfterMethod(alwaysRun = true)
	public static void clearCookies() throws Exception {
		getDriver().manage().deleteAllCookies();
	}

	/**
	 * Close the driver
	 */
	@AfterSuite(alwaysRun = true)
	public static void closeDriverObjects() {
		if (driver != null) {
			try {
				driver.close();
				driver.quit();

				return;
			} catch (NoSuchWindowException e) {
				log.error("Not able to close the browser: ", e);
			}
		}
	}

}
