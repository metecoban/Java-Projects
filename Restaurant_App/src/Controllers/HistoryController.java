package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class HistoryController implements Initializable {
	@FXML
	private AnchorPane anchorPaneHistory;

	@FXML
	private JFXListView<String> listViewHistory;

	@FXML
	private JFXButton buttonGoBack;

	private ArrayList<String> historyL;

	private void fillTheListView() {
		listViewHistory.getItems().clear();
		historyL.clear();
		getHistoryValues();

		listViewHistory.setItems(FXCollections.observableArrayList(historyL));
	}

	private void getHistoryValues() {
		for (int i = 0; i < Main.historyList.size(); i++) {
			historyL.add("Date: " + Main.historyList.get(i).getDate() + "  -  " + Main.historyList.get(i).getTable()
					+ "  -  " + Main.historyList.get(i).getGain() + "$");
		}
	}

	@FXML
	void clickGoBack(ActionEvent event) {
		TableMainController.getInstance().createPage(anchorPaneHistory, "/FXML/Table.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		historyL = new ArrayList<String>();
		fillTheListView();
	}
}
