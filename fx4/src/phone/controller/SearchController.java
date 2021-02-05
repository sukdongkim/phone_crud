package phone.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import phone.mysqlconnect;

public class SearchController {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String sql;

	@FXML
	private TextField txt_name;

	@FXML
	private TextField txt_id;

	@FXML
	private TextField txt_email;

	@FXML
	private TextField txt_phone;
	
	@FXML
	void onClickSearch(ActionEvent event) {
		conn = mysqlconnect.ConnectDb();
		sql = "select * from student where name=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, txt_name.getText());
			rs = pst.executeQuery();

			if(rs.next()) {
				txt_id.setText(rs.getString("id"));
				txt_email.setText(rs.getString("email"));
				txt_phone.setText(rs.getString("phone"));

			} else {
				JOptionPane.showMessageDialog(null,"Try to another name ");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    @FXML
    void onClickClear(ActionEvent event) {
    	txt_name.setText("");
    	txt_id.setText("");
    	txt_email.setText("");
    	txt_phone.setText("");
    }

    @FXML
    void onClickStop(ActionEvent event) {
    	System.exit(0);
    }
    
	@FXML
	void onClickHome(ActionEvent event) {
		MainViewController t = new MainViewController();
		t.stopSearchView();
	}


}