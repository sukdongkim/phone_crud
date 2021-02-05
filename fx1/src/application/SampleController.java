package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SampleController {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_phone;
    

    @FXML
    void onClickOK(ActionEvent event) {
    	conn = mysqlconnect.ConnectDb();
    	String sql = "select * from student where name=?";
    	
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, txt_name.getText());
			rs = pst.executeQuery();
			
			if(rs.next()) {
				txt_phone.setText(rs.getString("phone"));
											
			} else {
				JOptionPane.showMessageDialog(null,"Try to another name ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
