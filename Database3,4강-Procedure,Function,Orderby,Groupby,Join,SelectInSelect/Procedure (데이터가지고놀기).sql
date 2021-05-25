#만일 다시 시행하게 될 경우 '처음부터 다시 만들라'는 명령어 
DROP PROCEDURE IF EXISTS insert_examtable;
#delimiter : 구분자라는 뜻, 여기서부터 '선언시작'
DELIMITER $$
# 'insert_examtable'이라는 procedure 생성, 파라미터 값으로 _last를 받음
CREATE PROCEDURE insert_examtable(_last integer)
#'BEGIN' : 시행해줄 명령을 여기서부터 시작
BEGIN
#'DECLARE' : 변수선언 
DECLARE _name varchar(20);
DECLARE _id integer;
DECLARE _cnt integer;
#SET : 값 초기화
SET _cnt=0;
#'만일 id 값이 1이상인 항목이 있다면 지워라' 
delete from examtable where id >0;
#LOOP : 반복문 
_loop :LOOP
SET _cnt=_cnt+1; #_cnt는 1씩 증가
SET _name = concat("홍길",cast(_cnt as char(4))); #_name에는 '홍길'에 숫자를 char(4바이트)로 형변환한 _cnt를 concat 시킴
SET _id = 209900+_cnt; #_id에는 209900에 _cnt를 붙임
#위에서 만들어준 값을 examtable에 넣기
INSERT INTO examtable VALUE (_name, _id, rand()*100, rand()*100, rand()*100); 
#IF문 '만일 _cnt가 _last라면  LEAVE(break)'
IF _cnt=_last THEN
LEAVE _loop;
#IF종료, LOOP종료, DELIMITER 종료
END IF;
END LOOP _loop;
END $$

#위에서 작성한 'insert_examtable'을 호출
call insert_examtable(1000);
#'examtable'을 보기
select * from examtable;
#셀렉트된 집합에서 30번째 부터 59개를 출력하라. (#LIMIT : 시작값, 종료값을 지정할 수 있음)
select *, kor+eng+mat as sum, (kor+eng+mat)/3 as ave from examtable LIMIT 30,59; 
