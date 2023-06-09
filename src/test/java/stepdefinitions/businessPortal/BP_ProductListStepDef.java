package stepdefinitions.businessPortal;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import pages.businessPortal.BP_ProductListPage;

public class BP_ProductListStepDef {

	Logger logger= LogManager.getLogger(BP_ProductListStepDef.class);

	@Steps
	BP_ProductListPage bp_ProductListPage;

	@Then("User add product from {string} overlay")
	public void user_add_product_from_overlay(String string) throws InterruptedException {
		assertTrue(bp_ProductListPage.verifyTheProductOverlayPage());
		logger.info("User clicked on Browse button ");
	}


	@And("User verify that correct product is added")
	public void user_verify_that_correct_product_is_added() throws InterruptedException {
		assertTrue(bp_ProductListPage.verifytheAddedProduct());
		logger.info("User is on Product List Page  ");
	}

	@And("User adds product by searching product by Product_ID")
	public void user_adds_product_by_searching_product_by_Product_ID() throws InterruptedException, AWTException {
		assertTrue(bp_ProductListPage.addProductByIdOnCart());
		logger.info("User is on cart Page");
	}



	@Then("User adds multiple product of different product category to the cart")
	public void user_adds_multiple_product_of_different_product_catagory_to_the_cart() throws InterruptedException {
		assertTrue(bp_ProductListPage.addProductWithMultipleCatagoryOnCart());
		logger.info("User is adding product");
	}

	@Then("User verify the added product of multiple category")
	public void user_verify_the_added_product_of_mutiple_category() throws InterruptedException {
		assertTrue(bp_ProductListPage.verifyProductWithMultipleCatagoryOnCart());
		logger.info("User is verifying selected product");
	}

	@And("User verify that correct Order Total is displayed on Create Orders page")
	public void user_verify_that_correct_Order_Total_is_displayed_on_Create_Orders_page() {
		assertTrue(bp_ProductListPage.verifyTheTotalOrderOfProductPrice());;
	}

	@Then("User adds product by searching product by Product_Name")
	public void user_adds_product_by_searching_product_by_Product_Name() throws InterruptedException {
		assertTrue(bp_ProductListPage.addProductByNameOnCart());
	}

	/*@And("User verify that correct product is added by Search")
	public void user_verify_that_correct_product_is_added_by_Search() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}*/
	@Then("User verify that delete icon is coming against products")
	public void user_verify_that_delete_icon_is_coming_against_products() {
		assertTrue(bp_ProductListPage.verifyDeleteIcon());
	}

	
    @And("User verify that product is not deleted")
	public void user_verify_that_product_is_not_deleted() {
		assertTrue(bp_ProductListPage.verifyProductNotDeleted());;
	}

    @And("User verify that product is deleted")
    public void user_verify_that_product_is_deleted() {
    	assertTrue(bp_ProductListPage.verifyProductIsDeleted());;
    }

   





}
