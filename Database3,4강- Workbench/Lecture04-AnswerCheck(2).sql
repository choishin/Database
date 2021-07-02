delete from Testing;
#t시험테이블 만들기
drop procedure if exists input_Testing;
delimiter $$
create procedure input_Testing(_subjectID integer, _number integer)
begin
	-- subjectID int not null,
   --  stu_name varchar(20),
    -- stu_id int not null,
	-- a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    -- a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    -- primary key(subjectID,stu_id)
    -- );
declare _cnt integer;
declare _stu_id integer;
declare _name integer;
declare _id integer;
declare _a01 integer;
declare _a02 integer;
declare _a03 integer;
declare _a04 integer;
declare _a05 integer;
declare _a06 integer;
declare _a07 integer;
declare _a08 integer;
declare _a09 integer;
declare _a10 integer;
declare _a11 integer;
declare _a12 integer;
declare _a13 integer;
declare _a14 integer;
declare _a15 integer;
declare _a16 integer;
declare _a17 integer;
declare _a18 integer;
declare _a19 integer;
declare _a20 integer;
#delete from Testing where _subjectID >3;
SET _cnt = 0;
_loop :LOOP
SET _cnt=_cnt+1;
SET _name=rand()*8+1;
SET _stu_id =209900+_cnt;
SET _a01= floor(rand()*2);
SET _a02= floor(rand()*2);
SET _a03= floor(rand()*2);
SET _a04= floor(rand()*2);
SET _a05= floor(rand()*2);
SET _a06= floor(rand()*2);
SET _a07= floor(rand()*2);
SET _a08= floor(rand()*2);
SET _a09= floor(rand()*2);
SET _a10= floor(rand()*2);
SET _a11= floor(rand()*2);
SET _a12= floor(rand()*2);
SET _a13= floor(rand()*2);
SET _a14= floor(rand()*2);
SET _a15= floor(rand()*2);
SET _a16= floor(rand()*2);
SET _a17= floor(rand()*2);
SET _a18= floor(rand()*2);
SET _a19= floor(rand()*2);
SET _a20= floor(rand()*2);
insert into Testing value (_subjectID,_name,_stu_id,_a01,_a02,_a03,_a04,_a05,_a06,_a07,_a08,_a09,_a10,_a11,_a12,_a13,_a14,_a15,_a16,_a17,_a18,_a19,_a20);
if _cnt=_number THEN
LEAVE _loop;
END IF;
END LOOP _loop;
END $$

#데이터 채워넣기
call input_Testing(1,1000);
call input_Testing(2,1000);
call input_Testing(3,1000);
select * from Testing;

#이름 바꾸기
update Testing set stu_name = replace (stu_name, 1, '나연');
update Testing set stu_name = replace (stu_name, 2, '정연');
update Testing set stu_name = replace (stu_name, 3, '모모');
update Testing set stu_name = replace (stu_name, 4, '사나');
update Testing set stu_name = replace (stu_name, 5, '지효');
update Testing set stu_name = replace (stu_name, 6, '미나');
update Testing set stu_name = replace (stu_name, 7, '다현');
update Testing set stu_name = replace (stu_name, 8, '채영');
update Testing set stu_name = replace (stu_name, 9, '쯔위');
select * from Testing;