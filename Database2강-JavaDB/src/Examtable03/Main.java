package Examtable03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * examtable(3) : JDBC라이브러리를 이용해 레코드를 집어넣기 
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		 Class.forName("com.mysql.cj.jdbc.Driver"); 	//(1)JDBC 라이브러리를 불러온다.
		 											 	//(2)MYSQL에 연결한다
	     Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
	     Statement stmt = conn.createStatement();  		//(3)MYSQL을 실행한다. 
	     
	     //(4)실행시킬 명령문 입력
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	     		+ "values('효민',209901,95,100,95);");
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	    		 + "values('보람',209902,95,95,95);");
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	    		 + "values('은정',209903,100,100,100);");
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	    		 + "values('지연',209904,100,95,90);");
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	    		 + "values('소연',209905,80,100,70);");
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	    		 + "values('큐리',209906,100,100,70);");
	     stmt.execute("insert into examtable2 (name,studentid,kor,eng,mat) "
	    		 + "values('화영',209907,70,70,70);");
	   
	     stmt.close(); 
	     conn.close(); 

		
	}

}
