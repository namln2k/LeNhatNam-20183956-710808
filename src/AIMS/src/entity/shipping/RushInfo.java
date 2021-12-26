package entity.shipping;

import entity.order.Order;

public class RushInfo {
	private Order order;
	
	public RushInfo(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}
}
