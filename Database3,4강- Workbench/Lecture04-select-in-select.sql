#데이터베이스 선택
use kopoctc;
#테이블 목록 보기
show tables;
#목표: hubo(a)에서 이름과 공약을 가져오고, tupyo3(a)에서 득표수를 a.kiho에따라 집계
select (select name from hubo where kiho=a.kiho), 
(select gongyak from hubo where kiho=a.kiho),
count(a.kiho) from tupyo3 as a group by a.kiho;
#한줄씩 확인
select (select name from hubo where kiho=a.kiho) from tupyo3 as a group by a.kiho;
select (select gongyak from hubo where kiho=a.kiho) from tupyo3 as a group by a.kiho;
select count(a.kiho) from tupyo3 as a group by a.kiho;
#'where kiho=a.kiho'를 데이터베이스는 어떻게 처리하나 확인
select a.kiho from tupyo3 as a group by a.kiho;