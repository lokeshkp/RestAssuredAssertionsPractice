package com.paypal.examples;

import org.junit.Test;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import com.paypal.base.BaseClass;
import com.paypal.payment.pojo.Amount;
import com.paypal.payment.pojo.Details;
import com.paypal.payment.pojo.Item_List;
import com.paypal.payment.pojo.Items;
import com.paypal.payment.pojo.Payer;
import com.paypal.payment.pojo.Payment_Options;
import com.paypal.payment.pojo.PostObj;
import com.paypal.payment.pojo.Redirect_urls;
import com.paypal.payment.pojo.Shipping_Address;
import com.paypal.payment.pojo.Transactions;

public class PayPalPostObject extends BaseClass {

	
	@Test
	public void createPayment(){
	
		//Setting Payer
		Payer payer = new Payer();
		payer.setPayment_method("paypal");
			
		Details details = new Details();
		details.setSubtotal("30.00");
		details.setTax("0.07");
		details.setShipping("0.03");
		details.setInsurance("0.01");
		details.setHandling_fee("1.00");
		details.setShipping_discount("-1.00");
		
		Amount amount = new Amount();
		amount.setTotal("31.11");
		amount.setCurrency("USD");
		amount.setDetails(details);
	
		
		Shipping_Address shippingAddress = new Shipping_Address();
		shippingAddress.setCity("Bangalore");
		shippingAddress.setCountry_code("IN");
		shippingAddress.setLine1("4th Floor");
		shippingAddress.setLine2("unit21");
		shippingAddress.setPhone("992392932939");
		shippingAddress.setPostal_code("43535");
		shippingAddress.setRecipient_name("RAM");
		shippingAddress.setState("KA");
		
		Items item1 = new Items();
		item1.setName("Cap");
		item1.setDescription("Yellow Color Cap");
		item1.setQuantity("5");
		item1.setPrice("5");
		item1.setTax("0.01");
		item1.setSku("1");
		item1.setCurrency("USD");

		
		Items item2 = new Items();
		item2.setName("Belt");
		item2.setDescription("Red Color Cap");
		item2.setQuantity("9");
		item2.setPrice("2");
		item2.setTax("0.02");
		item2.setSku("1");
		item2.setCurrency("USD");

		
		List<Items> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		
		Item_List itemList = new Item_List();
		itemList.setItems(items);
		itemList.setShipping_address(shippingAddress);
		
		
		Payment_Options paymentOptions = new Payment_Options();
		paymentOptions.setAllowed_payment_method("INSTANT_FUNDING_SOURCE");
		
		Transactions trans = new Transactions();
		trans.setAmount(amount);
		trans.setDescription("The payment transaction description.");
		trans.setCustom("EBAY_EMS_90048630024435");
		trans.setInvoice_number("48787589635");
		trans.setPayment_options(paymentOptions);
		trans.setSoft_descriptor("ECHI5786212");
		trans.setItem_list(itemList);
		
		
		List<Transactions> transactions = new ArrayList<>();
		transactions.add(trans);
	
		
		Redirect_urls red_url = new Redirect_urls();
		red_url.setReturn_url("http://amazon.com");
		red_url.setCancel_url("http://hawaii.com");
		
		
		
		PostObj postObj = new PostObj();
		postObj.setIntent("sale");
		postObj.setPayer(payer);
		postObj.setTransactions(transactions);
		postObj.setNote_to_payer("Contact us for any questions on your order.");
		postObj.setRedirect_urls(red_url);
		
		given().contentType(ContentType.JSON).auth().oauth2(access_token).
		when().body(postObj).post("/payments/payment").
		then().log().all();
		
	}
}
