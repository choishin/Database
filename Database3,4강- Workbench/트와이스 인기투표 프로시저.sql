#drop table if exists tupyo2;
#create table tupyo2(
#kiho1 int,
#kiho2 int,
#kiho3 int,
#age int);
#desc tupyo2;

drop procedure if exists insert_tupyo2(
delimiter $$
create procedure insert_tupyo2(_limit integer)
begin
declare _cnt integer;
declare _kiho1 integer;
declare _kiho2 integer;
declare _kiho3 integer;
declare _age integer;
delete from tupyo2 where age>0;
SET _cnt=0;
_loop :LOOP
SET _cnt=_cnt+1;
SET _kiho1 = rand()*8+1;
SET _kiho2 = rand()*8+1;
SET _kiho3 = rand()*8+1;
SET _age = rand()*8+1;
insert into tupyo2 value (_kiho1,_kiho2,_kiho3,_age);
if _cnt=_limit THEN
LEAVE _loop;
END IF;
END LOOP _loop;
END $$


