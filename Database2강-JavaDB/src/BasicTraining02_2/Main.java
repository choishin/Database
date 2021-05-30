package BasicTraining02_2;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * BasicTraining2-(2) :성적집계표, 데이터 넣기
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  			//(1)JDBC 라이브러리를 불러온다.
																//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.147.18:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 				//(3)MYSQL을 실행한다.
		
		/*입력할 데이터를 생성*/
		int k40_iPerson = 1000;
		for(int k40_i =1; k40_i<=k40_iPerson; k40_i++) {
			String k40_name = String.format("학생%02d", k40_i); //학생이름
			int k40_kor = (int)(Math.random()*100);				//국어
			int k40_eng = (int)(Math.random()*100);				//영어
			int k40_mat = (int)(Math.random()*100);				//수학
			
																//(4)MYSQL내에서 실행할 명령문 입력
			String QueryTxt;							
			QueryTxt = String.format("insert into scoreTable("+
					"number,"+
					"student_name,"+
					"kor,"+
					"eng,"+	
					"mat,"+
					"sum,"+
					"avg)"+
					"values(" 
					+ " %s, "
					+ "'%s',%s,%s,%s,%s,%s);",
					k40_i,
					k40_name,k40_kor,k40_eng,k40_mat,k40_kor+k40_eng+k40_mat,(k40_kor+k40_eng+k40_mat)/3
					);
			stmt.execute(QueryTxt);
			System.out.printf("%d번째 항목 Insert OK [%s]\n",k40_i,QueryTxt);
		}

		stmt.close(); 
		conn.close(); 


	}

}

