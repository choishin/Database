package freewifi03;

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
 * free wifi(3) : ���� �������� ����, ������ �о���� (�ǽ�1 ���� ����� �� ã��)
 * 													  (�ǽ�2 ���� ����)
 * 													  (�ǽ�3 SKT���� ã��)
 *													  (�ǽ�4 �����ò� ã��)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		//(1)JDBC ���̺귯���� �ҷ��´�.
														//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL�� �����Ѵ�.

		double lat=37.3860521; 
		double lng =127.1214038;

		String QueryTxt;
		//��where ���� ���� ����!!
		/*�ǽ�1 ���� ����� �� ã��*/
		QueryTxt = String.format("select * from freewifi where "
				+ "SQRT(POWER(latitude - %f,2) + POWER (longtitude - %f,2)) = "
				+ "(select MIN(SQRT(POWER(latitude - %f,2) + POWER(longtitude - %f,2))) from freewifi);",lat,lng,lat,lng);
		/*�ǽ�2 ���� ����*/									
		//QueryTxt = "select * from freewifi";	
		/*�ǽ�3 SKT���� ã��*/
		//QueryTxt = "select * from freewifi where service_provider='SKT'";	
		/*�ǽ�4 �����ò� ã�� �ؼ���Ư���� ������ �־ '������'�� �ٲ㺸�ҽ��ϴ�*/
		//QueryTxt = "select * from freewifi where inst_country='������'";		


		ResultSet rset = stmt.executeQuery(QueryTxt);
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(%d)*****************************************\n",iCnt++);
			System.out.printf("��ġ��Ҹ�		:%s\n",rset.getString(2));
			System.out.printf("��ġ��һ�		:%s\n",rset.getString(3));
			System.out.printf("��ġ�õ���		:%s\n",rset.getString(4));
			System.out.printf("��ġ�ñ�����		:%s\n",rset.getString(5));
			System.out.printf("��ġ�ü�����		:%s\n",rset.getString(6));
			System.out.printf("�����������		:%s\n",rset.getString(7));
			System.out.printf("��������SSID		:%s\n",rset.getString(8));
			System.out.printf("��ġ���		:%s\n",rset.getString(9));
			System.out.printf("���������θ��ּ�	:%s\n",rset.getString(10));
			System.out.printf("�����������ּ�		:%s\n",rset.getString(11));
			System.out.printf("���������		:%s\n",rset.getString(12));
			System.out.printf("���������ȭ��ȣ	:%s\n",rset.getString(13));
			System.out.printf("����			:%s\n",rset.getString(14));
			System.out.printf("�浵			:%s\n",rset.getString(15));
			System.out.printf("�����ͱ�������		:%s\n",rset.getString(16));
		}

		rset.close();
		stmt.close(); 
		conn.close(); 


	}

}

