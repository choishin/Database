package BasicTraining02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * BasicTraining2-(1) : ��������ǥ�� �ǽ�
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC ���̺귯���� �ҷ��´�.
													//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.147.18:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 	//(3)MYSQL�� �����Ѵ�.
													//(4)MYSQL������ ������ ��ɹ� �Է�
		stmt.execute("create table scoreTable("
				+ "number int not null primary key,"		
				+ "student_name varchar(20),"		
				+ "kor int,"						
				+ "eng int,"				
				+ "mat int,"
				+ "sum int,"
				+ "avg int);"
				);

		stmt.close(); 
		conn.close(); 


	}

}
