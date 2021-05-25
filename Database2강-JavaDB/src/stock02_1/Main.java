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
 * Training : 일자별 주가 데이터정보로 실습, 테이블에 데이터 입력
 * (실습2 파일을 읽어서 table에 집어넣기, P-KEY 잡아주셔요 (일자, 단축코드 복합 P-KEY)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		/*데이터 처리시간을 찍어줄 클래스 불러오기*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC 라이브러리를 불러온다.
		//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		//※PreparedStatement는 캐시에 SQL 문장을 담아 한번에 처리하기 때문에
		//※Statement 보다 처리속도가 빠르다.
		String QueryTxt = "insert into stockInfo_dailyPrice (serialCode,date,currentPrice,highPrice,lowPrice,closingPrice,"
				+ "turnover,dealPrice) values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = conn.prepareStatement(QueryTxt);
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

