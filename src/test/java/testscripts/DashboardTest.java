package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends TestBase{

	@Test
	public void verifyItemListInSideFilterOptions() {
		List<String> expectedCategoriesList = new ArrayList<String>(Arrays.asList("fashion", "electronics", "household"));
		List<String> expectedSubCategoriesList = new ArrayList<String>(Arrays.asList("t-shirts", "shirts", "shoes","mobiles","laptops"));
		List<String> expectedSearchForList = new ArrayList<String>(Arrays.asList("men", "women"));
		
		login();
		System.out.println("STEP : Get Total number of Items in categories");
		int actualCategoriesItemSize = dashboardPage.getTotalNumberOfItemsInCategories();
		
		System.out.println("VERIFY : Number of Items visible in categories");
		Assert.assertEquals(actualCategoriesItemSize, 3);
	
		System.out.println("VERIFY : Items in categories");
		List<String> actualCategoriesList = dashboardPage.getCategoriesItemsTextList();
		Assert.assertEquals(actualCategoriesList, expectedCategoriesList);
		
		System.out.println("STEP : Get Total number of Items in sub categories");
		int actualSubCategoriesItemSize = dashboardPage.getTotalNumberOfItemsInSubCategories();
		
		System.out.println("VERIFY : Number of Items visible in sub categories");
		Assert.assertEquals(actualSubCategoriesItemSize, 5);
	
		System.out.println("VERIFY : Items in sub categories");
		List<String> actualSubCategoriesList = dashboardPage.getSubCategoriesItemsTextList();
		Assert.assertEquals(actualSubCategoriesList, expectedSubCategoriesList);
		
		System.out.println("STEP : Get Total number of Items in search for");
		int actualSearchForItemSize = dashboardPage.getTotalNumberOfItemsInSearchFor();
		
		System.out.println("VERIFY : Number of Items visible in search for");
		Assert.assertEquals(actualSearchForItemSize, 2);
	
		System.out.println("VERIFY : Items in search for");
		List<String> actualSearchForList = dashboardPage.getSearchForItemsTextList();
		Assert.assertEquals(actualSearchForList, expectedSearchForList);
		
	}
	
	@Test
	public void verifyFilterTest() {
		login();
		
		System.out.println("STEP : User selects the electronics checkbox under Categories");
		dashboardPage.selectOptionItem("electronics");
	
		System.out.println("VERIFY : Option item is selected");
		boolean optionItemSelectedFlag = dashboardPage.isOptionSelected("electronics");
		Assert.assertTrue(optionItemSelectedFlag);
	
		System.out.println("VERIFY : Options are visible as per applied filter");
		int totalCards = dashboardPage.getTotalNumberOfVisibleCards();
		Assert.assertEquals(totalCards, 1);
	}
}
