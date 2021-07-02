package BasicTraining02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * BasicTraining2-(1) : 성적집계표로 실습
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC 라이브러리를 불러온다.
													//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.147.18:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 	//(3)MYSQL을 실행한다.
													//(4)MYSQL내에서 실행할 명령문 입력
		stmt.execute("create table scoreTable("
				+ "number int not null primary key,"		
				+ "student_name varchar(20),"		
				+ "kor int,"						
				+ "eng int,"				
				+ "mat int,"
				+ "sum int,"
				+ "avg int);"
				);

		stmt.close(); 
		conn.close(); 


	}

}
