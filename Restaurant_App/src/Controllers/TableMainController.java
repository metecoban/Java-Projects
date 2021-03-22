package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import application.Main;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TableMainController implements Initializable {

	@FXML
	private AnchorPane changeblePane;

	@FXML
	private JFXButton buttonProductSettings;

	@FXML
	private JFXButton buttonUserSettings;

	@FXML
	private JFXButton buttonHistory;

	@FXML
	private Label labelName;

	@FXML
	private JFXButton buttonCloseProgram;

	AnchorPane home;

	private static TableMainController instance;

	public TableMainController() {
		instance = this;
	}

	public static TableMainController getInstance() {
		return instance;
	}

	public void setUsername() {
		labelName.setText("Welcome " + LoginController.getInstance().username());
	}

	@FXML
	void clickCloseProgram(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void clickHistory(ActionEvent event) {
		createPage(home, "/FXML/History.fxml");
	}

	@FXML
	void clickProductSettings(ActionEvent event) {
		createPage(home, "/FXML/ProductSettings.fxml");
	}

	@FXML
	void clickUserSettings(ActionEvent event) {
		if (LoginController.getInstance().getQuality().equals("Boss")) {
			createPage(home, "/FXML/UserSettings.fxml");
		} else {
			Main.giveInformationAlert("You are not enter here!", "Only boss form can be opened.");
		}
	}

	private void setNode(Node node) {
		changeblePane.getChildren().clear();
		changeblePane.getChildren().add((Node) node);

		FadeTransition ft = new FadeTransition(Duration.millis(1500));
		ft.setNode(node);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}

	public void createPage(AnchorPane pane, String location) {
		try {
			pane = FXMLLoader.load(getClass().getResource(location));
			setNode(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createPage(home, "/FXML/Table.fxml");
		setUsername();
	}
}
