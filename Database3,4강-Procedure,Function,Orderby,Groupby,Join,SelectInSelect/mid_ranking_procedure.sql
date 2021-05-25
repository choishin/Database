#순위를 정하는 함수를 생성
drop function if exists mid_ranking;
DELIMITER $$
create function mid_ranking(_sum integer,_page integer,_result integer) returns integer
begin
declare _ranking integer;
declare id_start integer;
declare id_end integer;

	set id_start = 209901+((_page-1)*_result);
    set id_end= 209901+(_page*_result);
    
#합계가 > 파라미터로 전달된 합계치보다 큰 곳을 찾아 전부 count를 해주고 자기자신을 포함시켜 순위를 작성.
select count(*)+1 into _ranking from examtable where kor+eng+mat > _sum and student_id > id_start and student_id <= id_end;
return _ranking;
end $$

show global variables like 'log_bin_trust_function_creators';
SET Global log_bin_trust_function_creators = 'ON';