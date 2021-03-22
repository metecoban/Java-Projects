package application;

import java.util.LinkedHashMap;

public class Table {
	private String tableName;
	private boolean activity;
	private String totalReceipt;
	private LinkedHashMap<String, String> products;

	public Table(String tableName, boolean activity, String totalReceipt) {
		this.tableName = tableName;
		this.activity = activity;
		this.totalReceipt = totalReceipt;
		products = new LinkedHashMap<String, String>();
	}

	public boolean isActivity() {
		return activity;
	}

	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public String getTotalReceipt() {
		return totalReceipt;
	}

	public void setTotalReceipt(String totalReceipt) {
		this.totalReceipt = totalReceipt;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public String toString() {
		return "Table [tableName=" + tableName + ", activity=" + activity + ", totalReceipt=" + totalReceipt + "]";
	}

	public LinkedHashMap<String, String> getProducts() {
		return products;
	}

	public void setProducts(LinkedHashMap<String, String> products) {
		this.products = products;
	}

	public void setProducts(String key, String value) {
		this.products.put(key, value);
	}

	public void dellProductFromLHM(String key) {
		this.products.remove(key);
	}

	public void clearProductsFromLHM() {
		this.products.clear();
	}
}
