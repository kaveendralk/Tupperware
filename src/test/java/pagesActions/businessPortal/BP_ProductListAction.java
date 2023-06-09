package pagesActions.businessPortal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import net.serenitybdd.core.pages.PageObject;

import utils.UiBase;

public class BP_ProductListAction extends PageObject{

	Logger logger= LogManager.getLogger(BP_ProductListAction.class);

	public UiBase uiBase = new UiBase();
	public static String productTitle;
	public static String ProductPrice;
	public static String productKidsTitle;
	public static String productServeWareTitle;
	public static String ActualFirstProductID;
	public static String ExpectedFirstProductID;

	public boolean verifyTheProductOverlayPage() throws InterruptedException {
		try {
			logger.info("User is on product Overlay Page to select listed products");
			uiBase.waitUntilElementDisplayed("SalesPage:btnKitchenTools", 50);
			uiBase.clickElement("SalesPage:btnKitchenTools");
			Actions action = new Actions(getDriver());
			action.moveToElement(uiBase.getElementFromJson("SalesPage:firstProduct")).click().build().perform();
			uiBase.getWaitForload();
			productTitle=uiBase.getText("SalesPage:firstProductTitle");
			ProductPrice=uiBase.getText("SalesPage:firstProductPrice");
			uiBase.clickElement("SalesPage:addToOrderButtonOnOverlay");
			logger.info("User added the product on cart");
			return true;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;

	}



	public boolean verifytheAddedProduct() throws InterruptedException {
		try {
			logger.info("User verifying the added product on cart");

			uiBase.waitUntilElementDisplayed("SalesPage:firstProductDescription", 20);
			String ActualproductTitle=uiBase.getText("SalesPage:firstProductDescription");
			System.out.println("ActualproductId : "+ActualproductTitle);
			String ExpectedProductTitle=productTitle.toUpperCase();
			System.out.println("ExpectedProductId : "+ExpectedProductTitle);
			assertEquals(ActualproductTitle, ExpectedProductTitle);

			logger.info("User Verified the added product on cart");
			return true;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addProductByIdOnCart() throws InterruptedException, AWTException {
		try {
			logger.info("User is on product Overlay Page to select product by Product ID");
			uiBase.getWaitForload();
			logger.info("User add product by SearchID on cart");
			uiBase.waitUntilElementDisplayed("SalesPage:inpAddNewItem", 15);
			uiBase.enterText("SalesPage:inpAddNewItem", uiBase.getTestDataFromJson("AddedProductList:ProductByID"));
			uiBase.getWaitForload();
			WebElement element=uiBase.getElementFromJson("SalesPage:inpAddNewItem");
			element.sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
			logger.info("Search By ID product added");
			return true;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean addProductByNameOnCart() throws InterruptedException {
		try {
			logger.info("User is on product Overlay Page to select product by Product Name");;
			uiBase.getWaitForload();
			uiBase.waitUntilElementDisplayed("SalesPage:inpAddNewItem", 15);
			uiBase.enterText("SalesPage:inpAddNewItem", uiBase.getTestDataFromJson("AddedProductList:ProductName"));
			uiBase.getWaitForload();
			WebElement element=uiBase.getElementFromJson("SalesPage:inpAddNewItem");
			element.sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
			logger.info("User added product by product name on cart");
			return true;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;

	}


	public boolean verifyTheTotalOrderOfProductPrice() {
		try {
			logger.info("User is on product cart Page to verify the total price of listed products");
			String SubtotalPrice= uiBase.getText("SalesPage:lblSubtotal");
			double totalprice=Double.parseDouble(SubtotalPrice.replace("$", ""));

			System.out.println("SubTotal Price of Product :" +SubtotalPrice);

			List<WebElement> productList=getDriver().findElements(By.xpath("//div[@data-bind='text: subtotal']"));
			double sum=0;
			for(int i=0;i<productList.size();i++)
			{
				double price=Double.parseDouble(productList.get(i).getText().replace("$", ""));
				sum=sum+price;
				System.out.println("Total Unit Price :" +sum);
				System.out.println("Product Count "+productList.size());
				System.out.println("Unit Price of products unit price:" +price);
			}
			if(sum==totalprice)
			{
				System.out.println("Product price equals to Sum of added products");
			}
			logger.info("User has verified the total price");
			return true;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;
	}



	public boolean addProductWithMultipleCatagoryOnCart() throws InterruptedException {
		Actions action = new Actions(getDriver());
		try {
			logger.info("User is on product select page to add product by Browse");
			uiBase.getWaitForload();
			uiBase.isElementDisplayed("SalesPage:btnBrowse");
			uiBase.clickElement("SalesPage:btnBrowse");
			if(uiBase.isElementDisplayed("SalesPage:btnKidsToys")) {
				uiBase.clickElement("SalesPage:btnKidsToys");
				uiBase.getWaitForload();
				action.moveToElement(uiBase.getElementFromJson("SalesPage:lnkSecondProductToys")).click().build().perform();
				uiBase.isElementDisplayed("SalesPage:btnAddToOrderPop");
				productKidsTitle=uiBase.getText("SalesPage:firstProductTitle");
				uiBase.clickElement("SalesPage:btnAddToOrderPop");
			}
			logger.info("User added product by Browse");

			return true;

		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;

	}


	public boolean verifyProductWithMultipleCatagoryOnCart() throws InterruptedException {
		try {
			logger.info("User is verifying added product in cart");
			uiBase.getWaitForload();
			String ActualFirstProductID=uiBase.getText("SalesPage:txtpoductID");
			String ExpectedFirstProductID=uiBase.getTestDataFromJson("AddedProductList:ProductByID");
			assertEquals(ActualFirstProductID, ExpectedFirstProductID);

			String ActualSecondProductTitle=uiBase.getText("SalesPage:SecondProductDesc");
			String ExpectedSecondProductTitle=uiBase.getTestDataFromJson("AddedProductList:ProductName");
			assertEquals(ActualSecondProductTitle, ExpectedSecondProductTitle);

			uiBase.isElementDisplayed("SalesPage:ThirdProductDescription");
			String ActualThirdProductTitle=uiBase.getText("SalesPage:ThirdProductDescription");

			System.out.println("ActualThirdProductTitle : "+ActualThirdProductTitle);
			String ExpectedThirdProductTitle=productKidsTitle.toUpperCase();
			System.out.println("ExpectedThirdProductTitle : "+ExpectedThirdProductTitle);
			if(ActualThirdProductTitle.contains(ExpectedThirdProductTitle))
			{
				System.out.println("Added Product present in cart");
			}
			
			logger.info("User verified added product in cart");
			return true;		
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;
	}



	public boolean verifyDeleteIcon() {
		try {
			logger.info("User is verifying delete Icon on Create Order Page");
			if(uiBase.isElementDisplayed("SalesPage:deleteIcon")) {
				logger.info("Delete Icon exist On Page");
				return true;
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;
	}



	public boolean verifyProductNotDeleted() {
		try {
			logger.info("User is verifying product not deleted");
			ActualFirstProductID=uiBase.getText("SalesPage:txtpoductID");

			if(ActualFirstProductID != null && !ActualFirstProductID.isEmpty()) {
				System.out.println("Product is not deleted on the cart");
				return true;
			}else {
				System.out.println("Product is  deleted on the cart");
				return false;
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;

	}



	public boolean verifyProductIsDeleted() {
		try {
			logger.info("User is verifying product get deleted");
			ActualFirstProductID=uiBase.getText("SalesPage:txtpoductID");
			ExpectedFirstProductID=uiBase.getTestDataFromJson("AddedProductList:ProductByID");
			if(ActualFirstProductID!=ExpectedFirstProductID) {
				System.out.println("Product is deleted on the cart");
				return true;
			}else {
				System.out.println("Product is not deleted on the cart");
				return false;
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return false;
	}

}
