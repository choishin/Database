#순위를 정하는 함수를 생성
drop function if exists sum_ranking;
DELIMITER $$
create function sum_ranking(_sum integer) returns integer
begin
declare _ranking integer;
#합계가 > 파라미터로 전달된 합계치보다 큰 곳을 찾아 전부 count를 해주고 자기자신을 포함시켜 순위를 작성.
select count(*)+1 into _ranking from examtable where kor+eng+mat > _sum;
return _ranking;
end $$
