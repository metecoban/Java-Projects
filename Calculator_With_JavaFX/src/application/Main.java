package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author MeteCoban
 */

public class Main extends Application {
	String temp;
	String option;
	boolean control = false; // En son bunu yapacakken kaldým

	Label label = new Label("0");
	Button button0 = new Button("0");
	Button button1 = new Button("1");
	Button button2 = new Button("2");
	Button button3 = new Button("3");
	Button button4 = new Button("4");
	Button button5 = new Button("5");
	Button button6 = new Button("6");
	Button button7 = new Button("7");
	Button button8 = new Button("8");
	Button button9 = new Button("9");
	Button buttonAC = new Button("AC");
	Button buttonPlusMinus = new Button("+/-");
	Button buttonPercent = new Button("%");
	Button buttonDivide = new Button("÷");
	Button buttonMultiply = new Button("x");
	Button buttonSubtraction = new Button("-");
	Button buttonPlus = new Button("+");
	Button buttonEqual = new Button("=");
	Button buttonDot = new Button(".");

	public void allNumberButtons(String temp) {

		if (label.getText().equals("0") || control == true) {
			label.setText(temp);
			control = false;
		} else {
			lastControlForLabel(label.getText() + temp);
		}
	}

	public void setLabelText(float result) {
		if (result % 2 == 0) {
			lastControlForLabel(String.valueOf((int) result));
		} else {
			lastControlForLabel(String.valueOf(result));
		}
	}
	
	public void lastControlForLabel(String text) {
		if (text.length()<8) {
			label.setText(text);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			label.setTextFill(Color.WHITE);
			buttonAC.setStyle("-fx-background-color: a6a6a6 ;");
			buttonPlusMinus.setStyle("-fx-background-color: a6a6a6 ;");
			buttonPercent.setStyle("-fx-background-color: a6a6a6 ;");
			buttonDivide.setStyle("-fx-background-color: ff9501 ;");
			buttonMultiply.setStyle("-fx-background-color: ff9501 ;");
			buttonSubtraction.setStyle("-fx-background-color: ff9501 ;");
			buttonPlus.setStyle("-fx-background-color: ff9501 ;");
			buttonEqual.setStyle("-fx-background-color: ff9501 ;");
			button0.setStyle("-fx-background-color: 3d3d3d ;");
			button1.setStyle("-fx-background-color: 3d3d3d ;");
			button2.setStyle("-fx-background-color: 3d3d3d ;");
			button3.setStyle("-fx-background-color: 3d3d3d ;");
			button4.setStyle("-fx-background-color: 3d3d3d ;");
			button5.setStyle("-fx-background-color: 3d3d3d ;");
			button6.setStyle("-fx-background-color: 3d3d3d ;");
			button7.setStyle("-fx-background-color: 3d3d3d ;");
			button8.setStyle("-fx-background-color: 3d3d3d ;");
			button9.setStyle("-fx-background-color: 3d3d3d ;");
			buttonDot.setStyle("-fx-background-color: 3d3d3d ;");
			button0.setStyle("-fx-min-width: 172px; " + "-fx-max-width: 172px; ");
			buttonDivide.setTextFill(Color.WHITE);
			buttonMultiply.setTextFill(Color.WHITE);
			buttonSubtraction.setTextFill(Color.WHITE);
			buttonPlus.setTextFill(Color.WHITE);
			buttonEqual.setTextFill(Color.WHITE);

			buttonAC.setOnAction(e -> {
				label.setText("0");
				option = "";
				temp = "";
			});
			buttonPlusMinus.setOnAction(e -> {
				if (label.getText().charAt(0) == '-') {
					label.setText(label.getText().replace("-", ""));
				} else {
					label.setText("-" + label.getText());
				}
			});
			buttonPercent.setOnAction(e -> {
				setLabelText(Float.parseFloat(label.getText()) / 100);
			});
			buttonDivide.setOnAction(e -> {
				option = "/";
				temp = label.getText();
				control = true;
			});
			buttonMultiply.setOnAction(e -> {
				option = "*";
				temp = label.getText();
				control = true;
			});
			buttonSubtraction.setOnAction(e -> {
				option = "-";
				temp = label.getText();
				control = true;
			});
			buttonPlus.setOnAction(e -> {
				option = "+";
				temp = label.getText();
				control = true;
			});
			buttonEqual.setOnAction(e -> {
				if (option.equals("") == false && temp != "") {
					if (option.equals("+")) {
						setLabelText(Float.parseFloat(temp) + Float.parseFloat(label.getText()));
					} else if (option.equals("/")) {
						if (label.getText().equals("0")) {
							label.setText("EROR");
						} else {
							setLabelText(Float.parseFloat(temp) / Float.parseFloat(label.getText()));
						}
					} else if (option.equals("*")) {
						setLabelText(Float.parseFloat(temp) * Float.parseFloat(label.getText()));
					} else if (option.equals("-")) {
						setLabelText(Float.parseFloat(temp) - Float.parseFloat(label.getText()));
					}
				}
				temp = "";
			});
			buttonDot.setOnAction(e -> {
				if (label.getText().charAt(label.getText().length() - 1) != '.') {
					label.setText(label.getText() + ".");
				}
			});
			button0.setOnAction(e -> {
				allNumberButtons("0");
			});
			button1.setOnAction(e -> {
				allNumberButtons("1");
			});
			button2.setOnAction(e -> {
				allNumberButtons("2");
			});
			button3.setOnAction(e -> {
				allNumberButtons("3");
			});
			button4.setOnAction(e -> {
				allNumberButtons("4");
			});
			button5.setOnAction(e -> {
				allNumberButtons("5");
			});
			button6.setOnAction(e -> {
				allNumberButtons("6");
			});
			button7.setOnAction(e -> {
				allNumberButtons("7");
			});
			button8.setOnAction(e -> {
				allNumberButtons("8");
			});
			button9.setOnAction(e -> {
				allNumberButtons("9");
			});

			AnchorPane root = new AnchorPane();
			HBox rootHBox = new HBox(buttonAC, buttonPlusMinus, buttonPercent, buttonDivide);
			rootHBox.setSpacing(12);
			HBox rootHBox2 = new HBox(button7, button8, button9, buttonMultiply);
			rootHBox2.setSpacing(12);
			HBox rootHBox3 = new HBox(button4, button5, button6, buttonSubtraction);
			rootHBox3.setSpacing(12);
			HBox rootHBox4 = new HBox(button1, button2, button3, buttonPlus);
			rootHBox4.setSpacing(12);
			HBox rootHBox5 = new HBox(button0, buttonDot, buttonEqual);
			rootHBox5.setSpacing(12);
			VBox rootVBox = new VBox(rootHBox, rootHBox2, rootHBox3, rootHBox4, rootHBox5);
			rootVBox.setSpacing(10);
			root.getChildren().addAll(label, rootVBox);
			root.setStyle("-fx-background-color: black ;");

			AnchorPane.setTopAnchor(rootVBox, 140.0);
			AnchorPane.setLeftAnchor(rootVBox, 20.0);
			AnchorPane.setRightAnchor(label, 24.0);
			AnchorPane.setTopAnchor(label, 40.0);
			Scene scene = new Scene(root, 400, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
