package freewifi01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * freewifi(1) : ����������� ����, �����ͺ��̽��� �ֱ� (���̺� �����)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		//(1)JDBC ���̺귯���� �ҷ��´�.
														//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		//(3)MYSQL�� �����Ѵ�.
		//(4)MYSQL������ ������ ��ɹ� �Է�
		stmt.execute("create table freewifi("+
				"number int not null,"+					//���̸��� index, order, name ���� ������ ������ ��. (������ε���) 
				"inst_place varchar(50),"+ 				//��ġ��Ҹ�(1)
				"inst_place_detail varchar(100),"+		//��ġ��һ�(2)
				"inst_city varchar(50),"+			 	//��ġ�õ���(3)
				"inst_country varchar(50),"+			//��ġ�ñ�����(4)
				"inst_place_flag varchar(50),"+			//��ġ�ü�����(5)
				"service_provider varchar(50),"+		//�����������(6)
				"wifi_ssid varchar(50),"+				//��������SSID(7)
				"inst_date varchar(50),"+				//��ġ����(8)
				"place_addr_road varchar(500),"+		//���������θ��ּ�(9)
				"place_addr_land varchar(500),"+		//�����������ּ�(10)
				"manage_office varchar(50),"+			//���������(11)
				"manage_office_phone varchar(50),"+		//���������ȭ��ȣ(12)
				"latitude double,"+						//����(13)
				"longtitude double,"+					//�浵(14)
				"write_date date);");					//�����ͱ�������(15)			

//		stmt.execute("alter table freewifi add(constraint pk_composite primary key(number,inst_place)");
		stmt.close(); 
		conn.close(); 


	}

}
