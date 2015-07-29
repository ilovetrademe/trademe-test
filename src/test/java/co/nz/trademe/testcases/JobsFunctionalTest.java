package co.nz.trademe.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import co.nz.trademe.base.TestBase;

/**
 * 
 * 
 * @author Yu Liu
 * 
 */

public class JobsFunctionalTest extends TestBase {

	private String baseUrl;
	// private String userName;
	// private String userPassword;
	private int timeout = 20;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		baseUrl = configProperties.getProperty("baseurl");
		driver.get(baseUrl);
		driver.manage().window().maximize();
		wait(timeout);
	}

	@Test
	public void testJobsSearchBar() {
		try {
			Log.info("Search valid job via search bar");
			this.click(By.id("searchString"));
			this.input(By.id("searchString"), "Test Analyst - Trade Me Jobs");
			this.select(By.id("SearchType"), "Jobs");
			this.click(By.cssSelector("button.btn.btn-trademe"));
			Thread.sleep(3000);
			this.verifyTrue(isTextPresent("Test Analyst - Trade Me Jobs"), "Failed to search valid job via search bar");

			Log.info("Search invalid job via search bar");
			this.click(By.id("searchString"));
			this.input(By.id("searchString"), "I would like to search proterty");
			this.select(By.id("SearchType"), "Jobs");
			this.click(By.cssSelector("button.btn.btn-trademe"));
			this.verifyTrue(isTextPresent("No results matching"), "Failed to search invalid job via search bar");

		} catch (Exception ex) {
			verificationErrors.append(ex.toString() + "\n");
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Test
	public void testJobsSearchKeywords() {
		try {
			Log.info("Navigate to the Job search keywords");
			this.click(By.cssSelector("li.jobs > a"));
			Log.info("Search valid job via search keywords");
			// input the keywords
			this.click(By.id("job-keywords"));
			this.input(By.id("job-keywords"), "Test Analyst - Trade Me Jobs");
			// select location
			this.select(By.id("SearchTabs1_JobsSearchFormControl_jobsTopTierLocation"), "Wellington");
			// select district
			this.select(By.id("SearchTabs1_JobsSearchFormControl_jobsDistrictsLocation"), "Wellington");
			// select category
			this.select(By.id("SearchTabs1_JobsSearchFormControl_jobsTopTierCategory"), "IT");
			// select subcategory
			this.select(By.id("SearchTabs1_JobsSearchFormControl_jobsSecondTierCategory"), "Testing");
			this.click(By.cssSelector("button.btn.btn-form.btn-jobs"));
			Thread.sleep(3000);
			this.verifyTrue(isTextPresent("Test Analyst - Trade Me Jobs"), "Failed to search valid job via search keywords");

		} catch (Exception ex) {
			verificationErrors.append(ex.toString() + "\n");
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
