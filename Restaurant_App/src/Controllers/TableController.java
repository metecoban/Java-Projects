package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class TableController implements Initializable {

	@FXML
	private AnchorPane anchorTables;

	@FXML
	private JFXButton buttonTable1;

	@FXML
	private JFXButton buttonTable2;

	@FXML
	private JFXButton buttonTable3;

	@FXML
	private JFXButton buttonTable4;

	@FXML
	private JFXButton buttonTable5;

	@FXML
	private JFXButton buttonTable6;

	@FXML
	private JFXButton buttonTable7;

	@FXML
	private JFXButton buttonTable8;

	@FXML
	private JFXButton buttonTable9;

	private ArrayList<JFXButton> buttons;

	private static TableController instance;

	private int TableNum;

	public TableController() {
		instance = this;
	}

	public static TableController getInstance() {
		return instance;
	}

	public int tableName() {
		return TableNum;
	}

	@FXML
	void clickTable(ActionEvent event) {
		TableNum = Integer.parseInt(event.getSource().toString().substring(24, 25));
		TableMainController.getInstance().createPage(anchorTables, "/FXML/OrderPage.fxml");
	}

	private void fillTableColors() {
		for (int i = 0; i < Main.tableArray.length; i++) {
			if (Main.tableArray[i].isActivity() == true) {
				buttons.get(i).setStyle("-fx-background-color: #B87D84");
				buttons.get(i).setText("Table " + (i + 1) + "     " + Main.tableArray[i].getTotalReceipt() + "$");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttons = new ArrayList<JFXButton>();
		buttons.add(buttonTable1);
		buttons.add(buttonTable2);
		buttons.add(buttonTable3);
		buttons.add(buttonTable4);
		buttons.add(buttonTable5);
		buttons.add(buttonTable6);
		buttons.add(buttonTable7);
		buttons.add(buttonTable8);
		buttons.add(buttonTable9);
		fillTableColors();
	}

}
