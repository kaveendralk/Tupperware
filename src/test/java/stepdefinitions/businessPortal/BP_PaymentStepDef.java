package stepdefinitions.businessPortal;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

import pages.businessPortal.BP_PaymentPage;

public class BP_PaymentStepDef {

Logger logger= LogManager.getLogger(BP_NonPartyStepDef.class);

	@Steps
	BP_PaymentPage bp_PaymentPage;
	
	@And("User click on Add New Card button on Payment Options page")
	public void user_clicks_on_Add_New_Card_on_Payment_Options_overlay() throws InterruptedException {
		assertTrue(bp_PaymentPage.clickOnAddPaymentButtonAndVerify());
	}

	@And("User verify shipping and payment details on Payment Details page")
	public void user_verify_shipping_and_payment_details_on_Payment_Details_page() throws InterruptedException {
		assertTrue(bp_PaymentPage.verifyShippingAndPaymentDetails());
	}

	@And("User entered Visa card details and press Next")
	public void user_choose_to_enter_Visa_card_details_and_press_Next() {
		assertTrue(bp_PaymentPage.enterVisaCardDetails());
	}

	@And("User click on Submit button on Create Order page")
	public void user_clicks_on_Submit_button_on_Create_Order_page() throws InterruptedException {
		assertTrue(bp_PaymentPage.clickOnSubmitAndVerify());
	}

	@And("User verify that correct order details is coming on Order Information page")
	public void user_verify_that_correct_order_details_is_coming_on_Order_Information_page() throws InterruptedException {
		assertTrue(bp_PaymentPage.verifyOrderInformationOfProducts());
	}
	
	@And("User click on Return to Order List on Order Information page")
	public void user_clicks_on_Return_to_Order_List_on_Order_Information_page() {
		assertTrue(bp_PaymentPage.clickReturnToOrderListAndVerify());
	}
	
	@Then("User click on existing payment card option")
	public void user_click_on_existing_payment_card_option() {
		assertTrue(bp_PaymentPage.clickOnExistingPaymentCard());	
		}
	
	@And("User verify that payment card details")
	public void user_verify_that_payment_card_details() {
		assertTrue(bp_PaymentPage.verifyPaymentCardDetails());
	}

}
