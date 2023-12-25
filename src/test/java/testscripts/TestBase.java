package testscripts;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ControlActions;
import pages.DashboardPage;
import pages.LoginPage;

public class TestBase {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	static int count = 1;
	
	@BeforeMethod
	public void setUp() {
		ControlActions.launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus()) {
			ControlActions.takeScreenshot(result.getName()+"_"+ count++);
		}
		ControlActions.closeBrowser();
	}
	
	void login() {
		loginPage.login("bankarjaykumar@gmail.com", "Jay@131197");
		boolean loginFlag = loginPage.isLoginSuccessfullyDisplayed();
		Assert.assertTrue(loginFlag);
	}
}
