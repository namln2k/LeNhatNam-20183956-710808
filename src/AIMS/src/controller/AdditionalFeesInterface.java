package controller;

import entity.order.Order;

/**
 * 
 * Interface các nghiệp vụ về tính tiền phí tăng thêm do Rush Order (Additional fees)
 * 
 * @author NAM.LN183956
 *
 */
public interface AdditionalFeesInterface {

	/**
	 * Hàm tính tiền phí tăng thêm
	 * 
	 * @param order: đơn hàng cần tính phí
	 * @return phí tăng thêm cho đơn hàng
	 */
	public abstract int calculateAdditionalFees(Order order);
}
