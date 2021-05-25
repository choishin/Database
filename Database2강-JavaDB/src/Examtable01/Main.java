package Examtable01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(1) : JDBC를 불러와서 MYSQL에 테이블 만들기 
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		//Class.forName : JDBC 라이브러리를 불러옴 
														//Connction : 외부에서 불러온 MYSQL에 연결할 경로와 아이디, 비밀번호를 입력
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement(); 		//Statement : SQL 구문을 실행하는 역할
	     
	     stmt.execute("create table examtable2("+ 		//stmt.execute(); -> MYSQL 내에서 실행할 명령문
	    		 "name varchar(20),"+
	    		 "studentid int not null primary key,"+
	    		 "kor int,"+
	    		 "eng int,"+
	    		 "mat int)"
	    		 );
	   
	     stmt.close(); //실행객체를 닫기
	     conn.close(); //연결객체를 닫기

		
	}

}
