package DBtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBtest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");  //�ܺ� ���̺귯���� �θ��� ���� ��ɾ�
		//�ܺο��� �ҷ��� MYSQL�� ������ ��ο� ���̵�, ��й�ȣ�� �Է�
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	   //SQL ������ �����ϴ� ����
	     Statement stmt = conn.createStatement(); 
	     //�������� ����Ǿ� ����� ��ȯ�� �����͵��� �����ϴ� Ŭ����
	     ResultSet rset = stmt.executeQuery("select * from examtable"); 
	     System.out.println("===========================================");
	     while (rset.next()) { 
	          //����� ó�� (��MYSQL�� 0���� ������ ����)
	    	 System.out.println("�̸� :"+rset.getString(1)); 	//������ �������� 1�� �ʵ带 �����´�
	    	 System.out.println("�й� :"+rset.getString(2));	//������ �������� 2�� �ʵ带 �����´�
	    	 System.out.println("���� :"+rset.getString(3));	//������ �������� 3�� �ʵ带 �����´�
	    	 System.out.println("���� :"+rset.getString(4));	//������ �������� 4�� �ʵ带 �����´�
	    	 System.out.println("���� :"+rset.getString(5));	//������ �������� 5�� �ʵ带 �����´�
	    	 System.out.println("===========================================");
	      } 
	     rset.close(); 
	     stmt.close(); 
	     conn.close(); 


	}

}
