package Stock01_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Training : 일자별 주가 데이터정보로 실습, 테이블 만들기
 * (실습1 table생성,지움 (단 [단축코드, 일자, 시가,고가,저가,종가, 거래량, 거래대금] 만으로 테이블 생성)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC 라이브러리를 불러온다.
													//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 	//(3)MYSQL을 실행한다.
													//(4)MYSQL내에서 실행할 명령문 입력
		/*(1)삼성전자 자료만 넣을 테이블*/
		//stmt.execute("create table stockInfo_samsung("
		
		/*(2)20150112 자료만 넣을 테이블*/
		//stmt.execute("create table stockInfo_20150112("
		
		/*(3)일자별 데이터를 전부 넣을 테이블*/
		stmt.execute("create table stockInfo_dailyPrice("
				+ "serialCode varchar(50) not null,"	//단축코드
				+ "date varchar(50),"					//일자
				+ "currentPrice int,"					//시가
				+ "highPrice int,"						//고가
				+ "lowPrice int,"						//저가
				+ "closingPrice int,"					//종가
				+ "turnover int,"						//거래량
				+ "dealPrice bigint,"					//거래대금
				+ "constraint pk_composite primary key(serialCode,date)"
				+ ");");				
		
	  //stmt.execute("alter table stockInfo add(constraint pk_composite primary key(serialCode,date);");
		stmt.close(); 
		conn.close(); 


	}

}
