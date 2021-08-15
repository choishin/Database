select * from my_contacts;
select * from job_current;
select * from job_listings;
select * from job_desired;
select * from zip_code;
desc my_contacts;

select * from profession where profession = 'teacher';

-- 그렉이 직원을 고용했습니다. 새 사람을 데이터베이스에 넣는 쿼리를 작성할 수 있겠어요? (주어진 데이터에 성별이 없음)
-- 성별을 대신 X로 넣기로 함. -> 총 성별의 수를 구할때 큰 문제가 됨.
insert into my_contacts values(4, 'pat', 'Murphy', 5551239, 'patmurphy@someemail.com','X', '1978-4-15', 2,10087,1);
insert into zip_code value(10087, 'anycity', 'AC');

-- 제발 확인해주세요 check 조건! : 체크 제약조건은 열에 넣을 수있는  값을 제한 합니다. where절과 같은 조건식을 사용합니다.)
-- MySQL은 check가 무결성을 강제하지 않는다고 함.... MySQL은 그냥 무시함. 
-- drop table piggy_bank;
-- create table piggy_bank (
-- 	id int not null auto_increment,
--     coin char(1) check (coin IN ('P','N','D','Q')),
--     constraint pk_id
--     primary key (id)
-- );

-- job_desired 테이블에서 웹디자이너와 연락처를 찾는 쿼리. 
select mc.last_name, mc.first_name, mc.phone from my_contacts mc right outer join job_desired jd on mc.contact_id = jd.contact_id and jd.title='Web develop';
select mc.last_name, mc.first_name, mc.phone from my_contacts mc right outer join job_desired jd on mc.contact_id = jd.contact_id and jd.title='Chef';
-- technical writer 기술문서 작성자를 구하는 쿼리도 작성해 보세요.
select jl.job_id, jl.title, jl.salary from job_listings jl where jl.title = 'Technical Writer';
select jl.job_id, jl.title, jl.salary from job_listings jl where jl.title = 'Cook';

select * from my_contacts;
select * from job_current;
select * from job_listings;
select * from job_desired;
select * from zip_code;

-- view : 테이블을 직접만드는 대신 view를 만들어서 쉽게 결과를 저장해 놓을 수가 있음.
-- 뷰를 가상테이블이라고도 함. 
create view web_designers as select mc.first_name, mc.last_name, mc.phone, mc.email from my_contacts mc
NATURAL JOIN job_desired jd
WHERE jd.title = 'Web Designer';
select * from web_designers;

create view tech_writer_jobs as 
select title, salary, description
from job_listings
where title = 'technical writer';
select * from tech_writer_jobs;

-- job_current 테이블과 job_desired 테이블 모두에 있는 사람에 대해 현재 버는 연봉과 받고 싶은 연봉 그리고 그 차액을 나타내는 뷰 만들기.
-- 그들의 이름, 이메일, 전화번호를 주세요.
-- 더미데이터 입력
select * from my_contacts mc inner join profession p on mc.prof_id = p.profession_id;
select * from job_desired;

select * from job_current;
select * from job_listings;
update job_listings set salary = 20000 where job_id = 8;
update job_listings set salary = 18000 where job_id = 9;
update job_listings set salary = 15000 where job_id = 6;
update job_listings set salary = 19000 where job_id = 11;
update job_listings set salary = 24000 where job_id = 12;
insert into job_desired(contact_id, title, salary_low, salary_high) values(3, 'Artist', 15000, 20000);

-- 내가 쓴 답
-- drop view job_raises;
-- create view job_raises as
select mc.last_name, mc.first_name, mc.email, mc.phone, jc.title, jc.salary, jd.salary_low, (jd.salary_low - jc.salary) as additional   
from my_contacts mc, job_desired jd inner join job_current jc 
on jd.title = jc.title where jc.contact_id = mc.contact_id;

-- headfirst 답
-- drop view job_raises;
-- create view job_raises as
select mc.last_name, mc.first_name, mc.email, mc.phone, jc.title, jc.salary, jd.salary_low, (jd.salary_low - jc.salary) as additional   
from job_current jc inner join job_desired jd inner join my_contacts mc 
where jd.title = jc.title and jc.contact_id = mc.contact_id;




