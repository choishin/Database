package Examtable02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(2) : JDBC ���̺귯���� �����ؼ� MYSQL�ȿ� �ִ� table�� �����
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//Class.forName : JDBC ���̺귯���� �ҷ��� 
														//Connction : �ܺο��� �ҷ��� MYSQL�� ������ ��ο� ���̵�, ��й�ȣ�� �Է�
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement(); 		//Statement : SQL ������ �����ϴ� ����
	     
	     stmt.execute("delete from examtable2;"); 		//<-��ɾ��Է� (table�� ������ ���� ������)
	   
	     stmt.close(); 
	     conn.close(); 

		
	}

}
