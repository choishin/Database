package BasicTraining02_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * 
 */
public class InputData {
	static int k40_size;
	static int k40_i;
	public static String[] k40_names;
	public static int[] k40_kors;
	public static int[] k40_engs;
	public static int[] k40_mats;
	public static int[] k40_sums;
	public static int[] k40_avgs;
	public static int k40_total_kor_sum;
	public static int k40_total_eng_sum;
	public static int k40_total_mat_sum;
	public static int k40_total_sum;
	public static int k40_total_avg;

	static Main main = new Main();


	InputData(int iPerson) {
		k40_size = iPerson;
		k40_names = new String[k40_size];
		k40_kors = new int[k40_size];
		k40_engs= new int[k40_size];
		k40_mats = new int[k40_size];
		k40_sums = new int[k40_size];
		k40_avgs = new int[k40_size];

	}

	void setData(int k40_i, String k40_name, int k40_kor, int k40_eng, int k40_mat, int k40_sum, int k40_avg) {

		k40_names[k40_i] = k40_name;
		k40_kors[k40_i] = k40_kor;
		k40_engs[k40_i] = k40_eng;
		k40_mats[k40_i] = k40_mat;
		k40_sums[k40_i] = k40_sum;
		k40_avgs[k40_i] = k40_avg;

	}

	void printHeader() {

		Date k40_date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		System.out.printf("%20s\n","성적집계표");
		System.out.printf("%s %12s %s\n","PAGE :"+ main.page_count,"출력일자 : ",sdf.format(k40_date));
		System.out.println("============================================");
		System.out.printf("%2.2s %3s %4s %2s %2s %3.2s %2.2s\n","번호","이름","국어","영어","수학","총점","평균");
		System.out.println("============================================");
	}

	void print_mid_Result(int k40_lineCount) throws ClassNotFoundException, SQLException { //<-현재페이지 집계를 위한 메소드

		int start =((main.page_count-2)*main.result_amount)+1;
		int end = k40_lineCount;

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.147.18:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 

		if ( k40_lineCount%30 == 0 ) {
			//System.out.println(k40_lineCount);
			//System.out.println(start);
			//System.out.println(end);

			String querytxt = String.format("select sum(kor),sum(eng),sum(mat),sum(sum),sum(avg) from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			ResultSet rset = stmt.executeQuery(querytxt);

			while(rset.next()) {
				System.out.println("============================================");
				System.out.println("현재페이지");

				System.out.printf("합계 %15d %4d %4d %4d %5d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5)); 
			}

			querytxt = String.format("select sum(kor)/"+main.result_amount+",sum(eng)/"+main.result_amount+","
					+ "sum(mat)/"+main.result_amount+",sum(sum)/"+main.result_amount+",sum(avg)/"+main.result_amount+" from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			rset = stmt.executeQuery(querytxt);
			while(rset.next()) {
				System.out.printf("평균 %15d %4d %4d %4d %5d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5));	
			}
		}

		else  {

			start = main.iPerson-(main.iPerson-((main.page_count-1)*main.result_amount))+1;
			end = k40_lineCount;

			String querytxt = String.format("select sum(kor),sum(eng),sum(mat),sum(sum),sum(avg) from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			ResultSet rset = stmt.executeQuery(querytxt);

			while(rset.next()) {
				System.out.println("============================================");
				System.out.println("현재페이지");

				System.out.printf("합계 %15d %4d %4d %4d %4d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5)); 
			}

			querytxt = String.format("select sum(kor)/"+(main.iPerson-((main.page_count-1)*main.result_amount))+",sum(eng)/"+(main.iPerson-((main.page_count-1)*main.result_amount))+","
					+ "sum(mat)/"+(main.iPerson-((main.page_count-1)*main.result_amount))+",sum(sum)/"+(main.iPerson-((main.page_count-1)*main.result_amount))+",sum(avg)/"+main.result_amount+" from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			rset = stmt.executeQuery(querytxt);
			while(rset.next()) {
				System.out.printf("평균 %15d %4d %4d %4d %4d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5));	
			}
		}

	}
	
	void printResult(int k40_lineCount) throws SQLException, ClassNotFoundException {
		int start =0;
		int end = k40_lineCount;

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.147.18:3306/kopoctc","root" , "kopoctc");  
		Statement stmt = conn.createStatement(); 

		if ( k40_lineCount%30 == 0 ) {
			System.out.println(k40_lineCount);
			System.out.println(start);
			System.out.println(end);

			String querytxt = String.format("select sum(kor),sum(eng),sum(mat),sum(sum),sum(avg) from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			ResultSet rset = stmt.executeQuery(querytxt);

			while(rset.next()) {
				System.out.println("============================================");
				System.out.println("누적페이지");

				System.out.printf("합계 %15d %4d %4d %4d %4d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5)); 
			}

			querytxt = String.format("select sum(kor)/"+k40_lineCount+",sum(eng)/"+k40_lineCount+","
					+ "sum(mat)/"+k40_lineCount+",sum(sum)/"+k40_lineCount+",sum(avg)/"+k40_lineCount+" from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			rset = stmt.executeQuery(querytxt);
			while(rset.next()) {
				System.out.printf("평균 %15d %5d %5d %5d %5d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5));	
			}
		}

		else  {

			start = 0;
			end = k40_lineCount;
			System.out.println(k40_lineCount);
			System.out.println(start);
			System.out.println(end);

			String querytxt = String.format("select sum(kor),sum(eng),sum(mat),sum(sum),sum(avg) from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			ResultSet rset = stmt.executeQuery(querytxt);

			while(rset.next()) {
				System.out.println("============================================");
				System.out.println("누적페이지");

				System.out.printf("합계 %15d %4d %4d %4d %5d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5)); 
			}

			querytxt = String.format("select sum(kor)/"+k40_lineCount+",sum(eng)/"+k40_lineCount+","
					+ "sum(mat)/"+k40_lineCount+",sum(sum)/"+k40_lineCount+",sum(avg)/"+k40_lineCount+" from scoreTable "
					+ "where number >="+start+" and number <="+end+";");
			rset = stmt.executeQuery(querytxt);
			while(rset.next()) {
				System.out.printf("평균 %15d %5d %5d %5d %5d \n",
						rset.getInt(1), rset.getInt(2), rset.getInt(3), 
						rset.getInt(4), rset.getInt(5));	
			}
		
		}
	}
}
