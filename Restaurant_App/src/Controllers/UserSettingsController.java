package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import DBConnection.DBHandler;
import application.Boss;
import application.Main;
import application.Waiter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class UserSettingsController implements Initializable {
	@FXML
	private AnchorPane anchorPaneUserSettings;

	@FXML
	private JFXListView<String> listViewUsers;

	@FXML
	private JFXTextField tFUsername;

	@FXML
	private JFXTextField tFPassword;

	@FXML
	private JFXRadioButton rBBoss;

	@FXML
	private ToggleGroup quality;

	@FXML
	private JFXRadioButton rBWaiter;

	@FXML
	private JFXButton buttonAddUser;

	@FXML
	private JFXButton buttonGoBack;

	@FXML
	private JFXButton buttonDeleteSelectedUser;

	private ArrayList<String> users;

	@FXML
	void clickAddUser(ActionEvent event) {
		if (!(tFUsername.getText().equals("") && tFPassword.getText().equals(""))) {
			if (rBBoss.isSelected()) {
				Main.bossList.add(new Boss(tFUsername.getText(), tFPassword.getText()));
				DBHandler.setDBPUser("Boss", tFUsername.getText(), tFPassword.getText());
			} else if (rBWaiter.isSelected()) {
				Main.waiterList.add(new Waiter(tFUsername.getText(), tFPassword.getText()));
				DBHandler.setDBPUser("User", tFUsername.getText(), tFPassword.getText());
			}
			tFUsername.setText("");
			tFPassword.setText("");
			fillTheListView();
		} else {
			Main.giveInformationAlert("You did not answer the fields!", "Try to enter all fields.");
		}
	}

	@FXML
	void clickDeleteSelectedUser(ActionEvent event) {
		if (listViewUsers.getSelectionModel().getSelectedItem() != null) {
			int i = 0;
			if (listViewUsers.getSelectionModel().getSelectedItem().split("-")[0].trim().equals("Boss")) {
				for (; i < Main.bossList.size(); i++) {
					if (Main.bossList.get(i).getUsername().equals(
							listViewUsers.getSelectionModel().getSelectedItem().split("-")[1].split("-")[0].trim())) {
						break;
					}
				}
				Main.bossList.remove(i);
			} else {
				for (; i < Main.waiterList.size(); i++) {
					if (Main.waiterList.get(i).getUsername().equals(
							listViewUsers.getSelectionModel().getSelectedItem().split("-")[1].split("-")[0].trim())) {
						break;
					}
				}
				Main.waiterList.remove(i);
			}
			DBHandler.deleteRowDBUser(
					listViewUsers.getSelectionModel().getSelectedItem().split("-")[1].split("-")[0].trim());
			fillTheListView();
		} else {
			Main.giveInformationAlert("You did not choose the field!", "Try to enter any row.");
		}
	}

	private void fillTheListView() {
		listViewUsers.getItems().clear();
		users.clear();
		getBossValues();
		getWaiterValues();
		listViewUsers.setItems(FXCollections.observableArrayList(users));
	}

	private void getBossValues() {
		for (int i = 0; i < Main.bossList.size(); i++) {
			users.add("Boss  -  " + Main.bossList.get(i).getUsername() + "  -  " + Main.bossList.get(i).getPassword());
		}

	}

	private void getWaiterValues() {
		for (int i = 0; i < Main.waiterList.size(); i++) {
			users.add("Waiter  -  " + Main.waiterList.get(i).getUsername() + "  -  "
					+ Main.waiterList.get(i).getPassword());
		}
	}

	@FXML
	void clickGoBack(ActionEvent event) {
		TableMainController.getInstance().createPage(anchorPaneUserSettings, "/FXML/Table.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		users = new ArrayList<String>();
		fillTheListView();
	}

}
