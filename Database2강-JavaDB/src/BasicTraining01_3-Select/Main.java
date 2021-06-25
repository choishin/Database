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
 * Basic Training(1) : 전국 주차장 정보, 데이터 읽어오기 (실습3 최단거리 등등 조회하는 실습실습)

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC 라이브러리를 불러온다.
														//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL을 실행한다.
	
		/*현재 위치를 나타내는 위도,경도*/
		double lat= 37.3860521; 
		double lng = 127.1214038;

		String QueryTxt;
		/* 나와 가까운 곳 찾기*/
		QueryTxt = String.format("select * from parkinglotInfo where "
				+ "SQRT(POWER(latitude - %f,2) + POWER (longitude - %f,2)) = "
				+ "(select MIN(SQRT(POWER(latitude - %f,2) + POWER(longitude - %f,2))) from parkinglotInfo);",lat,lng,lat,lng);
		
		/*전부보기*/
		//QueryTxt = "select * from parkinglotInfo";
		/*주차장이 서울에 있으면서, 무료인 곳 찾기*/
		//QueryTxt = "select * from freewifi where classification='무료'and city='서울특별시'";	

		ResultSet rset = stmt.executeQuery(QueryTxt);
		int iCnt=0;
		while(rset.next()) {
			System.out.printf("*(%d)*****************************************\n",iCnt++);
			System.out.printf("주차장관리번호		:%s\n",rset.getString(1));
			System.out.printf("주차장명		:%s\n",rset.getString(2));
			System.out.printf("경도			:%s\n",rset.getString(3));
			System.out.printf("위도			:%s\n",rset.getString(4));
			System.out.printf("주차장구분		:%s\n",rset.getString(5));
			System.out.printf("주차장유형		:%s\n",rset.getString(6));
			System.out.printf("주차장지번주소		:%s\n",rset.getString(7));
			System.out.printf("주차장도로명주소	:%s\n",rset.getString(8));
			System.out.printf("주차구획수		:%s\n",rset.getString(9));
			System.out.printf("운영요일		:%s\n",rset.getString(10));
			System.out.printf("평일운영시작시각	:%s\n",rset.getString(11));
			System.out.printf("평일운영종료시각	:%s\n",rset.getString(12));
			System.out.printf("토요일운영시작시각	:%s\n",rset.getString(13));
			System.out.printf("토요일운영종료시각	:%s\n",rset.getString(14));
			System.out.printf("공휴일운영시작시각	:%s\n",rset.getString(15));
			System.out.printf("공휴일운영종료시각	:%s\n",rset.getString(16));
			System.out.printf("요금정보		:%s\n",rset.getString(17));
			System.out.printf("관리기관명		:%s\n",rset.getString(18));
			System.out.printf("지역구분		:%s\n",rset.getString(19));
			System.out.printf("지역구분	시군구	:%s\n",rset.getString(20));
			System.out.printf("지역중심좌표(X좌표)	:%s\n",rset.getString(21));
			System.out.printf("지역중심좌표(y좌표)	:%s\n",rset.getString(22));
			System.out.printf("지역코드		:%s\n",rset.getString(23));
			System.out.printf("연락처			:%s\n",rset.getString(24));
			System.out.printf("수정일자		:%s\n",rset.getString(25));

		}

		rset.close();
		stmt.close(); 
		conn.close(); 


	}

}

