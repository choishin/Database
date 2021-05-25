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
 * freewifi(2) : 무료와이파이정보, 테이블에 데이터 넣기 (실습4 테이블의 설치년월을  Date형태의 필드로 고치고 데이터 정제후 디비 업로드)

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 
		
		File f = new File("C:\\Users\\최신\\Desktop\\Data\\12_04_07_E_무료와이파이정보(공백수정).txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("빈 파일입니다\n");
			return;
		}
		String[] field_name = readtxt.split("\t");
		
		int LineCnt=0;
		while((readtxt=br.readLine())!=null) {
			/*8번째 필드의 값을 MM-dd 형태로 바꿔줄 것임*/
			String[]field = readtxt.split("\t");
			String QueryTxt;
			String field8Convert="";
			String MM ="";
			String dd ="";
			/*8번째 필드값을 substring으로 자른 후 */
			String inst_date_MM = field[8].substring(3,6);
			String inst_date_dd = field[8].substring(0,2);
			/*그 값이 조건문과 같다면 숫자로 바꿔주기 */
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
			/*substring으로 자른 날짜 가지고 와서 MM-dd 형태로 만들기*/
			dd = inst_date_dd;
			field8Convert=MM+"-"+dd;
			/*쿼리로 데이터 입력*/
			QueryTxt = String.format("insert into freewifi("
					+ "number,"
					+ "inst_place," 		//설치장소명(1)
					+ "inst_place_detail," 	//설치장소상세(2)
					+ "inst_city,"			//설치시도명(3)
					+ "inst_country,"		//설치시군구명(4)
					+ "inst_place_flag,"	//설치시설구분(5)
					+ "service_provider,"	//서비스제공사명(6)
					+ "wifi_ssid,"			//와이파이SSID(7)
					+ "inst_date,"			//설치연월(8)
					+ "place_addr_road,"	//소재지도로명주소(9)
					+ "place_addr_land,"	//소재지지번주소(10)
					+ "manage_office,"		//관리기관명(11)
					+ "manage_office_phone,"//관리기관전화번호(12)
					+ "latitude,"			//위도(13)	
					+ "longtitude,"			//경도(14)
					+ "write_date)"+		//데이터기준일자(15)
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
			System.out.printf("%d번째 항목 Insert OK [%s]\n",LineCnt,QueryTxt);
			LineCnt++;
		}
		br.close();

		stmt.close(); 
		conn.close(); 


	}

}

