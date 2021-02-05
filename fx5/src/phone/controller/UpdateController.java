package phone.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import phone.Main;
import phone.mysqlconnect;

public class UpdateController {
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
	void onClickUpdate(ActionEvent event) {
		conn = mysqlconnect.ConnectDb();
		String r1 = txt_id.getText();
		String r2 = txt_name.getText();
		String r3 = txt_email.getText();
		String r4 = txt_phone.getText();

		if(r2.length()==0) {
			JOptionPane.showMessageDialog(null, "No Name");
		}
		else {
			try {
				pst = conn.prepareStatement("update student set id= ?, email=?, phone=? where name = ?");
				pst.setString(1, r1);
				pst.setString(2, r3);
				pst.setString(3, r4);
				pst.setString(4, r2);

				JOptionPane.showMessageDialog(null, "Student update!");
				pst.executeUpdate();			

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onClickStop(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void onClickBack(ActionEvent event) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../view/MainView3.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}