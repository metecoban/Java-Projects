package application;

public abstract class Product {
	private String productName;
	private String price;

	public Product(String productName, String price) {
		this.productName = productName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", price=" + price + "]";
	}
}
