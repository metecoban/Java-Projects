package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private JFXTextField textfUsername;

	@FXML
	private JFXPasswordField textfPassword;

	@FXML
	private JFXButton btnLogin;

	private static LoginController instance;

	private boolean control = false;

	private String quality;

	public LoginController() {
		instance = this;
	}

	public static LoginController getInstance() {
		return instance;
	}

	public String username() {
		return textfUsername.getText();
	}

	public String getQuality() {
		return quality;
	}

	private void userControl() {
		Main.bossList.forEach((e) -> {
			if (e.getUsername().equals(textfUsername.getText().trim())
					&& e.getPassword().equals(textfPassword.getText().trim())) {
				quality = "Boss";
				control = true;
			}
		});
		Main.waiterList.forEach((e) -> {
			if (e.getUsername().equals(textfUsername.getText().trim())
					&& e.getPassword().equals(textfPassword.getText().trim())) {
				quality = "Waiter";
				control = true;
			}
		});
	}

	@FXML
	void login(ActionEvent event) throws IOException {
		userControl();
		if (control == true) {
			btnLogin.getScene().getWindow().hide();
			Stage tableMain = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/TableMain.fxml"));
			Scene scene = new Scene(root);
			tableMain.getIcons().add(new Image("/Icons/restaurant.png"));
			tableMain.setTitle("Coban Restaurant");
			tableMain.setScene(scene);
			tableMain.show();
			tableMain.setResizable(false);
		} else {
			Main.giveInformationAlert("You entered invalid username or password.", "Try again with valid information.");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
