package Examtable01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(1) : JDBC�� �ҷ��ͼ� MYSQL�� ���̺� ����� 
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		//Class.forName : JDBC ���̺귯���� �ҷ��� 
														//Connction : �ܺο��� �ҷ��� MYSQL�� ������ ��ο� ���̵�, ��й�ȣ�� �Է�
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement(); 		//Statement : SQL ������ �����ϴ� ����
	     
	     stmt.execute("create table examtable2("+ 		//stmt.execute(); -> MYSQL ������ ������ ��ɹ�
	    		 "name varchar(20),"+
	    		 "studentid int not null primary key,"+
	    		 "kor int,"+
	    		 "eng int,"+
	    		 "mat int)"
	    		 );
	   
	     stmt.close(); //���ఴü�� �ݱ�
	     conn.close(); //���ᰴü�� �ݱ�

		
	}

}
