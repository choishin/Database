package Examtable02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(2) : JDBC 라이브러리를 연결해서 MYSQL안에 있는 table을 지우기
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//Class.forName : JDBC 라이브러리를 불러옴 
														//Connction : 외부에서 불러온 MYSQL에 연결할 경로와 아이디, 비밀번호를 입력
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement(); 		//Statement : SQL 구문을 실행하는 역할
	     
	     stmt.execute("delete from examtable2;"); 		//<-명령어입력 (table의 내용이 전부 지워짐)
	   
	     stmt.close(); 
	     conn.close(); 

		
	}

}
