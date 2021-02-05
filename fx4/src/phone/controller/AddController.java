package phone.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import phone.mysqlconnect;

public class AddController {
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
	void onClickConfirm(ActionEvent event) {
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
	void onClickAdd(ActionEvent event) {
		conn = mysqlconnect.ConnectDb();
		String r1 = txt_id.getText();
		if(r1.length()==0) {
			JOptionPane.showMessageDialog(null, "No Record");
			return ;
		}
		
		String r2 = txt_name.getText();
		String r3 = txt_email.getText();
		String r4 = txt_phone.getText();

		try {
			pst = conn.prepareStatement("insert into student (id,name, email, phone) values (?,?,?,?)");
			pst.setString(1, r1);
			pst.setString(2, r2);
			pst.setString(3, r3);
			pst.setString(4, r4);
			int k =pst.executeUpdate();

			if(k==1) {
				JOptionPane.showMessageDialog(null, "Add Student !!!");
			} else {
				JOptionPane.showMessageDialog(null, "Error!");
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Duplicate!");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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