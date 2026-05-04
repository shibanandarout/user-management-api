package demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class OrderDto {
	@NotBlank(message = "Product should not be empty")
	private String product;
	
	@Min(value = 100,message = "Price must be greater than 100")
	private double price;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
