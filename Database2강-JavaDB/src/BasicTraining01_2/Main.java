package BasicTraining01_2;

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
 * BasicTraining(2) :전국 주차장 정보, 테이블에 데이터 넣기 (실습2 파일을 읽어서 table에 집어넣기, P-KEY 잡아주셔요)
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 
		
		File f = new File("C:\\Users\\최신\\Desktop\\Data\\한국교통안전공단_전국공영주차장정보_20191224.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("빈 파일입니다\n");
			return;
		}
		String[] field_name = readtxt.split("\t");
		
		int LineCnt=0;
		while((readtxt=br.readLine())!=null) {
			String[]field = readtxt.split("\t");
			String QueryTxt;
			QueryTxt = String.format("insert into parkinglotInfo("+
					"number,"+
					"parkinglot_name,"+
					"longitude,"+
					"latitude,"+
					"classification,"+
					"type,"+
					"address_land,"+
					"address_load,"+
					"capacity,"+
					"weekday_info,"+
					"weekday_open,"+
					"weekday_off,"+
					"weekend_open,"+
					"weekedn_off,"+
					"holiday_open,"+
					"holiday_off,"+
					"pay_info,"+		
					"admin_office,"+
					"city,"+
					"city_sub,"+
					"location_x,"+
					"location_y,"+
					"localnumber,"+
					"contacts,"+	
					"date)"+
					"values(" 
					+ " %s, "
					+ "'%s', %s , %s ,'%s','%s',"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s');",
					field[0],
					field[1],field[2],field[3],field[4],field[5],
					field[6],field[7],field[8],field[9],field[10],
					field[11],field[12],field[13],field[14],field[15],
					field[16],field[17],field[18],field[19],field[20],
					field[21],field[22],field[23],field[24]
					);
			stmt.execute(QueryTxt);
			System.out.printf("%d번째 항목 Insert OK [%s]\n",LineCnt,QueryTxt);
			LineCnt++;
		}
		br.close();

		stmt.close(); 
		conn.close(); 


	}

}

