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

	static int page_count;	
	static int result_amount=30;
	static int iPerson = 1000;	

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC ���̺귯���� �ҷ��´�.
														//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.147.18:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL�� �����Ѵ�.

		/*������ ó���� ���� ����*/
														
		InputData inData = new InputData(iPerson);		//�����͸� ó������ InputDataŬ������ �ҷ�����
		int lineCount = 0; 	
		page_count=1;
		inData.printHeader();						
		/*������ ���� ������ �о����*/
		String querytxt = String.format("select * from scoreTable;");
		ResultSet rset = stmt.executeQuery(querytxt);

		while(rset.next()) {
			lineCount++; 
			System.out.printf("%04d %6s %4s %4d %4d %5d %4d\n",
					rset.getInt(1), rset.getString(2), rset.getString(3), 
					rset.getInt(4), rset.getInt(5),rset.getInt(6),rset.getInt(7)); 
			
			if (lineCount % result_amount == 0 && lineCount < (iPerson - 1) ) { 
				page_count++;						
				inData.print_mid_Result(lineCount); 	 
				inData.printResult(lineCount); 			
				System.out.println();				   
				inData.printHeader();					 
			}
		}

		rset.close();

		inData.print_mid_Result(lineCount);
		inData.printResult(lineCount);


		stmt.close(); 
		conn.close(); 




	}



}

