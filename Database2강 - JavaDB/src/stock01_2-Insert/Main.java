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
 * Training : 일자별 주가 데이터정보로 실습, 테이블에 데이터 입력
 * (실습2 파일을 읽어서 table에 집어넣기, P-KEY 잡아주셔요 (일자, 단축코드 복합 P-KEY)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		/*데이터 처리시간을 찍어줄 클래스 불러오기*/
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC 라이브러리를 불러온다.
													//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement();	//(3)MYSQL을 실행한다.
		
		/*(1)삼성전자 자료 읽어오기*/
		//File f = new File("C:\\Users\\최신\\Desktop\\Data\\day_data\\A005930.csv");
		
		/*(2)20150112 자료 읽어오기*/
		//File f = new File("C:\\Users\\최신\\Desktop\\Data\\day_data\\20150112.csv");
		
		/*(3)일자별 데이터를 전부 읽어오기*/
		File f = new File("C:\\Users\\최신\\Desktop\\Data\\day_data\\StockDailyPrice.csv");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("빈 파일입니다\n");
			return;
		}
		String[] field_name = readtxt.split(",");
		
		int LineCnt=0;
		while((readtxt=br.readLine())!=null) {
			String[]field = readtxt.split(",");
			String QueryTxt;
			/*(1)삼성전자 자료 입력하기*/
			//QueryTxt = String.format("insert into stockInfo_samsung("
			/*(2)20150112 자료 입력하기*/
			//QueryTxt = String.format("insert into stockInfo_20150112("
			/*(3)일자별 데이터를 입력하기*/
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
			System.out.printf("%d번째 항목 %s Insert OK [%s] \n",LineCnt,sdf.format(date),QueryTxt);
			LineCnt++;
		}

		br.close();
		stmt.close(); 
		conn.close(); 

	}

}

