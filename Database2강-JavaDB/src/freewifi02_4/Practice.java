package freewifi02_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Practice {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
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
			String[]field = readtxt.split("\t");
			String QueryTxt;
			String field8Convert="";
			String MM ="";
			String dd ="";
			String inst_date_MM = field[8].substring(3,6);
			String inst_date_dd = field[8].substring(0,2);
			
//			System.out.println(inst_date_MM);
//			System.out.println(inst_date_dd);

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
			dd = inst_date_dd;
			field8Convert=MM+"-"+dd;
			System.out.println(LineCnt +":"+field8Convert);
			LineCnt++;

		}
	}

}
