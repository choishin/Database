##examtable에 데이터 넣기
#call insert_examtable(1000);

##examtable에 넣은 데이터 보기
#select * from examtable;

##print_report 불러오기
#call print_roport(5,25);

#결과테이블1
#select student_id as 학번, student_name as 이름, kor as 국어, eng as 영어, mat as 수학, kor+eng+mat as 총점, (kor+eng+mat)/3 as 평균 
#from examtable limit 100,25; 

drop table currentpage_result;
create table currentpage_result (
kor int,
eng int,
mat int,
sum int,
avg double
);
drop table totalpage_result;
create table totalpage_result(
total_kor int,
total_eng int,
total_mat int,
total_sum int,
total_avg double
);

#결과테이블
call print_report(5,25);



