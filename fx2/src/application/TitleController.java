package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TitleController {
	static Stage stage;
	Parent root;
	@FXML
	void onClickSearch(ActionEvent event) {
		try {

			root = FXMLLoader.load(getClass().getResource("Search.fxml"));
			stage = new Stage();

			stage.setTitle("Searching . . . ");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait(); 


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onClickUpdate(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Update.fxml"));
			stage = new Stage();

			stage.setTitle("Updating . . . ");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait(); 


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onClickDelete(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Delete.fxml"));
			stage = new Stage();

			stage.setTitle("Deleting . . . ");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait(); 


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onClickAdd(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Add.fxml"));
			stage = new Stage();

			stage.setTitle("Adding . . . ");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait(); 


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopSearchView() {
		stage.close();
	}

}
