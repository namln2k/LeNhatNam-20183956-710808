package controller;

import entity.order.Order;

public class AdditionalFeesController implements AdditionalFeesInterface {

	/**
	 * @see AdditionalFeesInterface#calculateAdditionalFees(entity.order.Order)
	 */
	public int calculateAdditionalFees(Order order) {
		// Not implemented, default return 50% of shippingFees
		return order.getShippingFees() * 50 / 100;
	}

}

