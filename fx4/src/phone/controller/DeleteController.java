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

public class DeleteController {
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
	void onClickDelete(ActionEvent event) {
		conn = mysqlconnect.ConnectDb();
		String r = txt_name.getText();
		if(r.length()==0) {
			JOptionPane.showMessageDialog(null, "No Name");
			return ;
		}
		int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to delete this student?");
		if(dialogresult == JOptionPane.YES_NO_OPTION)
		{
			try {
				pst = conn.prepareStatement("delete from student where name = ?");
				pst.setString(1, r);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Student Delete!");					
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
	void onClickHome(ActionEvent event) {
		MainViewController t = new MainViewController();
		t.stopSearchView();
	}
}