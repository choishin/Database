package freewifi01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * freewifi(1) : 무료와이파이 정보, 데이터베이스에 넣기 (테이블 만들기)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		//(1)JDBC 라이브러리를 불러온다.
														//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL을 실행한다.
		//(4)MYSQL내에서 실행할 명령문 입력
		stmt.execute("create table freewifi("+
				"number int not null,"+					//※이름에 index, order, name 등을 넣으면 오류가 남. (예약어인듯함) 
				"inst_place varchar(50),"+ 				//설치장소명(1)
				"inst_place_detail varchar(100),"+		//설치장소상세(2)
				"inst_city varchar(50),"+			 	//설치시도명(3)
				"inst_country varchar(50),"+			//설치시군구명(4)
				"inst_place_flag varchar(50),"+			//설치시설구분(5)
				"service_provider varchar(50),"+		//서비스제공사명(6)
				"wifi_ssid varchar(50),"+				//와이파이SSID(7)
				"inst_date varchar(50),"+				//설치연월(8)
				"place_addr_road varchar(500),"+		//소재지도로명주소(9)
				"place_addr_land varchar(500),"+		//소재지지번주소(10)
				"manage_office varchar(50),"+			//관리기관명(11)
				"manage_office_phone varchar(50),"+		//관리기관전화번호(12)
				"latitude double,"+						//위도(13)
				"longtitude double,"+					//경도(14)
				"write_date date);");					//데이터기준일자(15)			

//		stmt.execute("alter table freewifi add(constraint pk_composite primary key(number,inst_place)");
		stmt.close(); 
		conn.close(); 


	}

}
