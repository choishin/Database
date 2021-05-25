package BasicTraining02_3;

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

	static Main k40_main = new Main();

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
		System.out.printf("%s %20s %s\n","PAGE :"+ k40_main.k40_page_count,"출력일자 : ",sdf.format(k40_date));
		System.out.println("============================================");
		System.out.printf("%2.2s %3s %4s %2s %2s %3.2s %2.2s\n","번호","이름","국어","영어","수학","총점","평균");
		System.out.println("============================================");
	}

	void print_mid_Result(int k40_lineCount) { //<-현재페이지 집계를 위한 메소드
		int k40_mid_total_kor_sum =0;
		int k40_mid_total_eng_sum =0;
		int k40_mid_total_mat_sum =0;
		int k40_mid_total_sum =0;
		int k40_mid_total_avg =0;
		if(k40_lineCount%30 == 0) { //lineCount는 각페이지의 마지막 순법 
			for (int i=k40_lineCount-29; i<k40_lineCount+1; i++) { //29를 뺀 순번부터, 현재 lineCount순번까지
				k40_mid_total_kor_sum = k40_mid_total_kor_sum + k40_kors[i]; //국어 합계
				k40_mid_total_eng_sum = k40_mid_total_eng_sum + k40_engs[i]; //영어 합계
				k40_mid_total_mat_sum = k40_mid_total_mat_sum + k40_mats[i]; //수학 합계
				k40_mid_total_sum = k40_mid_total_sum + k40_sums[i]; //총 합계
			
		}
		k40_mid_total_avg = k40_mid_total_sum/3;//평균 값
		System.out.println("============================================");
		System.out.println("현재페이지");
		System.out.printf("합계 %7s %4d %4d %4d %5d %4d\n"," ", //합계를 출력
				k40_mid_total_kor_sum,k40_mid_total_eng_sum,k40_mid_total_mat_sum,k40_mid_total_sum,k40_mid_total_avg);
		System.out.printf("평균 %7s %4d %4d %4d %5d %4d\n", " ", //평균을 출력
				k40_mid_total_kor_sum/30,k40_mid_total_eng_sum/30,k40_mid_total_mat_sum/30,k40_mid_total_sum/30,k40_mid_total_avg/30);	
		}
		else { // 마지막 데이터가 30개보다 적은 데이터라면
				//마지막 데이터 순번에서 이전페이지에*30을 곱한 수(즉, 누적숫자)를 빼준 수부터, 마지막 순번까지 
			for (int i=k40_lineCount-((k40_lineCount-(k40_main.k40_page_count-1)*30))+1; i<k40_lineCount+1; i++) {
				k40_mid_total_kor_sum = k40_mid_total_kor_sum + k40_kors[i]; //국어 합계
				k40_mid_total_eng_sum = k40_mid_total_eng_sum + k40_engs[i]; //영어 합계
				k40_mid_total_mat_sum = k40_mid_total_mat_sum + k40_mats[i]; //수학 합계
				k40_mid_total_sum = k40_mid_total_sum + k40_sums[i]; //총 합계
			
		}
		k40_mid_total_avg = k40_mid_total_sum/3; //평균 값
		System.out.println("============================================");
		System.out.println("현재페이지");
		System.out.printf("합계 %6s %4d %4d %4d %5d %4d\n"," ",
				k40_mid_total_kor_sum,k40_mid_total_eng_sum,k40_mid_total_mat_sum,k40_mid_total_sum,k40_mid_total_avg);
		System.out.printf("평균 %6s %4d %4d %4d %5d %4d\n", " ",
				//마지막 숫자에서 누적개수를 뺀것 = 남은 데이터 개수 
				k40_mid_total_kor_sum/((k40_lineCount-(k40_main.k40_page_count-1)*30)),//국어합계/남은 데이터 개수
				k40_mid_total_eng_sum/((k40_lineCount-(k40_main.k40_page_count-1)*30)),//영어합계/남은 데이터 개수
				k40_mid_total_mat_sum/((k40_lineCount-(k40_main.k40_page_count-1)*30)),//수학합계/남은 데이터 개수
				k40_mid_total_sum/((k40_lineCount-(k40_main.k40_page_count-1)*30)), //총 합계/남은 데이터 개수
				k40_mid_total_avg/((k40_lineCount-(k40_main.k40_page_count-1)*30)));//총 평균 /남은 데이터 개수	
		}
	}
	void printResult(int k40_lineCount) {
		k40_total_kor_sum = 0;
		k40_total_eng_sum = 0;
		k40_total_mat_sum = 0;
		k40_total_sum =0;
		k40_total_avg = 0;
		for(int k40_i=1; k40_i<k40_lineCount+1; k40_i++) {
			k40_total_kor_sum = k40_total_kor_sum +k40_kors[k40_i];
			k40_total_eng_sum = k40_total_eng_sum +k40_engs[k40_i];
			k40_total_mat_sum = k40_total_mat_sum +k40_mats[k40_i];
			k40_total_sum = k40_total_sum + k40_sums[k40_i];

		}
		k40_total_avg = k40_total_sum/3;
		System.out.println("============================================");
		System.out.println("누적페이지");
		System.out.printf("합계 %5s %5d %5d %5d %5d %5d\n"," ",
				k40_total_kor_sum,k40_total_eng_sum,k40_total_mat_sum,k40_total_sum,k40_total_avg);
		System.out.printf("평균 %6s %4d %5d %5d %5d %5d\n", " ",
				k40_total_kor_sum/(k40_lineCount),k40_total_eng_sum/(k40_lineCount),k40_total_mat_sum/(k40_lineCount),
				k40_total_sum/(k40_lineCount),k40_total_avg/(k40_lineCount));		
	}
}
