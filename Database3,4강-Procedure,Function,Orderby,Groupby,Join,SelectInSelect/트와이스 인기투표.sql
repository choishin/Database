#use kopoctc;

#create table tupyo(
#twice_name varchar(20),
#age int
#);

#트와이스 전체 득표율 집계
#select * from tupyo;
#select twice_name,count(twice_name) as voteCount, (count(twice_name)/(select count(*) from tupyo))*100 as votePercent
#from tupyo group by twice_name order by votePercent desc; 

#나연 테이블 생성
#create table nayeonResult (
#age_sort varchar(10),
#age_vote int
#);

#나연의 득표결과를 확인
#select * from tupyo where twice_name='나연';
#나연의 나이대별 특표결과를 확인
#select * from tupyo where twice_name='나연'and age>0 and age<20;

#insert into nayeonResult(age_sort,age_vote) select '10대',count(twice_name) from tupyo where twice_name='나연'and age>0 and age<20;
#insert into nayeonResult(age_sort,age_vote) select '20대',count(twice_name) from tupyo where twice_name='나연'and age>20 and age<30;
#insert into nayeonResult(age_sort,age_vote) select '30대',count(twice_name) from tupyo where twice_name='나연'and age>30 and age<40;
#insert into nayeonResult(age_sort,age_vote)select '40대',count(twice_name) from tupyo where twice_name='나연'and age>40 and age<50;
#insert into nayeonResult(age_sort,age_vote)select '50대',count(twice_name) from tupyo where twice_name='나연'and age>50 and age<60;

#select * from nayeonResult;

#정연 테이블 생성
#create table jeongyeonResult (
#age_sort varchar(10),
#age_vote int
#);

#정연의 득표결과를 확인
#select * from tupyo where twice_name='정연';
#정연의 나이대별 특표결과를 확인
#select * from tupyo where twice_name='정연'and age>0 and age<20;

#insert into jeongyeonResult(age_sort,age_vote) select '10대',count(twice_name) from tupyo where twice_name='정연'and age>0 and age<20;
#insert into jeongyeonResult(age_sort,age_vote) select '20대',count(twice_name) from tupyo where twice_name='정연'and age>20 and age<30;
#insert into jeongyeonResult(age_sort,age_vote) select '30대',count(twice_name) from tupyo where twice_name='정연'and age>30 and age<40;
#insert into jeongyeonResult(age_sort,age_vote)select '40대',count(twice_name) from tupyo where twice_name='정연'and age>40 and age<50;
#insert into jeongyeonResult(age_sort,age_vote)select '50대',count(twice_name) from tupyo where twice_name='정연'and age>50 and age<60;

#select * from jeongyeonResult order by age_vote desc;
