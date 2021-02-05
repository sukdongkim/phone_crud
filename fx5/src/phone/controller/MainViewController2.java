package phone.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import phone.Student;
import phone.mysqlconnect;

public class MainViewController2 {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Statement stmt = null;
	String index,sql;
	ObservableList<Student> list;
	static Stage stage;
	Parent root;

    @FXML
    private TableView<Student> tableContent;

    @FXML
    private TableColumn<Student, String> col_id;

    @FXML
    private TableColumn<Student, String> col_name;

    @FXML
    private TableColumn<Student, String> col_email;

    @FXML
    private TableColumn<Student, String> col_phone;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_name;
    
    @FXML
    private void initialize() {
    	conn = mysqlconnect.ConnectDb();
    	tablelookup();
    }
    
	public void tablelookup() {
	
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from student");

			list = FXCollections.observableArrayList();
			while(rs.next()) {

				String r1 = rs.getString("id");
				String r2 = rs.getString("name");
				String r3 = rs.getString("email");
				String r4 = rs.getString("phone");				

				Student student = new Student(r1,r2,r3,r4);	

				list.add(student);

				col_id.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
				col_name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
				col_email.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
				col_phone.setCellValueFactory(new PropertyValueFactory<Student,String>("phone"));										
 
				tableContent.setItems(list);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	void onMouseClicked(MouseEvent event) {
		TableViewSelectionModel<Student> model = tableContent.getSelectionModel();

		Student s = (Student)model.getSelectedItem();
		txt_id.setText(s.getId());
		txt_name.setText(s.getName());
		txt_email.setText(s.getEmail());
		txt_phone.setText(s.getPhone());

		index = s.getId();
	}
	
    @FXML
    void onClickAdd(ActionEvent event) {
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

		
		tablelookup();
		cleartext();
    }

    @FXML
    void onClickDelete(ActionEvent event) {
//		String r1 = txt_id.getText();
		if(index.length()==0) {
			JOptionPane.showMessageDialog(null, "No id");
			return ;
		}
		int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to delete this student?");
		if(dialogresult == JOptionPane.YES_NO_OPTION)
		{
			try {
				pst = conn.prepareStatement("delete from student where id = ?");
				pst.setString(1, index);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Student Deleted!");
				tablelookup();						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cleartext();
    }
 

    @FXML
    void onClickDone(ActionEvent event) {
		int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to finish ?");
		if(dialogresult == JOptionPane.YES_NO_OPTION)
		{
//			JOptionPane.showMessageDialog(null, "Bye !!!");
			System.exit(0);
		}
		
		else
			return;
    }

    @FXML
    void onClickClear(ActionEvent event) {
    	cleartext();
    }
    @FXML
    void onClickUpdate(ActionEvent event) {
		String r1 = txt_id.getText();
		String r2 = txt_name.getText();
		String r3 = txt_email.getText();
		String r4 = txt_phone.getText();
		if(r1.length()==0) {
			JOptionPane.showMessageDialog(null, "No id");
			return ;
		}

		try {
			pst = conn.prepareStatement("update student set name= ?, email=?,phone=? where id = ?");
			pst.setString(1, r2);
			pst.setString(2, r3);
			pst.setString(3, r4);
			pst.setString(4, r1);


			if(index.equals(r1)) {
				JOptionPane.showMessageDialog(null, "Student update!");
				pst.executeUpdate();
			} else
			{
				JOptionPane.showMessageDialog(null, "Different id");
			}
			
			cleartext();
			tablelookup();	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void cleartext() {
		txt_id.setText("");
		txt_name.setText("");
		txt_email.setText("");
		txt_phone.setText("");
	}

}
