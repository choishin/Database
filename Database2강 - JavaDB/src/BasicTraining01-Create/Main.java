package BasicTraining01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * BasicTraining(1) : ������ ��ġ������ �ǽ� (�ǽ�1 table����,����)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver"); 		 	//(1)JDBC ���̺귯���� �ҷ��´�.
														 	//(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		 	//(3)MYSQL�� �����Ѵ�.
															//(4)MYSQL������ ������ ��ɹ� �Է�
		stmt.execute("create table parkinglotInfo("
				+ "number int not null primary key,"		//�����������ȣ(0)
				+ "parkinglot_name varchar(100),"			//�������(1)
				+ "longitude double,"						//�浵(2)
				+ "latitude double,"						//����(3)
				+ "classification varchar(100),"			//�����屸��(4)
				+ "type varchar(10),"						//����������(5)
				+ "address_land varchar(200),"				//�����������ּ�(6)
				+ "address_load varchar(200),"				//�����嵵�θ��ּ�(7)
				+ "capacity varchar(50),"					//������ȹ��(8)
				+ "weekday_info varchar(500),"				//�����(9)
				+ "weekday_open varchar(500),"				//���Ͽ���۽ð�(10)
				+ "weekday_off varchar(500),"				//���Ͽ����ð�(11)
				+ "weekend_open varchar(500),"				//����Ͽ���۽ð�(12)
				+ "weekedn_off varchar(500),"				//����Ͽ����ð�(13)
				+ "holiday_open varchar(500),"				//�����Ͽ���۽ð�(14)			
				+ "holiday_off varchar(500),"				//�����Ͽ����ð�(15)
				+ "pay_info varchar(500),"					//�������(16)
				+ "admin_office varchar(100),"				//���������(17)
				+ "city varchar(20),"						//��������(18)
				+ "city_sub varchar(20),"					//��������_sub(19)
				+ "location_x varchar(50),"					//�����߽���ǥ(X��ǥ)(20)
				+ "location_y varchar(50),"					//�����߽���ǥ(y��ǥ)(21)
				+ "localnumber varchar(50),"				//�����ڵ�(22)			
				+ "contacts varchar(50),"					//����ó(23)
				+ "date varchar(100));");					//��������(24)
		
//		stmt.execute("alter table parkinglotInfo add(constraint pk_composite primary key(number,parkinglot_name)");
		stmt.close(); 
		conn.close(); 


	}

}
