package phone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import phone.Main;

public class MainViewController {
	@FXML
	AnchorPane root;
    @FXML
    void onClickOne(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/MainView2.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickSep(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/MainView3.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickClose(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void onClickHome(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/MainItem.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void onClickStop(ActionEvent event) {
    	System.exit(0);
    }

}
