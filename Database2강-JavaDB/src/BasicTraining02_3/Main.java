package BasicTraining02_3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * 
 */
public class Main {

	static int k40_page_count;							/*페이지 수를 입력해줄 변수*/

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC 라이브러리를 불러온다.
														//(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL을 실행한다.

		/*페이지 처리를 해줄 것임*/
		int iPerson = 1000;								//iPerson : 1000명을 선언
		InputData inData = new InputData(iPerson);		//데이터를 처리해줄 InputData클래스를 불러오기
		int lineCount = 0; 								//lineCount : 처리 횟수를 세어줄 변수 선언, 초기화
		inData.printHeader();							//헤더출력	
		/*쿼리문 실행 데이터 읽어오기*/
		String querytxt = String.format("select * from scoreTable;");
		ResultSet rset = stmt.executeQuery(querytxt);

		while(rset.next()) {
			System.out.printf("%04d %6s %4s %4d %4d %5d %4d\n",
					rset.getInt(1), rset.getString(2), rset.getString(3), 
					rset.getInt(4), rset.getInt(5),rset.getInt(6),rset.getInt(7)); 
			inData.setData(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),
					rset.getInt(5),rset.getInt(6),rset.getInt(7));
			
			lineCount++; 								 //<-정보를 출력해주고 1씩 lineCount를 올림
														 //만일 30번 수행했다면
			if (lineCount % 30 == 0 && lineCount < (iPerson - 1)) { 
				k40_page_count++;						 //<-(1)page_count를 올리고
				inData.print_mid_Result(lineCount); 	 //<-(2)print_mid_Result로 현재 집계 숫자 전달
				inData.printResult(lineCount); 			 //<-(3)printResult로 누적집계 숫자 전달
				System.out.println();				   	 //<-한줄 개행 후
				inData.printHeader();					 //<-헤더 출력후 다시 반복

			}

		}

		rset.close();

		inData.print_mid_Result(lineCount - 1);
		inData.printResult(lineCount - 1);


		stmt.close(); 
		conn.close(); 




	}



}

