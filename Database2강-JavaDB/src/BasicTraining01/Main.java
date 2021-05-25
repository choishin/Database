package BasicTraining01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * BasicTraining(1) : 주차장 위치정보로 실습 (실습1 table생성,지움)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		 	//(1)JDBC 라이브러리를 불러온다.
														 	//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		 	//(3)MYSQL을 실행한다.
															//(4)MYSQL내에서 실행할 명령문 입력
		stmt.execute("create table parkinglotInfo("
				+ "number int not null primary key,"		//주차장관리번호(0)
				+ "parkinglot_name varchar(100),"			//주차장명(1)
				+ "longitude double,"						//경도(2)
				+ "latitude double,"						//위도(3)
				+ "classification varchar(100),"			//주차장구분(4)
				+ "type varchar(10),"						//주차장유형(5)
				+ "address_land varchar(200),"				//주차장지번주소(6)
				+ "address_load varchar(200),"				//주차장도로명주소(7)
				+ "capacity varchar(50),"					//주차구획수(8)
				+ "weekday_info varchar(500),"				//운영요일(9)
				+ "weekday_open varchar(500),"				//평일운영시작시각(10)
				+ "weekday_off varchar(500),"				//평일운영종료시각(11)
				+ "weekend_open varchar(500),"				//토요일운영시작시각(12)
				+ "weekedn_off varchar(500),"				//토요일운영종료시각(13)
				+ "holiday_open varchar(500),"				//공휴일운영시작시각(14)			
				+ "holiday_off varchar(500),"				//공휴일운영종료시각(15)
				+ "pay_info varchar(500),"					//요금정보(16)
				+ "admin_office varchar(100),"				//관리기관명(17)
				+ "city varchar(20),"						//지역구분(18)
				+ "city_sub varchar(20),"					//지역구분_sub(19)
				+ "location_x varchar(50),"					//지역중심좌표(X좌표)(20)
				+ "location_y varchar(50),"					//지역중심좌표(y좌표)(21)
				+ "localnumber varchar(50),"				//지역코드(22)			
				+ "contacts varchar(50),"					//연락처(23)
				+ "date varchar(100));");					//수정일자(24)
		
//		stmt.execute("alter table parkinglotInfo add(constraint pk_composite primary key(number,parkinglot_name)");
		stmt.close(); 
		conn.close(); 


	}

}
