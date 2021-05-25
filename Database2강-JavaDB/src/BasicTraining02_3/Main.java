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

	static int k40_page_count;							/*������ ���� �Է����� ����*/

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  	//(1)JDBC ���̺귯���� �ҷ��´�.
														//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL�� �����Ѵ�.

		/*������ ó���� ���� ����*/
		int iPerson = 1000;								//iPerson : 1000���� ����
		InputData inData = new InputData(iPerson);		//�����͸� ó������ InputDataŬ������ �ҷ�����
		int lineCount = 0; 								//lineCount : ó�� Ƚ���� ������ ���� ����, �ʱ�ȭ
		inData.printHeader();							//������	
		/*������ ���� ������ �о����*/
		String querytxt = String.format("select * from scoreTable;");
		ResultSet rset = stmt.executeQuery(querytxt);

		while(rset.next()) {
			System.out.printf("%04d %6s %4s %4d %4d %5d %4d\n",
					rset.getInt(1), rset.getString(2), rset.getString(3), 
					rset.getInt(4), rset.getInt(5),rset.getInt(6),rset.getInt(7)); 
			inData.setData(rset.getInt(1),rset.getString(2),rset.getInt(3),rset.getInt(4),
					rset.getInt(5),rset.getInt(6),rset.getInt(7));
			
			lineCount++; 								 //<-������ ������ְ� 1�� lineCount�� �ø�
														 //���� 30�� �����ߴٸ�
			if (lineCount % 30 == 0 && lineCount < (iPerson - 1)) { 
				k40_page_count++;						 //<-(1)page_count�� �ø���
				inData.print_mid_Result(lineCount); 	 //<-(2)print_mid_Result�� ���� ���� ���� ����
				inData.printResult(lineCount); 			 //<-(3)printResult�� �������� ���� ����
				System.out.println();				   	 //<-���� ���� ��
				inData.printHeader();					 //<-��� ����� �ٽ� �ݺ�

			}

		}

		rset.close();

		inData.print_mid_Result(lineCount - 1);
		inData.printResult(lineCount - 1);


		stmt.close(); 
		conn.close(); 




	}



}

