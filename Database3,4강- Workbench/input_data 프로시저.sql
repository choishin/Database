drop procedure if exists input_data(
delimiter $$
create procedure input_data(_limit integer)
begin
declare _cnt integer;
declare _kiho integer;
declare _age integer;
delete from tupyo where age>0;
SET _cnt=0;
_loop :LOOP
SET _cnt=_cnt+1;
SET _kiho = rand()*8+1;
SET _age = rand()*60+1;
insert into tupyo value (_kiho,_age);
if _cnt=_limit THEN
LEAVE _loop;
END IF;
END LOOP _loop;
END $$


