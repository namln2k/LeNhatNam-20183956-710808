package entity.invoice;

import entity.order.Order;
import entity.shipping.RushInfo;

public class Invoice {

    private Order order;
    private int amount;
    
    public Invoice(){

    }

    public Invoice(Order order){
        this.order = order;
    }
    
    public Invoice(Order order, RushInfo rushInfo) {
    	this.order = order;
    }

    public Order getOrder() {
        return order;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void saveInvoice(){
        
    }
}
