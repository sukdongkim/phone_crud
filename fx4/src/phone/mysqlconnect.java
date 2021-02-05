package phone;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class mysqlconnect {
	static Connection conn;

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?serverTimezone=UTC", "root","brd901as-kim");
	//		JOptionPane.showMessageDialog(null, "Connection Established !!!");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		} 
	}
}