package Examtable04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(4) :JDBC ���̺귯���� �ҷ��� �ڷ� �о����
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		 //(1)JDBC ���̺귯���� �ҷ��´�.
														 //(2)MYSQL�� �����Ѵ�
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement(); 		 //(3)MYSQL�� �����Ѵ�.
	     												 //��ResultSet : �������� ����Ǿ� ����� ��ȯ�� �����͸� �����ϴ� Ŭ����
	     //(4) select~ �� ����Ǵ� ������� ��������	 //��ResultSet�� executeQuery()�޼ҵ�, Statement�� execute() �޼ҵ�
	     ResultSet rset = stmt.executeQuery("select * from examtable"); 
	     System.out.printf("  �̸� ��  ��  ����  ����  ����\n");
	     while (rset.next()) { 
	          											//����� ó�� (��MYSQL�� 0���� ������ ����)
	    	 											//getString(), getInt() �޼ҵ带 �̿��� ��� ��������
	    	 System.out.printf("%4s %6d %4d %5d %5d\n",rset.getString(2),rset.getInt(1),
	    			 rset.getInt(3),rset.getInt(4),rset.getInt(5));
	      }
	     
	     rset.close();
	     stmt.close(); 
	     conn.close(); 

		
	}

}
