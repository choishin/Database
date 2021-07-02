DROP PROCEDURE IF EXISTS insert_examtable;
DELIMITER $$
CREATE PROCEDURE insert_examtable(_last integer)
BEGIN
DECLARE _name varchar(20);
DECLARE _id integer;
DECLARE _cnt integer;
SET _cnt=0;
delete from examtable where id >0;
_loop :LOOP
SET _cnt=_cnt+1; 
SET _name = concat("홍길",cast(_cnt as char(4))); 
SET _id = 209900+_cnt; 
INSERT INTO examtable VALUE (_id, _name, rand()*100, rand()*100, rand()*100); 
IF _cnt=_last THEN
LEAVE _loop;
END IF;
END LOOP _loop;
END $$


call insert_examtable(100);
select * from examtable;
 
