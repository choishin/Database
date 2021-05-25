package DBtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBtest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");  //외부 라이브러리를 부를때 쓰는 명령어
		//외부에서 불러온 MYSQL에 연결할 경로와 아이디, 비밀번호를 입력
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	   //SQL 구문을 실행하는 역할
	     Statement stmt = conn.createStatement(); 
	     //쿼리문이 실행되어 결과로 반환된 데이터들을 관리하는 클래스
	     ResultSet rset = stmt.executeQuery("select * from examtable"); 
	     System.out.println("===========================================");
	     while (rset.next()) { 
	          //결과물 처리 (※MYSQL은 0번을 만들지 않음)
	    	 System.out.println("이름 :"+rset.getString(1)); 	//가져온 쿼리문의 1번 필드를 가져온다
	    	 System.out.println("학번 :"+rset.getString(2));	//가져온 쿼리문의 2번 필드를 가져온다
	    	 System.out.println("국어 :"+rset.getString(3));	//가져온 쿼리문의 3번 필드를 가져온다
	    	 System.out.println("영어 :"+rset.getString(4));	//가져온 쿼리문의 4번 필드를 가져온다
	    	 System.out.println("수학 :"+rset.getString(5));	//가져온 쿼리문의 5번 필드를 가져온다
	    	 System.out.println("===========================================");
	      } 
	     rset.close(); 
	     stmt.close(); 
	     conn.close(); 


	}

}
