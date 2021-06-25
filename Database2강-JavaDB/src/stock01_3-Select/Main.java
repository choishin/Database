package stock01_3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *  Training : ���ں� �ְ� ������������ �ǽ�, ���̺� �����

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		/*ó���ð��� ����� ������*/
		Date start = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC ���̺귯���� �ҷ��´�.
														//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL�� �����Ѵ�.
	

		String QueryTxt;
		
		/*������ ���� ���κ���*/
//		QueryTxt = "select * from stockInfo_samsung";
//		QueryTxt = "select * from stockInfo_20150112";
		QueryTxt = "select * from stockInfo_dailyPrice";
		
		
		ResultSet rset = stmt.executeQuery(QueryTxt);
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(ó���Ǽ�:%d)*****************************************\n",iCnt++);
			System.out.printf("�����ڵ�	:%s\n",rset.getString(1));
			System.out.printf("����		:%s\n",rset.getString(2));
			System.out.printf("�ð�		:%s��\n",rset.getString(3));
			System.out.printf("��		:%s��\n",rset.getString(4));
			System.out.printf("����		:%s��\n",rset.getString(5));
			System.out.printf("����		:%s��\n",rset.getString(6));
			System.out.printf("�ŷ���		:%s��\n",rset.getString(7));
			System.out.printf("�ŷ����	:%s��\n",rset.getString(8));
	
		}
		Date end = new Date();
		System.out.println("*(ó������)*****************************************\n");
		System.out.printf("DB���� ���۽ð� :%s, DB ����ð� :%s",sdf.format(start),sdf.format(end));

		rset.close();
		stmt.close(); 
		conn.close(); 


	}

}

