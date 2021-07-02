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
 * freewifi(2) : 무료와이파이정보, 테이블에 데이터 넣기 (실습1 현재꺼 그대로 해보기)
 * 														(실습2 자료를 넣다 보면 에러가 나는 줄이 있다. 이에 어떻게 대처했는지 제출
 * 														(즉 원 자료를 수정하든지, 테이블 필드길이를 조정하든지) 

 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");		 //(1)JDBC 라이브러리를 불러온다.
														 //(2)MYSQL에 연결한다
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.98:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 		 //(3)MYSQL을 실행한다.
		
		/*와이파이 정보는 BufferedReader을 이용해 파일을 읽어와서 입력해 줄 것임*/
		File f = new File("C:\\Users\\최신\\Desktop\\Data\\12_04_07_E_무료와이파이정보(공백수정).txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		/*예외처리 : 만일 아무것도 없는 파일이라면*/
		String readtxt;
		if((readtxt=br.readLine())==null) {
			System.out.printf("빈 파일입니다\n");
			return;
		}
		/*필드명 읽어오기*/
		String[] field_name = readtxt.split("\t");
		/*읽어 온 파일 탭별로 쪼개기*/
		int LineCnt=0;
		while((readtxt=br.readLine())!=null) {
			String[]field = readtxt.split("\t");
			String QueryTxt;
			
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
					field[6],field[7],field[8],field[9],field[10],
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

