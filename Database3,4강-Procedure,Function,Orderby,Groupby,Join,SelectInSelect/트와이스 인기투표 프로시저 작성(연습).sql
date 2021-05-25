DROP PROCEDURE IF EXISTS input_data;
DELIMITER $$
CREATE PROCEDURE input_data(_people integer) 
BEGIN
DECLARE _cnt integer;
DECLARE _name varchar(10);
DECLARE _age integer;
SET _cnt = 0;
delete from tupyo2 where age > 0;
#반복문 시작
_loop: LOOP
SET _age = rand()*60+1;
SET _name =rand()*8+1;
IF _name = 1 THEN
SET _name = '나연';
else IF _name = 2 THEN
SET _name = '정연';
else IF _name = 3 THEN
SET _name = '사나';
else IF _name = 4 THEN
SET _name = '쯔위';
else IF _name = 5 THEN
SET _name = '모모';
else IF _name = 6 THEN
SET _name = '미나';
else IF _name = 7 THEN
SET _name = '지효';
else IF _name = 8 THEN
SET _name = '다연';
else if _name = 9 THEN
SET _name = '채영';
SET _cnt=_cnt+1;
end if;
end if;
end if;
end if;
end if;
end if;
end if;
end if;
end if;
insert into tupyo2 value (_name,_age);
IF _cnt=_people THEN
LEAVE _loop;
END IF;
END LOOP _loop;
END $$
