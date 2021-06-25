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
 * free wifi(3) : 무료 와이파이 정보, 데이터 읽어오기 (실습1 나와 가까운 곳 찾기)
 * 													  (실습2 전부 보기)
 * 													  (실습3 SKT꺼만 찾기)
 *													  (실습4 수원시꺼 찾기)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		//(1)JDBC 라이브러리를 불러온다.
														//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL을 실행한다.

		double lat=37.3860521; 
		double lng =127.1214038;

		String QueryTxt;
		//※where 다음 띄어쓰기 주의!!
		/*실습1 나와 가까운 곳 찾기*/
		QueryTxt = String.format("select * from freewifi where "
				+ "SQRT(POWER(latitude - %f,2) + POWER (longtitude - %f,2)) = "
				+ "(select MIN(SQRT(POWER(latitude - %f,2) + POWER(longtitude - %f,2))) from freewifi);",lat,lng,lat,lng);
		/*실습2 전부 보기*/									
		//QueryTxt = "select * from freewifi";	
		/*실습3 SKT꺼만 찾기*/
		//QueryTxt = "select * from freewifi where service_provider='SKT'";	
		/*실습4 수원시꺼 찾기 ※서울특별시 정보만 있어서 '성동구'로 바꿔보았습니다*/
		//QueryTxt = "select * from freewifi where inst_country='성동구'";		


		ResultSet rset = stmt.executeQuery(QueryTxt);
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(%d)*****************************************\n",iCnt++);
			System.out.printf("설치장소명		:%s\n",rset.getString(2));
			System.out.printf("설치장소상세		:%s\n",rset.getString(3));
			System.out.printf("설치시도명		:%s\n",rset.getString(4));
			System.out.printf("설치시군구명		:%s\n",rset.getString(5));
			System.out.printf("설치시설구분		:%s\n",rset.getString(6));
			System.out.printf("서비스제공사명		:%s\n",rset.getString(7));
			System.out.printf("와이파이SSID		:%s\n",rset.getString(8));
			System.out.printf("설치년월		:%s\n",rset.getString(9));
			System.out.printf("소재지도로명주소	:%s\n",rset.getString(10));
			System.out.printf("소재지지번주소		:%s\n",rset.getString(11));
			System.out.printf("관리기관명		:%s\n",rset.getString(12));
			System.out.printf("관리기관전화번호	:%s\n",rset.getString(13));
			System.out.printf("위도			:%s\n",rset.getString(14));
			System.out.printf("경도			:%s\n",rset.getString(15));
			System.out.printf("데이터기준일자		:%s\n",rset.getString(16));
		}

		rset.close();
		stmt.close(); 
		conn.close(); 


	}

}

