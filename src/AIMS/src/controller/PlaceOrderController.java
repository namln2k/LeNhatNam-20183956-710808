package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * 
 * @author nguyenlm
 *
 */
public class PlaceOrderController extends BaseController {

	/**
	 * Just for logging purpose
	 */
	private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

	/**
	 * This method checks the avalibility of product when user click PlaceOrder
	 * button
	 * 
	 * @throws SQLException
	 */
	public void placeOrder() throws SQLException {
		Cart.getCart().checkAvailabilityOfProduct();
	}

	/**
	 * This method creates the new Order based on the Cart
	 * 
	 * @return Order
	 * @throws SQLException
	 */
	public Order createOrder() throws SQLException {
		Order order = new Order();
		for (Object object : Cart.getCart().getListMedia()) {
			CartMedia cartMedia = (CartMedia) object;
			OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), cartMedia.getQuantity(), cartMedia.getPrice());
			order.getlstOrderMedia().add(orderMedia);
		}
		return order;
	}

	/**
	 * This method creates the new Invoice based on order
	 * 
	 * @param order
	 * @return Invoice
	 */
	public Invoice createInvoice(Order order) {
		return new Invoice(order);
	}

	/**
	 * This method takes responsibility for processing the shipping info from user
	 * 
	 * @param info
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException {
		LOGGER.info("Process Delivery Info");
		LOGGER.info(info.toString());
		validateDeliveryInfo(info);
	}

	/**
	 * The method validates the info
	 * 
	 * @param info
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException {
		if (!validatePhoneNumber(info.get("phoneNumber")))
			throw new InterruptedException("Số điện thoại không hợp lệ!");
		if (!validateName(info.get("name")))
			throw new InterruptedException("Tên không hợp lệ!");
		if (!validateAddress(info.get("addess")))
			throw new InterruptedException("Địa chỉ giao hàng không hợp lệ!");
	}

	/**
	 * Check if phone number is valid
	 * 
	 * @author NAM.LN183956
	 * @param phoneNumber
	 * @return true if phone number contains exactly 10 numbers and starts with 0.
	 *         Otherwise return false
	 */
	public boolean validatePhoneNumber(String phoneNumber) {
		if (!phoneNumber.startsWith("0"))
			return false;
		if (phoneNumber.length() != 10)
			return false;
		if (!phoneNumber.matches("[0-9]+"))
			return false;
		return true;
	}

	/**
	 * Check if name is valid
	 * 
	 * @author NAM.LN183956
	 * @param name
	 * @return true if name is not null and contains only alphabetical characters
	 *         and no special character. Otherwise return false
	 */
	public boolean validateName(String name) {
		if (name == null)
			return false;
		if (name.matches("^[ A-Za-z]+$"))
			return true;
		return false;
	}

	/**
	 * Check if address is valid
	 * 
	 * @author NAM.LN183956
	 * @param address
	 * @return true if address is not null and contains no special character
	 */
	public boolean validateAddress(String address) {
		if (address == null)
			return false;
		if (address.matches("[A-Za-z0-9 ]+"))
			return true;
		return false;
	}

	/**
	 * This method calculates the shipping fees of order
	 * 
	 * @param order
	 * @return shippingFee
	 */
	public int calculateShippingFee(Order order) {
		Random rand = new Random();
		int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
		LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
		return fees;
	}
}
