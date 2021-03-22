package application;

import java.util.ArrayList;
import DBConnection.DBHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

public class Main extends Application {

	private static Alert alert = new Alert(Alert.AlertType.ERROR);
	private static final int TABLECOUNT = 9;

	public static ArrayList<Waiter> waiterList = new ArrayList<Waiter>();
	public static ArrayList<Boss> bossList = new ArrayList<Boss>();
	public static ArrayList<Drink> drinkList = new ArrayList<Drink>();
	public static ArrayList<Food> foodList = new ArrayList<Food>();
	public static ArrayList<History> historyList = new ArrayList<History>();
	public static Table[] tableArray = new Table[TABLECOUNT];

	private void setTableArray() {
		for (int i = 0; i < TABLECOUNT; i++) {
			tableArray[i] = new Table("Table " + (i + 1), false, "0");
		}
	}

	public static void giveInformationAlert(String infoHeaderText, String infoContentText) {
		alert.setTitle("Alert");
		alert.setHeaderText(infoHeaderText);
		alert.setContentText(infoContentText);
		alert.showAndWait();
	}

	private void fillTheList() {
		DBHandler.fillUserList();
		DBHandler.fillProductLists();
		DBHandler.fillHistoryLists();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			setTableArray();
			fillTheList();
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
			Scene scene = new Scene(root, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/Icons/restaurant.png"));
			primaryStage.setTitle("Coban Restaurant");
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
