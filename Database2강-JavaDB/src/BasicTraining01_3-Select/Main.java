package BasicTraining01_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Basic Training(1) : ���� ������ ����, ������ �о���� (�ǽ�3 �ִܰŸ� ��� ��ȸ�ϴ� �ǽ��ǽ�)

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC ���̺귯���� �ҷ��´�.
														//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL�� �����Ѵ�.
	
		/*���� ��ġ�� ��Ÿ���� ����,�浵*/
		double lat= 37.3860521; 
		double lng = 127.1214038;

		String QueryTxt;
		/* ���� ����� �� ã��*/
		QueryTxt = String.format("select * from parkinglotInfo where "
				+ "SQRT(POWER(latitude - %f,2) + POWER (longitude - %f,2)) = "
				+ "(select MIN(SQRT(POWER(latitude - %f,2) + POWER(longitude - %f,2))) from parkinglotInfo);",lat,lng,lat,lng);
		
		/*���κ���*/
		//QueryTxt = "select * from parkinglotInfo";
		/*�������� ���￡ �����鼭, ������ �� ã��*/
		//QueryTxt = "select * from freewifi where classification='����'and city='����Ư����'";	

		ResultSet rset = stmt.executeQuery(QueryTxt);
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(%d)*****************************************\n",iCnt++);
			System.out.printf("�����������ȣ		:%s\n",rset.getString(1));
			System.out.printf("�������		:%s\n",rset.getString(2));
			System.out.printf("�浵			:%s\n",rset.getString(3));
			System.out.printf("����			:%s\n",rset.getString(4));
			System.out.printf("�����屸��		:%s\n",rset.getString(5));
			System.out.printf("����������		:%s\n",rset.getString(6));
			System.out.printf("�����������ּ�		:%s\n",rset.getString(7));
			System.out.printf("�����嵵�θ��ּ�	:%s\n",rset.getString(8));
			System.out.printf("������ȹ��		:%s\n",rset.getString(9));
			System.out.printf("�����		:%s\n",rset.getString(10));
			System.out.printf("���Ͽ���۽ð�	:%s\n",rset.getString(11));
			System.out.printf("���Ͽ����ð�	:%s\n",rset.getString(12));
			System.out.printf("����Ͽ���۽ð�	:%s\n",rset.getString(13));
			System.out.printf("����Ͽ����ð�	:%s\n",rset.getString(14));
			System.out.printf("�����Ͽ���۽ð�	:%s\n",rset.getString(15));
			System.out.printf("�����Ͽ����ð�	:%s\n",rset.getString(16));
			System.out.printf("�������		:%s\n",rset.getString(17));
			System.out.printf("���������		:%s\n",rset.getString(18));
			System.out.printf("��������		:%s\n",rset.getString(19));
			System.out.printf("��������	�ñ���	:%s\n",rset.getString(20));
			System.out.printf("�����߽���ǥ(X��ǥ)	:%s\n",rset.getString(21));
			System.out.printf("�����߽���ǥ(y��ǥ)	:%s\n",rset.getString(22));
			System.out.printf("�����ڵ�		:%s\n",rset.getString(23));
			System.out.printf("����ó			:%s\n",rset.getString(24));
			System.out.printf("��������		:%s\n",rset.getString(25));

		}

		rset.close();
		stmt.close(); 
		conn.close(); 


	}

}

