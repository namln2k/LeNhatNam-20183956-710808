package controller;

import entity.order.Order;

/**
 * Interface các nghiệp vụ về tính phí ship (Shipping fees)
 * 
 * @author NAM.LN183956
 *
 */
public interface ShippingFeesInterface {

	/**
	 * Hàm tính toán phí ship
	 * 
	 * @param order: đơn hàng cần tính phí ship
	 * @return phí ship cho đơn hàng
	 */
	public abstract int calculateShippingFees(Order order);
}
