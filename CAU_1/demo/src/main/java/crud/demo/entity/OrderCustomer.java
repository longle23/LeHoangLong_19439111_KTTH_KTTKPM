package crud.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor

@ToString
public class OrderCustomer {
	private int orderId;

	private String name;

	private double price;

	private Customer customerId;

	public OrderCustomer(int orderId, String name, double price, Customer customerId) {
		super();
		this.orderId = orderId;
		this.name = name;
		this.price = price;
		this.customerId = customerId;
	}

	public OrderCustomer() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}
	
	

}
