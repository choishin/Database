-- 그렉이 직업소개업에 뛰어들었습니다!
-- 현재의 직업 job_current(contact_id, title,salary,start_date)
-- 원하는 직업 job_desired(contact_id, title,salary_low,salary_high,available,years_exp)
-- 소개할 직업 job_listings(job_id,title,salary,zip,_description)

drop table job_current;
create table job_current (
	contact_id int not null,
    title varchar(20) not null,
    salary int,
    start_date date,
	constraint pk_contact_id
    primary key (contact_id),
    constraint fk_contact_id
    foreign key (contact_id) references my_contacts(contact_id)
);

drop table job_desired;
create table job_desired (
	contact_id int not null,
    title varchar(20) not null,
    salary_low int,
    salary_high int,
    available char(1),
    years_exp int,
	constraint pk_contact_id
    primary key (contact_id),
	constraint fk_contact_id2
    foreign key (contact_id) references my_contacts(contact_id)
);

drop table job_listings;
create table job_listings (
	job_id int not null auto_increment,
    title varchar(20) not null,
    salary int,
    zip varchar(20),
    description longtext,
	constraint pk_job_id
    primary key (job_id)
);
select * from my_contacts;

-- 웹개발자를 찾는다. 5년이상경력에 105,000 이하의 연봉을 요구하는 웹 개발자를 찾는 쿼리를 찾아라
select a.contact_id, b.title, b.salary_low, b.available, b.years_exp from job_current as a, job_desired as b
where b.title = 'developer' and b.salary_low = 105000 and b.available = 'y'and b.years_exp >=5;

insert into job_current(contact_id,title) select m.contact_id, p.profession from my_contacts m LEFT OUTER JOIN profession p ON m.prof_id = p.profession_id;
insert into job_listings(title) values('Cook');
insert into job_listings(title) values('Hairdresser');
insert into job_listings(title) values('Waiter');
insert into job_listings(title) values('Web Designer');
insert into job_listings(title) values('Web Developer');

select * from profession;
select * from job_listings;
select * from job_desired;
select * from job_current;
select * from my_contacts;

-- 더미데이터 입력
update job_current set salary=12000 where contact_id = 1;
update job_current set salary=20000 where contact_id = 2;
update job_current set salary=18000 where contact_id = 3;
update job_current set salary=17000 where contact_id = 341;
update job_current set salary=25000 where contact_id = 1854;
insert into job_desired(contact_id, title, salary_low, salary_high) values(2, 'Chef', 20000, 22000);

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

-- job_current 테이블에서 모든 직업을 찾습니다.
select title from job_listings group by title order by title;
select mc.last_name, mc.first_name, p.profession from my_contacts mc, profession p where mc.prof_id=p.profession_id;

-- natural Join : 동일한 타입과 이름을 가진 컬럼을 조인 조건으로 이용하는 조인을 간단히 표현하는 방법
select mc.last_name, mc.first_name from my_contacts mc NATURAL JOIN job_desired jd where jd.title = 'Chef' and jd.salary_low <= 20500; 

-- IN : where과 같이 쓰임 그 조건 중 하나에 해당하면 모두 반환하는 쿼리
select mc.last_name, mc.first_name, jc.title from my_contacts mc NATURAL JOIN job_current jc where jc.title IN ('Chef','Artist','Veterinarian','Web Designer','Web Developer'); 
 
-- 테이블에서 가장 연봉이 높은 직업들의 이름을 나열하세요.
select title from job_current jc where salary = (select max(salary) from job_current);

-- 더 쉬운 방법이 있음 (order by salary로 해놓고 맨위의 1개만 뽑는방법)
select title from job_current order by salary desc limit 1;

-- 평균 연봉보다 많은 연봉인 사람의 이름과 성을 나열하세요.
select mc.contact_id, mc.last_name, mc.first_name from my_contacts mc, job_current jc where jc.contact_id = mc.contact_id and jc.salary >= (select avg(salary) from job_current);


