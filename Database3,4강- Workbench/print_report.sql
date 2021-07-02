drop procedure if exists  print_report;
delimiter $$
create procedure print_report(_page integer, _result integer)
begin
	declare id_start integer;
    declare id_end integer;
	declare _start integer;
	declare _end integer;
    
	if _page < 1 then
    set _page =1;
    
    else if _page > (1000/_result) then
    set _page = (1000/_result);
    
    end if;
    end if;
    set _start = (_page-1)*_result;
	set _end = _page*_result;
    set id_start = 209901+((_page-1)*_result);
    set id_end= 209901+(_page*_result);
    
    delete from currentpage_result;
    
    insert into currentpage_result values(
		(select sum(kor) from examtable where student_id > id_start and student_id <= id_end),
        (select sum(eng) from examtable where student_id > id_start and student_id <= id_end),
        (select sum(mat) from examtable where student_id > id_start and student_id <= id_end),
        (select sum(kor+eng+mat) from examtable where student_id > id_start and student_id <= id_end),
        (select sum(kor+eng+mat)/3 from examtable where student_id > id_start and student_id <= id_end)
	);
    
    delete from totalpage_result;
    
    insert into totalpage_result values(
		(select sum(kor) from examtable where student_id > 209900 and student_id <= id_end),
        (select sum(eng) from examtable where student_id > 209900 and student_id <= id_end),
        (select sum(mat) from examtable where student_id > 209900 and student_id <= id_end),
        (select sum(kor+eng+mat) from examtable where student_id > 209900 and student_id <= id_end),
        (select sum(kor+eng+mat)/3 from examtable where student_id > 209900 and student_id <= id_end)
	);
    
	select *,kor+eng+mat as sum, (kor+eng+mat)/3 as avg, sum_ranking(kor+eng+mat) from examtable limit _start, _result;
	select * from currentpage_result;
	select * from totalpage_result;
    
end $$
delimiter ;