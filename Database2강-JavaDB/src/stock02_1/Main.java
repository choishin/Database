package stock02_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * Training : ���ں� �ְ� ������������ �ǽ�, ���̺� ������ �Է�
 * (�ǽ�2 ������ �о table�� ����ֱ�, P-KEY ����ּſ� (����, �����ڵ� ���� P-KEY)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		/*������ ó���ð��� ����� Ŭ���� �ҷ�����*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC ���̺귯���� �ҷ��´�.
		//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		//��PreparedStatement�� ĳ�ÿ� SQL ������ ��� �ѹ��� ó���ϱ� ������
		//��Statement ���� ó���ӵ��� ������.
		String QueryTxt = "insert into stockInfo_dailyPrice (serialCode,date,currentPrice,highPrice,lowPrice,closingPrice,"
				+ "turnover,dealPrice) values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
		Statement stmt = conn.createStatement();	//(3)MYSQL�� �����Ѵ�.

		/*(1)�Ｚ���� �ڷ� �о����*/
		//File f = new File("C:\\Users\\�ֽ�\\Desktop\\Data\\day_data\\A005930.csv");

		/*(2)20150112 �ڷ� �о����*/
		//File f = new File("C:\\Users\\�ֽ�\\Desktop\\Data\\day_data\\20150112.csv");

		/*(3)���ں� �����͸� ���� �о����*/
		File f = new File("C:\\Users\\�ֽ�\\Desktop\\Data\\day_data\\StockDailyPrice.csv");
		BufferedReader br = new BufferedReader(new FileReader(f));

		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("�� �����Դϴ�\n");
			return;
		}
		String[] field_name = readtxt.split(",");

		int LineCnt=0;
		conn.setAutoCommit(false);
		long startTime = System.currentTimeMillis();
		while((readtxt=br.readLine())!=null) {
			String[]field = readtxt.split(",");

			pstmt.setString(1, field[2]);
			pstmt.setString(2, field[1]);
			pstmt.setString(3, field[4]);
			pstmt.setString(4, field[5]);
			pstmt.setString(5, field[6]);
			pstmt.setString(6, field[3]);
			pstmt.setString(7, field[11]);
			pstmt.setString(8, field[12]);
			pstmt.addBatch();
			pstmt.clearParameters();
			LineCnt++;
			try {
				if(LineCnt%10000 == 0) {
					pstmt.executeBatch();
					conn.commit();
					System.out.printf("%d\n",LineCnt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		try {
			pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.commit();
		conn.setAutoCommit(true);
		long endTime = System.currentTimeMillis();

		System.out.printf("Insert End\n");
		System.out.printf("total	: %d\n",LineCnt);
		System.out.printf("start	: %s\n",sdf.format(startTime));
		System.out.printf("end	: %s\n",sdf.format(endTime));

		br.close();
		stmt.close(); 
		conn.close(); 

	}

}

