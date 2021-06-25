package freewifi02_4;

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
 * freewifi(2) : ���������������, ���̺� ������ �ֱ� (�ǽ�4 ���̺��� ��ġ�����  Date������ �ʵ�� ��ġ�� ������ ������ ��� ���ε�)

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 
		
		File f = new File("C:\\Users\\�ֽ�\\Desktop\\Data\\12_04_07_E_���������������(�������).txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("�� �����Դϴ�\n");
			return;
		}
		String[] field_name = readtxt.split("\t");
		
		int LineCnt=0;
		while((readtxt=br.readLine())!=null) {
			/*8��° �ʵ��� ���� MM-dd ���·� �ٲ��� ����*/
			String[]field = readtxt.split("\t");
			String QueryTxt;
			String field8Convert="";
			String MM ="";
			String dd ="";
			/*8��° �ʵ尪�� substring���� �ڸ� �� */
			String inst_date_MM = field[8].substring(3,6);
			String inst_date_dd = field[8].substring(0,2);
			/*�� ���� ���ǹ��� ���ٸ� ���ڷ� �ٲ��ֱ� */
			if(inst_date_MM.contains("Jan")) {
				MM="01";
			}else if (inst_date_MM.contains("Feb")) {
				MM="02";
			}else if (inst_date_MM.contains("Mar")) {
				MM="03";
			}else if (inst_date_MM.contains("Apr")) {
				MM="04";
			}else if (inst_date_MM.contains("May")) {
				MM="05";
			}else if (inst_date_MM.contains("Jun")) {
				MM="06";
			}else if (inst_date_MM.contains("Jul")) {
				MM="07";
			}else if (inst_date_MM.contains("Aug")) {
				MM="08";
			}else if (inst_date_MM.contains("Sep")) {
				MM="09";
			}else if (inst_date_MM.contains("Oct")) {
				MM="10";
			}else if (inst_date_MM.contains("Nov")) {
				MM="11";
			}else if (inst_date_MM.contains("Dec")) {
				MM="12";
			}else {
				MM="00";
			}
			/*substring���� �ڸ� ��¥ ������ �ͼ� MM-dd ���·� �����*/
			dd = inst_date_dd;
			field8Convert=MM+"-"+dd;
			/*������ ������ �Է�*/
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
					field[6],field[7],field8Convert,field[9],field[10],
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

