package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;

public class CartTest extends TestBase{

	@Test
	public void verifyAddToCartFunctionality() {
		login();
		String productName = "IPHONE 13 PRO";
		System.out.println("STEP : Click on AddToCart to add product to cart");
		dashboardPage.addToCart(productName);
		dashboardPage.waitForSpinnerToBeDisappear();
		
		System.out.println("STEP : Navigate to cart menu");
		CartPage cartPage =dashboardPage.clickOnCartMenu();
		cartPage.waitForCartPageLoad();
		
		System.out.println("STEP : Get count of product added into cart menu");
		int countOfProduct = cartPage.getTotalProductCountOnCartMenu();
		System.out.println("VERIFY : Count should be 1");
		Assert.assertEquals(countOfProduct, 1);
		
		System.out.println("VEFIRY : Count of product in MyCart should be 1");
		countOfProduct = cartPage.getTotalNumberOfProductsInMyCart();
		Assert.assertEquals(countOfProduct, 1);
		
		System.out.println("VERIFY : Added Product is visible in MyCart");
		boolean productVisibleFlag = cartPage.isProductAvailableInMyCart(productName);
		Assert.assertTrue(productVisibleFlag);
	}
	
	@Test
	public void verifyMultipleAddToCartProductFunctionality() {
		login();
		String productName1 = "IPHONE 13 PRO";
		String productName2 = "ZARA COAT 3";
		System.out.println("STEP : Click on AddToCart to add product to cart");
		dashboardPage.addToCart(productName1);
		dashboardPage.waitForSpinnerToBeDisappear();
		dashboardPage.addToCart(productName2);
		dashboardPage.waitForSpinnerToBeDisappear();
		
		System.out.println("STEP : Navigate to cart menu");
		CartPage cartPage =dashboardPage.clickOnCartMenu();
		cartPage.waitForCartPageLoad();
		
		System.out.println("STEP : Get count of product added into cart menu");
		int countOfProduct = cartPage.getTotalProductCountOnCartMenu();
		System.out.println("VERIFY : Count should be 2");
		Assert.assertEquals(countOfProduct, 2);
		
		System.out.println("VEFIRY : Count of product in MyCart should be 2");
		countOfProduct = cartPage.getTotalNumberOfProductsInMyCart();
		Assert.assertEquals(countOfProduct, 2);
		
		System.out.println("VERIFY : Added Products are visible in MyCart");
		boolean productVisibleFlag = cartPage.isProductAvailableInMyCart(productName1);
		Assert.assertTrue(productVisibleFlag);
		productVisibleFlag = cartPage.isProductAvailableInMyCart(productName2);
		Assert.assertTrue(productVisibleFlag);
	}
	
	@Test
	public void verifyCartListAfterLogout() {
		login();
		String productName = "IPHONE 13 PRO";
		System.out.println("STEP : Click on AddToCart to add product to cart");
		dashboardPage.addToCart(productName);
		dashboardPage.waitForSpinnerToBeDisappear();
		
		System.out.println("STEP : Navigate to cart menu");
		CartPage cartPage =dashboardPage.clickOnCartMenu();
		cartPage.waitForCartPageLoad();
		
		System.out.println("STEP : Get count of product added into cart menu");
		int countOfProduct = cartPage.getTotalProductCountOnCartMenu();
		System.out.println("VERIFY : Count should be 1");
		Assert.assertEquals(countOfProduct, 1);
		
		System.out.println("STEP : Logout from application");
		dashboardPage.clickOnLogout();
		
		System.out.println("STEP : Login to application");
		login();
		
		System.out.println("STEP : Click on MyCart menu");
		dashboardPage.clickOnCartMenu();
		
		System.out.println("VERIFY : No Products in Your Cart! is displayed");
		cartPage.waitForCartPageLoad();
		boolean noProductMessageFlag = cartPage.isNoProductInCartIsDisplayed();
		Assert.assertTrue(noProductMessageFlag);
	}
}
