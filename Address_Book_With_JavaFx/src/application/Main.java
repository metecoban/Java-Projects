package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Mete Coban - 181805008
 * @author Abdullah Sezdi - 181805022
 * @author Kerem Beytullah Aydogdu - 181805006
 *
 */
public class Main extends Application {
	int iter = 0;
	ArrayList<Person> people = new ArrayList<Person>();
	Alert alert = new Alert(AlertType.INFORMATION);
	final int ID_SIZE = 4;
	final int NAME_SIZE = 32;
	final int STREET_SIZE = 32;
	final int CITY_SIZE = 20;
	final int GENDER_SIZE = 1;
	final int ZIP_SIZE = 5;

	public Main() {
		FileOperations.readFromTxt(people);
		setTextFields(0);
	}

	Button buttonAdd = new Button("Add");
	Button buttonFirst = new Button("First");
	Button buttonNext = new Button("Next");
	Button buttonPrevious = new Button("Previous");
	Button buttonLast = new Button("Last");
	Button buttonUpdateById = new Button("UpdateByID");
	Button buttonSearchById = new Button("SearchByID");
	Button buttonCleanTextFields = new Button("Clean TextFields");

	Label labelId = new Label("ID");
	Label labelSearchUpdateId = new Label("Search/Update ID");
	Label labelName = new Label("Name");
	Label labelStreet = new Label("Street");
	Label labelCity = new Label("City");
	Label labelGender = new Label("Gender");
	Label labelZip = new Label("Zip");

	TextField fieldId = new TextField("1");
	TextField fieldSearchUpdateId = new TextField();
	TextField fieldName = new TextField();
	TextField fieldStreet = new TextField();
	TextField fieldCity = new TextField();
	TextField fieldGender = new TextField();
	TextField fieldZip = new TextField();

	public void setTextFields(int temp) {
		fieldId.setText(String.valueOf(people.get(temp).getId()));
		fieldName.setText(String.valueOf(people.get(temp).getName()));
		fieldStreet.setText(String.valueOf(people.get(temp).getStreet()));
		fieldCity.setText(String.valueOf(people.get(temp).getCity()));
		fieldGender.setText(String.valueOf(people.get(temp).getGender()));
		fieldZip.setText(String.valueOf(people.get(temp).getZip()));
	}

	public void giveInformationAlert(String information) {
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(information);
		alert.showAndWait();
	}

	public void textFieldControl() throws Exception {
		if (fieldName.getText().trim().isEmpty() || fieldStreet.getText().trim().isEmpty()
				|| fieldCity.getText().trim().isEmpty() || fieldGender.getText().trim().isEmpty()
				|| fieldZip.getText().trim().isEmpty()) {
			throw new Exception();
		} else if ((fieldGender.getText().trim().equals("F") || fieldGender.getText().trim().equals("M")) == false) {
			throw new Exception();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		fieldId.setTextFormatter(new TextFormatter<>((change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > ID_SIZE) {
				return null;
			} else {
				return change;
			}
		}));

		fieldName.setTextFormatter(new TextFormatter<>((change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > NAME_SIZE) {
				return null;
			} else {
				return change;
			}
		}));

		fieldStreet.setTextFormatter(new TextFormatter<>((change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > STREET_SIZE) {
				return null;
			} else {
				return change;
			}
		}));

		fieldCity.setTextFormatter(new TextFormatter<>((change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > CITY_SIZE) {
				return null;
			} else {
				return change;
			}
		}));

		fieldGender.setTextFormatter(new TextFormatter<>((change) -> {
			change.setText(change.getText().toUpperCase());
			String newText = change.getControlNewText();
			if (newText.length() > GENDER_SIZE) {
				return null;
			} else {
				return change;
			}
		}));

		fieldZip.setTextFormatter(new TextFormatter<>((change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > ZIP_SIZE) {
				return null;
			} else {
				return change;
			}
		}));

		buttonAdd.setOnAction(e -> {
			try {
				textFieldControl();
				people.add(new Person(people.get(people.size() - 1).getId() + 1, fieldName.getText().trim(),
						fieldStreet.getText().trim(), fieldCity.getText().trim(), fieldGender.getText(),
						fieldZip.getText().trim()));
				FileOperations.writeToFile(people);
				giveInformationAlert("Record is added successfully.");
			} catch (Exception c) {
				giveInformationAlert("Record isn't added successfully. Control the text areas and try again.");
			}
		});

		buttonFirst.setOnAction(e -> {
			setTextFields(0);
			iter = 0;
		});

		buttonNext.setOnAction(e -> {
			iter += 1;
			if (iter < people.size()) {
				setTextFields(iter);
			} else {
				iter -= 1;
			}
		});

		buttonPrevious.setOnAction(e -> {
			iter -= 1;
			if (iter > -1) {
				setTextFields(iter);
			} else {
				iter += 1;
			}
		});

		buttonLast.setOnAction(e -> {
			int temp = people.size() - 1;
			setTextFields(temp);
			iter = temp;
		});

		buttonUpdateById.setOnAction(e -> {
			try {
				textFieldControl();
				int temp = Integer.parseInt(fieldSearchUpdateId.getText()) - 1;
				people.get(temp).setName(fieldName.getText().trim());
				people.get(temp).setStreet(fieldStreet.getText().trim());
				people.get(temp).setCity(fieldCity.getText().trim());
				people.get(temp).setGender(fieldGender.getText());
				people.get(temp).setZip(fieldZip.getText().trim());
				FileOperations.updateToFile(people);
				giveInformationAlert("Update is added successfully.");
			} catch (Exception c) {
				giveInformationAlert("Update isn't added successfully. Control the text areas and try again.");
			}
		});

		buttonSearchById.setOnAction(e -> {
			try {
				int temp = Integer.parseInt(fieldSearchUpdateId.getText()) - 1;
				setTextFields(temp);
			} catch (Exception c) {
				giveInformationAlert(
						"Search isn't successfully. Control the Search/UpdateBy ID text area and try again.");
			}
		});

		buttonCleanTextFields.setOnAction(e -> {
			fieldId.setText("");
			fieldSearchUpdateId.setText("");
			fieldName.setText("");
			fieldStreet.setText("");
			fieldCity.setText("");
			fieldGender.setText("");
			fieldZip.setText("");
		});

		labelId.setPadding(new Insets(0, 20, 0, 0));
		labelCity.setPadding(new Insets(0, 11, 0, 0));
		fieldId.setEditable(false);
		fieldId.setDisable(true);
		fieldZip.setPrefSize(60, 25);
		fieldId.setPrefSize(60, 25);
		fieldCity.setPrefSize(147, 25);
		fieldGender.setPrefSize(27, 25);
		fieldName.setPrefSize(306, 25);
		fieldStreet.setPrefSize(306, 25);
		fieldSearchUpdateId.setPrefSize(144, 25);

		AnchorPane rootAnchorPane = new AnchorPane();
		HBox rootHBox1 = new HBox(labelId, fieldId, labelSearchUpdateId, fieldSearchUpdateId);
		rootHBox1.setSpacing(4);
		HBox rootHBox2 = new HBox(labelName, fieldName);
		rootHBox2.setSpacing(4);
		HBox rootHBox3 = new HBox(labelStreet, fieldStreet);
		rootHBox3.setSpacing(4);
		HBox rootHBox4 = new HBox(labelCity, fieldCity, labelGender, fieldGender, labelZip, fieldZip);
		rootHBox4.setSpacing(4);
		VBox rootVBox = new VBox(rootHBox1, rootHBox2, rootHBox3, rootHBox4);
		rootVBox.setSpacing(4);
		HBox rootHBox = new HBox(buttonAdd, buttonFirst, buttonNext, buttonPrevious, buttonLast, buttonUpdateById,
				buttonSearchById, buttonCleanTextFields);
		rootHBox.setSpacing(4);
		AnchorPane.setTopAnchor(rootHBox, 154.0);
		AnchorPane.setLeftAnchor(rootHBox, 25.0);
		AnchorPane.setTopAnchor(rootVBox, 20.0);
		AnchorPane.setLeftAnchor(rootVBox, 102.0);
		rootAnchorPane.getChildren().addAll(rootVBox, rootHBox);
		Scene scene = new Scene(rootAnchorPane, 549, 180);

		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
