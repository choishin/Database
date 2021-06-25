package freewifi02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * freewifi(2) : ���������������, ���̺� ������ �ֱ� (�ǽ�1 ���粨 �״�� �غ���)
 * 														(�ǽ�2 �ڷḦ �ִ� ���� ������ ���� ���� �ִ�. �̿� ��� ��ó�ߴ��� ����
 * 														(�� �� �ڷḦ �����ϵ���, ���̺� �ʵ���̸� �����ϵ���) 

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");		 //(1)JDBC ���̺귯���� �ҷ��´�.
														 //(2)MYSQL�� �����Ѵ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		 //(3)MYSQL�� �����Ѵ�.
		
		/*�������� ������ BufferedReader�� �̿��� ������ �о�ͼ� �Է��� �� ����*/
		File f = new File("C:\\Users\\�ֽ�\\Desktop\\Data\\12_04_07_E_���������������(�������).txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		/*����ó�� : ���� �ƹ��͵� ���� �����̶��*/
		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("�� �����Դϴ�\n");
			return;
		}
		/*�ʵ�� �о����*/
		String[] field_name = readtxt.split("\t");
		/*�о� �� ���� �Ǻ��� �ɰ���*/
		int LineCnt=0;
		while((readtxt=br.readLine())!=null) {
			String[]field = readtxt.split("\t");
			String QueryTxt;
			
			QueryTxt = String.format("insert into freewifi("
					+ "number,"
					+ "inst_place," 		//��ġ��Ҹ�(1)
					+ "inst_place_detail," 	//��ġ��һ�(2)
					+ "inst_city,"			//��ġ�õ���(3)
					+ "inst_country,"		//��ġ�ñ�����(4)
					+ "inst_place_flag,"	//��ġ�ü�����(5)
					+ "service_provider,"	//�����������(6)
					+ "wifi_ssid,"			//��������SSID(7)
					+ "inst_date,"			//��ġ����(8)
					+ "place_addr_road,"	//���������θ��ּ�(9)
					+ "place_addr_land,"	//�����������ּ�(10)
					+ "manage_office,"		//���������(11)
					+ "manage_office_phone,"//���������ȭ��ȣ(12)
					+ "latitude,"			//����(13)	
					+ "longtitude,"			//�浵(14)
					+ "write_date)"+		//�����ͱ�������(15)
					"values (" 
					+"%s,"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s',%s,%s,'%s');",
					field[0],
					field[1],field[2],field[3],field[4],field[5],
					field[6],field[7],field[8],field[9],field[10],
					field[11],field[12],field[13],field[14],field[15]
					);
			stmt.execute(QueryTxt);
			System.out.printf("%d��° �׸� Insert OK [%s]\n",LineCnt,QueryTxt);
			LineCnt++;
		}
		br.close();
		stmt.close(); 
		conn.close(); 


	}

}

