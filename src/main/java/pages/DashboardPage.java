package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class DashboardPage extends ControlActions{

	@FindBy(xpath = "//section[@id='sidebar']//h6[text()='Categories']/following-sibling::div[not(@id='orange')]/label")
	List<WebElement> categoriesElementList;
	
	@FindBy(xpath = "//section[@id='sidebar']//h6[text()='Sub Categories']/following-sibling::div[not(@id='orange')]/label")
	List<WebElement> subCategoriesElementList;
	
	@FindBy(xpath = "//section[@id='sidebar']//h6[text()='Search For']/following-sibling::div[not(@id='orange')]/label")
	List<WebElement> searchForElementList;
	
	@FindBy(xpath = "//div[@class='card']")
	List<WebElement> listOfCards;
	
	@FindBy(xpath = "//button[@class='btn btn-custom' and contains(text(), 'Cart')]")
	WebElement cartMenu;
	
	@FindBy(xpath = "//div[contains(@class, 'la-ball-scale-multiple ng-star-inserted')]")
	WebElement spinnerElement;
	
	@FindBy(xpath = "//button[contains(text(), 'Sign Out')]")
	WebElement logoutElement;
	
	private CartPage cartPage;
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
		cartPage = new CartPage();
	}
	
	public int getTotalNumberOfItemsInCategories() {
		return categoriesElementList.size();
	}
	
	public List<String> getCategoriesItemsTextList() {
		/*
		 * List<String> categoriesItemsTextList = new ArrayList<String>();
		 * for(WebElement item : categoriesElementList) {
		 * categoriesItemsTextList.add(item.getText()); } return
		 * categoriesItemsTextList;
		 */
		return getElementTextList(categoriesElementList);
	}
	
	public int getTotalNumberOfItemsInSubCategories() {
		return subCategoriesElementList.size();
	}
	
	public List<String> getSubCategoriesItemsTextList() {
		/*
		 * List<String> subCategoriesItemsTextList = new ArrayList<String>();
		 * for(WebElement item : subCategoriesElementList) {
		 * subCategoriesItemsTextList.add(item.getText()); } return
		 * subCategoriesItemsTextList;
		 */
		return getElementTextList(subCategoriesElementList);
	}
	
	public int getTotalNumberOfItemsInSearchFor() {
		return searchForElementList.size();
	}
	
	public List<String> getSearchForItemsTextList() {
		/*
		 * List<String> searchForItemsTextList = new ArrayList<String>(); for(WebElement
		 * item : searchForElementList) { searchForItemsTextList.add(item.getText()); }
		 * return searchForItemsTextList;
		 */
		return getElementTextList(searchForElementList);
	}
	
	public void selectOptionItem(String itemName) {
		String locatorValue = "(//label[text()='"+itemName+"'])[2]/preceding-sibling::input";
		/*
		 * WebElement e = getElement("XPATH", locatorValue, true); e.click();
		 */
		clickOnElement("XPATH", locatorValue, true);
	}
	
	public boolean isOptionSelected(String itemName) {
		String locatorValue = String.format("(//label[text()='%s'])[2]/preceding-sibling::input", itemName);
		return getElement("XPATH", locatorValue, false).isSelected();
	}
	
	public int getTotalNumberOfVisibleCards() {
		return listOfCards.size();
	}
	
	public void addToCart(String productName) {
		//productName = productName.toLowerCase();
		String locator = String.format("//b[text()='%s']/parent::h5/following-sibling::button[contains(text(), 'Add To Cart')]", productName);
		clickOnElement("XPATH", locator, true);
	}
	
	public void waitForSpinnerToBeDisappear() {
		waitForElementToBeInVisible(spinnerElement);
	}
	
	public CartPage clickOnCartMenu() {
		clickOnElement(cartMenu, true);
		return cartPage;
	}
	
	public void clickOnLogout() {
		clickOnElement(logoutElement, false);
	}
}
