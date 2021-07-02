package Examtable04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(4) :JDBC 라이브러리를 불러와 자료 읽어오기
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		 //(1)JDBC 라이브러리를 불러온다.
														 //(2)MYSQL에 연결한다
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement(); 		 //(3)MYSQL을 실행한다.
	     												 //※ResultSet : 쿼리문이 실행되어 결과를 반환한 데이터를 관리하는 클래스
	     //(4) select~ 로 실행되는 결과들을 가져오기	 //※ResultSet은 executeQuery()메소드, Statement는 execute() 메소드
	     ResultSet rset = stmt.executeQuery("select * from examtable"); 
	     System.out.printf("  이름 학  번  국어  영어  수학\n");
	     while (rset.next()) { 
	          											//결과물 처리 (※MYSQL은 0번을 만들지 않음)
	    	 											//getString(), getInt() 메소드를 이용해 결과 가져오기
	    	 System.out.printf("%4s %6d %4d %5d %5d\n",rset.getString(2),rset.getInt(1),
	    			 rset.getInt(3),rset.getInt(4),rset.getInt(5));
	      }
	     
	     rset.close();
	     stmt.close(); 
	     conn.close(); 

		
	}

}
