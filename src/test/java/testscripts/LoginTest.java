package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.ConstantPath;
import utility.ExcelOperations;

public class LoginTest extends TestBase{
	
	@Test
	public void verifyLogin() {
		loginPage.login("bankarjaykumar@gmail.com", "Jay@131197");
		boolean loginFlag = loginPage.isLoginSuccessfullyDisplayed();
		Assert.assertTrue(loginFlag);
	}
	
	@Test
	public void verifyErrorMessages() {
		System.out.println("STEP : Click on login button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY : Email required Error Message is visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);
		
		System.out.println("VERIFY : Password required Error Message is visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);
	}
	
	@Test
	public void verifyPasswordErrorMessage() {
		System.out.println("STEP : Enter valid user email");
		loginPage.enterUserEmail("bankarjaykumar@gmail.com");
		
		System.out.println("STEP : Click on login button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY : Email required Error Message is not visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorMessageFlag);
		
		System.out.println("VERIFY : Password required Error Message is visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);
	}
	
	@Test
	public void verifyEmailErrorMessage() {
		System.out.println("STEP : Enter valid password");
		loginPage.enterPassword("Jay@131197");
		
		System.out.println("STEP : Click on login button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("VERIFY : Email required Error Message is visible");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);
		
		System.out.println("VERIFY : Password required Error Message is not visible");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertFalse(passwordErrorMessageFlag);
	}
	
	@Test(dataProvider = "LoginDataProvider")
	public void verifyLoginUsingDataDrivenTest(String username, String password, String loginStatus) {
		System.out.println("STEP : Login with given credentials");
		loginPage.login(username, password);
		
		String currentURL = "";
		boolean loginFlag;
		
		if(loginStatus.equalsIgnoreCase("pass")) {
			System.out.println("VERIFY : Login Successful toast message displayed");
			loginFlag = loginPage.isLoginSuccessfullyDisplayed();
			Assert.assertTrue(loginFlag, "Login successfully message was not displayed");
			
			System.out.println("VERIFY : Incorrect email or password toast message is not displayed");
			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
			Assert.assertFalse(loginFlag, "Incorrect email or password message was displayed");

			System.out.println("VERIFY : It should redirect to dashboard page");
			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"), "Current URL: "+currentURL);
		}else{
			System.out.println("VERIFY : Incorrect email or password message toast message displayed");
			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
			Assert.assertTrue(loginFlag, "Incorrect email or password message was not displayed");
			
			System.out.println("VERIFY : Login Successful toast message is not displayed");
			loginFlag = loginPage.isLoginSuccessfullyDisplayed();
			Assert.assertFalse(loginFlag, "Login successfully message was displayed");
			
			System.out.println("VERIFY : It should redirect to login page");
			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("auth/login"), "Current URL: "+currentURL);
		}
	}
	
	@DataProvider(name = "LoginDataProvider")
	public Object[] [] getLoginData() throws IOException{
		return ExcelOperations.getAllRows(ConstantPath.LOGIN_WORKBOOK_PATH, "Login");
	}
}
