
-- union : 아래  컬럼을 다 합친 결과를 보여줌. (단, 데이터 타입이 같아야 하고, 찾고자 하는 컬럼수가 같아야함 (여기선 title 한개로 동일))
select title from job_current
union
select title from job_desired
union
select title from job_listings order by title;


-- union all : 만일 중복된 값도 보고싶다면 
select title from job_current
union all
select title from job_desired
union all
select title from job_listings order by title;

-- create table as : 유니온으로 테이블을 만들 수도 있음. (간이 테이블이 필요할 때 쓸 수있는 방법)
drop table my_union;
create table my_union as
select title from job_current union
select title from job_desired union
select title from job_listings;

select * from my_union;

-- 다음의 유니옴을 만드세요. job_current의 contact_id와 job_listings의 salary
select contact_id from job_current 
union
select salary from job_listings; 

-- 결과의 데이터 타입이 무엇일지 추측해보세요. create table as를 사용하여 유니온을 사용한 결과의 테이블을 만드는 쿼리를 작성하세요.
drop table my_union;
create table my_union as
select salary from job_listings
union
select title from job_current; 

-- desc를 사용해서 여러분이 추측한 데이터 타입이 맞았는지 확인하세요. 답 : varchar와 int가 섞이면 varchar로 정해짐. 
select * from my_union;
desc my_union;

-- Intersect, Except (<-mysql에는 없는 기능)
-- select title from job_current
-- intersect
-- select salary from job_listings; <- 안됨.


