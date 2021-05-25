package Stock01_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Training : ���ں� �ְ� ������������ �ǽ�, ���̺� �����
 * (�ǽ�1 table����,���� (�� [�����ڵ�, ����, �ð�,��,����,����, �ŷ���, �ŷ����] ������ ���̺� ����)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  //(1)JDBC ���̺귯���� �ҷ��´�.
													//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 	//(3)MYSQL�� �����Ѵ�.
													//(4)MYSQL������ ������ ��ɹ� �Է�
		/*(1)�Ｚ���� �ڷḸ ���� ���̺�*/
		//stmt.execute("create table stockInfo_samsung("
		
		/*(2)20150112 �ڷḸ ���� ���̺�*/
		//stmt.execute("create table stockInfo_20150112("
		
		/*(3)���ں� �����͸� ���� ���� ���̺�*/
		stmt.execute("create table stockInfo_dailyPrice("
				+ "serialCode varchar(50) not null,"	//�����ڵ�
				+ "date varchar(50),"					//����
				+ "currentPrice int,"					//�ð�
				+ "highPrice int,"						//��
				+ "lowPrice int,"						//����
				+ "closingPrice int,"					//����
				+ "turnover int,"						//�ŷ���
				+ "dealPrice bigint,"					//�ŷ����
				+ "constraint pk_composite primary key(serialCode,date)"
				+ ");");				
		
	  //stmt.execute("alter table stockInfo add(constraint pk_composite primary key(serialCode,date);");
		stmt.close(); 
		conn.close(); 


	}

}
