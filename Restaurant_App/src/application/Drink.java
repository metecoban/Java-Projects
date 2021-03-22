package application;

public class Drink extends Product {
	private String brand;

	public Drink(String productName, String price, String brand) {
		super(productName, price);
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

}
