show databases;
use head_first;

-- 썩 잘못된 테이블, 이 잘 못된 테이블로 정규화를 시도 할 것임.
drop table clown_info;
create table clown_info (
	name varchar(20),
    last_seen varchar(100) ,
    appearance varchar(200),
    activities varchar(200)
);

insert into clown_info values('Elsie','Cherry Hill Senior Center', 'F,red hair,green dress,huge feet','balloons,little car');
insert into clown_info values('Pickles','Jack Green\'s party', 'M,orange hair,blue suit,huge feet','mime');
insert into clown_info values('Snuggles','Ball-Mart', 'F,yellow shirt,baggy red pants','horn,umbrella');
insert into clown_info values('Mr.Hobo','BG Circus', 'M,cigar,black hair,tiny hat','violin');
insert into clown_info values('Ciarabelle','Belmont Senior Center', 'F,pink hair,huge flower,blue dress','yelling,dancing');
insert into clown_info values('Scooter','Oakland Hospital', 'M,blue hair,red suit,huge nose','balloons');
insert into clown_info values('Zippo','Milestone Mall', 'F,orange suit,baggy pants','dancing');
insert into clown_info values('Babe','Earl\'s Autos', 'F,all pink and sparkly','balancing,little car');
insert into clown_info values('Bonzo','', 'M,in drag,polka dotted dress','singing,dancing');
insert into clown_info values('Sniffles','Tracy\'s','M,green and purple suit,pointy nose','');
insert into clown_info values('Zippo','Milestone Mall', 'F,orange suit,baggy pants','singing');
insert into clown_info values('Snuggles','Ball-Mart', 'F,yellow shirt,baggy blue pants','horn,umbrella');
insert into clown_info values('Bonzo','Dickson Park', 'M,in drag,polka dotted dress','singing,dancing');
insert into clown_info values('Mr.Hobo','Party for Eric Gray', 'M,cigar,black hair,tiny hat','violin');

-- 잘 들어갔나 확인
select * from clown_info;
-- 컬럼에 ","로 겹쳐있는 데이터가 많음. 정규화를 해야함.

-- 컬럼추가 (인덱스)
alter table clown_info 
add column clown_idx int not null;

-- 컬럼 순서 바꾸기 (인덱스)
set sql_safe_updates=0;
alter table clown_info
modify column clown_idx int first;

-- 잘 들어갔는지 확인하기
select * from clown_info;
select * from clown_info where name ='Pickles';

-- index를 위해 컬럼 수정, 중복된 행 삭제
update clown_info set activities ='climbing into tiny car' where clown_idx =10;
delete from clown_info where name = 'Bonzo' and last_seen ='';

-- 기본키 추가
alter table clown_info
add primary key (clown_idx);

-- 기본키에 auto_increment 추가
alter table clown_info 
modify clown_idx int auto_increment;

-- 정규화 시작!
-- 데이터가 중복된 컬럼을 따로 테이블로 만들 것임
-- 1. activities 항목을 빼주기
select apperance from clown_info;

SET foreign_key_checks =0;
drop table activities;
create table activities (
activity_id int auto_increment not null,
activity varchar(50),
primary key (activity_id)
);

-- clown_info --∈ info_activities ∋-- activities 
--  多:多 관계를 끊기 위해 관계 테이블, 종류 테이블 만들기
drop table info_activities;
create table info_activities (
clown_idx int not null,
activity_id int not null,
foreign key(clown_idx) references clown_info (clown_idx) ON DELETE CASCADE,
foreign key(activity_id) references activities (activity_id) ON DELETE CASCADE
);

-- 테이블 수정하기 (on delete cascade)
alter table info_activities
add foreign key (clown_idx) references clown_info (clown_idx) ON DELETE CASCADE on update cascade;

-- 테이블 잘 생겼나 확인
show tables;

-- substring_index로 ","을 기준으로 쪼개고 데이터 확인
select substring_index(activities,',',1) from clown_info;

-- distinct로 중복되는 것 없앤 후 데이터 확인 (1로 하면 앞에서 첫번째, -1 하면 뒤에서 첫번째)
select distinct substring_index(activities,',',1) from clown_info;
select distinct substring_index(activities,',',-1) from clown_info;

-- 마지막으로 substring_index로 나눈 컬럼을 distinct로 중복을 없앤 컬럼을 insert 하기
insert activities(activity) select distinct substring_index(activities,',',1) from clown_info;
insert activities(activity) select distinct substring_index(activities,',',-1) from clown_info;

-- 쉽게 넣을 방법이 분명 있을 텐데... 우선 일일히 하나씩 넣음
insert into info_activities value(10,8);
-- join문으로 확인하기 (어떤 광대가 어떤 activity를 할 수 있나)
select a.clown_idx, a.name, b.activity_id from clown_info as a, info_activities as b where a.clown_idx = b.clown_idx; 

-- 데이터 잘 들어갔나 확인
select * from clown_info;
select * from activities;
select * from info_activities;


-- 2. clown_info의 last_seen을 따로 빼주기
-- 광대와 장소 정보 연결하기
drop table info_location;
create table info_location (
	clown_idx int not null,
    location_id int not null,
    time_info datetime,
    constraint clown_info_clown_idx_fk
	foreign key(clown_idx) references clown_info(clown_idx) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(location_id) references location(location_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

-- 장소 정보만 넣을 테이블 만들기
drop table location;
create table location (
location_id int not null auto_increment,
location varchar(100),
primary key (location_id)
);

-- 테이블 수정하기 (on delete cascade)
alter table info_location
add foreign key (clown_idx) references clown_info (clown_idx) ON DELETE CASCADE ON UPDATE CASCADE;
alter table info_location
add foreign key (location_id) references location (location_id) ON DELETE CASCADE ON UPDATE CASCADE;

-- 장소 정보 데이터 넣기
insert location(location) select distinct last_seen from clown_info;
insert info_location(clown_idx,location_id) select a.clown_idx, b.location_id from clown_info as a, location as b where a.last_seen = b.location; 

-- 데이터 잘 들어갔나 확인하기
select * from clown_info;
select * from info_location;
select * from location; 
select * from clown_info as a, info_location as b where a.clown_idx = b.id;


-- 2. appearance에 포함된 성별과 외양 정보빼주기 (성별 테이블은 딱히... 필요 없을거 같음, 어차피 두갠데...)
-- drop table sex;
-- create table sex (
-- 	sex_id int not null auto_increment,
--     sex char(1) not null,
--     primary key (sex_id)
-- );

-- 성별 넣을 테이블 만들기
drop table info_sex;
create table info_sex (
clown_idx int not null,
sex char(1),
foreign key(clown_idx) references clown_info(clown_idx)
);

-- clown_info에서 데이터 쪼개서 새로 만든 테이블에 넣기
insert info_sex select clown_idx, (select distinct substring_index(appearance,',',1)) from clown_info;
-- 데이터 잘 들어갔나 확인
select * from info_sex;
select * from clown_info;

-- 외양 정보 넣을 테이블 만들기
drop table appearances;
create table appearances (
	appearance_id int not null auto_increment,
    appearance varchar(100),
    primary key (appearance_id)
);

-- 광대와 외양 정보 연결하는 테이블 만들기
drop table info_appearance;
create table info_appearance (
	clown_idx int not null,
    appearance_id int not null,
    foreign key(clown_idx) references clown_info(clown_idx),
	foreign key(appearance_id) references appearances(appearance_id)
);

-- 테이블 수정
alter table info_appearance
add foreign key (clown_idx) references clown_info (clown_idx) ON DELETE CASCADE ON UPDATE CASCADE;
alter table info_appearance
add foreign key (appearance_id) references appearances (appearance_id) ON DELETE CASCADE ON UPDATE CASCADE;


-- appearance 문자열 잘라서 apperances에 넣기
select distinct substring_index(substring_index(appearance,',',2),',',-1) from clown_info;
insert appearances(appearance) select distinct substring_index(substring_index(appearance,',',2),',',-1) from clown_info;
-- 중간의 문자열 가져오기
select distinct substring_index(substring_index(appearance,',',3),',',-1) from clown_info;
insert appearances(appearance) select distinct substring_index(substring_index(appearance,',',3),',',-1) from clown_info;
-- 중간의 문자열 가져오기
select distinct substring_index(substring_index(appearance,',',4),',',-1) from clown_info;
insert appearances(appearance) select distinct substring_index(substring_index(appearance,',',4),',',-1) from clown_info;
select * from clown_info;

--  info_appearance에 데이터 넣기
-- 쉽게 넣을 수 있는 방법을 아주 많이 고민했음
-- 1. 새로운 컬럼을 만들어서 쪼개서 넣은 후 한번에 insert select 하기 <- 안 됨
-- 2. procedure로 loop안에 넣고 하나씩 넣기 <- 2중 for문을 지원하지 않음
-- 3. dual from where not exists 이용해서 해보려고 함 <- 안 됨
select appearance_id from appearances where appearance='pointy nose';
delete from appearances where appearance_id = 39;
insert into info_appearance(clown_idx,appearance_id) values (10,25);

--  데이터 잘 쪼개서 들어갔나 확인 하기
select * from clown_info;
select * from activities;
select * from info_activities;
select * from sex;
select * from info_sex;
select * from appearances;
select * from info_appearance;
select * from location;
select * from info_location;

-- 만일 광대의 성별을 조회하고 싶다면
select a.clown_idx, b.sex from clown_info as a, info_sex as b where a.clown_idx = b.clown_idx;
-- 만일 광대의 외양을 조회하고 싶다면
select a.clown_idx, b.appearance_id, (select c.appearance from appearances as c where b.appearance_id = c.appearance_id) from clown_info as a, info_appearance as b where a.clown_idx = b.clown_idx;
-- 만일 광대의 위치를 조회하고 싶다면 
select a.clown_idx, b.location_id, (select c.location from location as c where b.location_id = c. location_id) from clown_info as a, info_location as b where a.clown_idx = b.clown_idx;

-- 마지막으로 clown_info에서 따로 빼준 컬럼은 지워준다.
alter table clown_info
drop column last_seen;

alter table clown_info
drop column activities;

alter table clown_info
drop column appearance;

