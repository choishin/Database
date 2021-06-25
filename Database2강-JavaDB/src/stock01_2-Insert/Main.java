package stock01_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC ���̺귯���� �ҷ��´�.
													//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
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
		while((readtxt=br.readLine())!=null) {
			String[]field = readtxt.split(",");
			String QueryTxt;
			/*(1)�Ｚ���� �ڷ� �Է��ϱ�*/
			//QueryTxt = String.format("insert into stockInfo_samsung("
			/*(2)20150112 �ڷ� �Է��ϱ�*/
			//QueryTxt = String.format("insert into stockInfo_20150112("
			/*(3)���ں� �����͸� �Է��ϱ�*/
			 QueryTxt = String.format("insert into stockInfo_dailyPrice("
					+ "serialCode,"
					+ "date,"
					+ "currentPrice,"
					+ "highPrice,"
					+ "lowPrice,"
					+ "closingPrice,"
					+ "turnover,"
					+ "dealPrice"	
					+")"
					+"values(" 
					+ "'%s',"
					+ "'%s', %s , %s ,%s,%s,"
					+ "%s,%s"
					+");",
					field[0],
					field[1],field[4],field[5],field[6],field[3],
					field[11],field[12]
					
					);
			stmt.execute(QueryTxt);
			date = new Date();
			System.out.printf("%d��° �׸� %s Insert OK [%s] \n",LineCnt,sdf.format(date),QueryTxt);
			LineCnt++;
		}

		br.close();
		stmt.close(); 
		conn.close(); 

	}

}

