package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import DBConnection.DBHandler;
import application.Drink;
import application.Food;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ProductSettingsController implements Initializable {
	@FXML
	private AnchorPane anchorPaneProducts;

	@FXML
	private JFXListView<String> listVievFood;

	@FXML
	private JFXListView<String> listVievDrink;

	@FXML
	private JFXTextField tFFoodName;

	@FXML
	private JFXTextField tFFoodPrice;

	@FXML
	private JFXButton buttonFoodAdd;

	@FXML
	private JFXTextField tFDrinkName;

	@FXML
	private JFXTextField tFDrinkBrand;

	@FXML
	private JFXTextField tFDrinkPrice;

	@FXML
	private JFXButton buttonDrinkAdd;

	@FXML
	private JFXButton buttonDeleteSelectedFood;

	@FXML
	private JFXButton buttonDeleteSelectedDrink;

	private ArrayList<String> foodAList, drinkAList;

	@FXML
	private JFXButton buttonGoBack;

	@FXML
	void clickGoBack(ActionEvent event) {
		TableMainController.getInstance().createPage(anchorPaneProducts, "/FXML/Table.fxml");
	}

	private void fillTheListView() {
		listVievFood.getItems().clear();
		listVievDrink.getItems().clear();
		foodAList.clear();
		drinkAList.clear();
		getFoodValues();
		getDrinkValues();
		listVievFood.setItems(FXCollections.observableArrayList(foodAList));
		listVievDrink.setItems(FXCollections.observableArrayList(drinkAList));
	}

	private void getFoodValues() {
		for (int i = 0; i < Main.foodList.size(); i++) {
			foodAList.add(Main.foodList.get(i).getProductName() + "  -  " + Main.foodList.get(i).getPrice() + "$");
		}

	}

	private void getDrinkValues() {
		for (int i = 0; i < Main.drinkList.size(); i++) {
			drinkAList.add(Main.drinkList.get(i).getProductName() + "  -  " + Main.drinkList.get(i).getPrice() + "$");
		}
	}

	@FXML
	void clickButtonDrinkAdd(ActionEvent event) {
		if (!(tFDrinkName.getText().equals("") && tFDrinkPrice.getText().equals("")
				&& tFDrinkBrand.getText().equals(""))) {
			Main.drinkList.add(new Drink(tFDrinkName.getText(), tFDrinkPrice.getText(), tFDrinkBrand.getText()));
			DBHandler.setDBProduct("Drink", tFDrinkName.getText(), tFDrinkPrice.getText(), tFDrinkBrand.getText());
		} else {
			Main.giveInformationAlert("You did not answer the fields!", "Try to enter all fields.");
		}
		tFDrinkName.setText("");
		tFDrinkPrice.setText("");
		tFDrinkBrand.setText("");
		fillTheListView();
	}

	@FXML
	void clickButtonFoodAdd(ActionEvent event) {
		if (!(tFFoodName.getText().equals("") && tFFoodPrice.getText().equals(""))) {
			Main.foodList.add(new Food(tFFoodName.getText(), tFFoodPrice.getText()));
			DBHandler.setDBProduct("Food", tFFoodName.getText(), tFFoodPrice.getText(), "");
		} else {
			Main.giveInformationAlert("You did not answer the fields!", "Try to enter all fields.");
		}
		tFFoodName.setText("");
		tFFoodPrice.setText("");
		fillTheListView();
	}

	@FXML
	void clickbuttonDeleteSelectedDrink(ActionEvent event) {
		if (listVievDrink.getSelectionModel().getSelectedItem() != null) {
			int i = 0;
			for (; i < Main.drinkList.size(); i++) {
				if (Main.drinkList.get(i).getProductName()
						.equals(listVievDrink.getSelectionModel().getSelectedItem().split("-")[0].trim())) {
					break;
				}
			}
			Main.drinkList.remove(i);
			DBHandler.deleteRowDBProduct(listVievDrink.getSelectionModel().getSelectedItem().split("-")[0].trim());
			fillTheListView();
		} else {
			Main.giveInformationAlert("You did not choose the field!", "Try to enter any row.");
		}
	}

	@FXML
	void clickbuttonDeleteSelectedFood(ActionEvent event) {
		if (listVievFood.getSelectionModel().getSelectedItem() != null) {
			int i = 0;
			for (; i < Main.foodList.size(); i++) {
				if (Main.foodList.get(i).getProductName()
						.equals(listVievFood.getSelectionModel().getSelectedItem().split("-")[0].trim())) {
					break;
				}
			}
			Main.foodList.remove(i);
			DBHandler.deleteRowDBProduct(listVievFood.getSelectionModel().getSelectedItem().split("-")[0].trim());
			fillTheListView();
		} else {
			Main.giveInformationAlert("You did not choose the field!", "Try to enter any row.");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		foodAList = new ArrayList<String>();
		drinkAList = new ArrayList<String>();
		fillTheListView();
	}
}
