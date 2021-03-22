package Controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import DBConnection.DBHandler;
import application.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

public class OrderPageController implements Initializable {

	@FXML
	private AnchorPane anchorOrderPage;

	@FXML
	private TextArea textAreaReceipt;

	@FXML
	private JFXButton buttonCloseTable;

	@FXML
	private JFXComboBox<String> comboFood;

	@FXML
	private Label labelTableNum;

	@FXML
	private JFXComboBox<String> comboDrink;

	@FXML
	private JFXListView<String> listViewProduct;

	@FXML
	private JFXButton buttonFoodAdd;

	@FXML
	private JFXButton buttonDrinkAdd;

	@FXML
	private JFXTextField tFFoodAmount;

	@FXML
	private JFXTextField tFDrinkAmount;

	@FXML
	private JFXButton buttonRmvSlctd;

	@FXML
	private JFXButton buttonGoBack;

	private ArrayList<String> list;

	public void setTableLabel() {
		labelTableNum.setText("Table " + TableController.getInstance().tableName());
	}

	@FXML
	void addDrink(ActionEvent event) {
		if (!tFDrinkAmount.getText().equals("") && Integer.parseInt(tFDrinkAmount.getText()) > 0) {
			Main.tableArray[TableController.getInstance().tableName() - 1].setProducts(comboDrink.getValue(),
					tFDrinkAmount.getText());
			tFDrinkAmount.setText("");
			fillTheListView();
			fillTheReceipt();
		} else {
			Main.giveInformationAlert("You did not answer the fields!", "Try to enter all fields.");
		}
	}

	@FXML
	void addFood(ActionEvent event) {
		if (!tFFoodAmount.getText().equals("") && Integer.parseInt(tFFoodAmount.getText()) > 0) {
			Main.tableArray[TableController.getInstance().tableName() - 1].setProducts(comboFood.getValue(),
					tFFoodAmount.getText());
			tFFoodAmount.setText("");
			fillTheListView();
			fillTheReceipt();
		} else {
			Main.giveInformationAlert("You did not answer the fields!", "Try to enter all fields.");
		}
	}

	private void fillTheReceipt() {
		textAreaReceipt.setText("");
		SimpleDateFormat dayFormatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String str = "";
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			int s1 = Integer.parseInt(list.get(i).split("-")[1].trim().split("x")[0].trim().replace("$", ""));
			int s2 = Integer.parseInt(list.get(i).split("-")[1].trim().split("x")[1].trim());

			total += (s1 * s2);
			str += "  " + list.get(i) + "\t= " + s1 * s2 + "$\n";
		}
		textAreaReceipt.appendText("\n                      Coban Restaurant\n\n   Date: " + dayFormatter.format(date)
				+ "\n   Time: " + timeFormatter.format(date) + "\n\nProducts:\n" + str
				+ "\n  ---------------------------------------------  \n  Total: " + total + "$");
		Main.tableArray[TableController.getInstance().tableName() - 1].setTotalReceipt(String.valueOf(total));

	}

	private void fillTheListView() {
		if (Main.tableArray[TableController.getInstance().tableName() - 1].getProducts().size() != 0) {
			getTableProductValues();
		}
		listViewProduct.setItems(FXCollections.observableArrayList(list));
	}

	private void getTableProductValues() {
		list.clear();
		Set<String> setKeys = Main.tableArray[TableController.getInstance().tableName() - 1].getProducts().keySet();
		for (int i = 0; i < Main.tableArray[TableController.getInstance().tableName() - 1].getProducts().size(); i++) {
			list.add(setKeys.toArray()[i] + " x " + Main.tableArray[TableController.getInstance().tableName() - 1]
					.getProducts().values().toArray()[i]);
		}
	}

	@FXML
	void clickGoBack(ActionEvent event) {
		if (!Main.tableArray[TableController.getInstance().tableName() - 1].getTotalReceipt().equals("0")) {
			Main.tableArray[TableController.getInstance().tableName() - 1].setActivity(true);
		} else {
			Main.tableArray[TableController.getInstance().tableName() - 1].setActivity(false);
		}
		TableMainController.getInstance().createPage(anchorOrderPage, "/FXML/Table.fxml");
	}

	@FXML
	void closeTheTable(ActionEvent event) {
		Main.tableArray[TableController.getInstance().tableName() - 1].setActivity(false);
		if (!Main.tableArray[TableController.getInstance().tableName() - 1].getTotalReceipt().equals("0")) {
			SimpleDateFormat dayFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			Main.historyList
					.add(new History(dayFormatter.format(date), "Table " + TableController.getInstance().tableName(),
							Main.tableArray[TableController.getInstance().tableName() - 1].getTotalReceipt()));
			DBHandler.setDBHistory(dayFormatter.format(date), "Table " + TableController.getInstance().tableName(),
					Main.tableArray[TableController.getInstance().tableName() - 1].getTotalReceipt());
		} else {
			Main.giveInformationAlert("Table is empty!", "Add some products.");
		}
		Main.tableArray[TableController.getInstance().tableName() - 1].clearProductsFromLHM();
		Main.tableArray[TableController.getInstance().tableName() - 1].setTotalReceipt("0");
		TableMainController.getInstance().createPage(anchorOrderPage, "/FXML/Table.fxml");
	}

	@FXML
	void removeSelected(ActionEvent event) {
		if (listViewProduct.getSelectionModel().getSelectedItem() != null) {
			Main.tableArray[TableController.getInstance().tableName() - 1]
					.dellProductFromLHM(listViewProduct.getSelectionModel().getSelectedItem().split("x")[0].trim());
			list.clear();
			fillTheListView();
			fillTheReceipt();
		} else {
			Main.giveInformationAlert("You did not choose the field!", "Try to enter any row.");
		}
	}

	private void fillComboFood() {
		ArrayList<String> strList = new ArrayList<String>();
		for (Food i : Main.foodList) {
			strList.add(i.getProductName() + "  -  " + i.getPrice() + "$");
		}
		comboFood.setItems(FXCollections.observableArrayList(strList));
		comboFood.getSelectionModel().select(0);
	}

	private void fillComboDrink() {
		textAreaReceipt.setEditable(false);
		ArrayList<String> strList = new ArrayList<String>();
		for (Drink i : Main.drinkList) {
			strList.add(i.getProductName() + "(" + i.getBrand() + ")" + "  -  " + i.getPrice() + "$");
		}
		comboDrink.setItems(FXCollections.observableArrayList(strList));
		comboDrink.getSelectionModel().select(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = new ArrayList<String>();
		setTableLabel();
		fillComboFood();
		fillComboDrink();
		fillTheListView();
		fillTheReceipt();
		tFFoodAmount.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 3)
				tFFoodAmount.setText(oldValue);
		});

		tFDrinkAmount.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 3)
				tFDrinkAmount.setText(oldValue);
		});
	}

}
