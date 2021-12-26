package controller;

import java.util.List;

import entity.order.Order;
import entity.order.OrderMedia;

public class ShippingFeesController implements ShippingFeesInterface {

	/**
	 * @see ShippingFeesInterface#calculateShippingFees(entity.order.Order)
	 */
	public int calculateShippingFees(Order order) {
		List lstOrderMedia = order.getlstOrderMedia();
		int shippingFees = 0;
		for (Object object : lstOrderMedia) {
			OrderMedia om = (OrderMedia) object;
			shippingFees += 0.1 * om.getPrice();
		}
		return shippingFees;
	}

}
