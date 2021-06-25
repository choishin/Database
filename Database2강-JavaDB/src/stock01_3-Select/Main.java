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
 *  Training : 일자별 주가 데이터정보로 실습, 테이블 만들기

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		/*처리시간을 찍어줄 데이터*/
		Date start = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC 라이브러리를 불러온다.
														//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL을 실행한다.
	

		String QueryTxt;
		
		/*쿼리문 실행 전부보기*/
//		QueryTxt = "select * from stockInfo_samsung";
//		QueryTxt = "select * from stockInfo_20150112";
		QueryTxt = "select * from stockInfo_dailyPrice";
		
		
		ResultSet rset = stmt.executeQuery(QueryTxt);
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(처리건수:%d)*****************************************\n",iCnt++);
			System.out.printf("단축코드	:%s\n",rset.getString(1));
			System.out.printf("일자		:%s\n",rset.getString(2));
			System.out.printf("시가		:%s원\n",rset.getString(3));
			System.out.printf("고가		:%s원\n",rset.getString(4));
			System.out.printf("저가		:%s원\n",rset.getString(5));
			System.out.printf("종가		:%s원\n",rset.getString(6));
			System.out.printf("거래량		:%s주\n",rset.getString(7));
			System.out.printf("거래대금	:%s원\n",rset.getString(8));
	
		}
		Date end = new Date();
		System.out.println("*(처리종료)*****************************************\n");
		System.out.printf("DB실행 시작시간 :%s, DB 종료시각 :%s",sdf.format(start),sdf.format(end));

		rset.close();
		stmt.close(); 
		conn.close(); 


	}

}

