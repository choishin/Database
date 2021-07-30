show databases;
use head_first;

drop table clown_info;
create table clown_info (
	name varchar(20),
    last_seen varchar(100),
    apperance varchar(200),
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

select * from clown_info;
select apperance from clown_info;
-- 컬럼에 ","로 겹쳐있는 데이터가 많음. 정규화를 해야함.

-- 컬럼추가 (인덱스)
alter table clown_info 
add column clown_idx int not null;

-- 컬럼 순서 바꾸기 (인덱스)
alter table clown_info
modify column clown_idx int first;

set sql_safe_updates=0;
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

-- 정규화
-- 데이터가 중복된 컬럼을 따로 테이블로 만들 것임
select apperance from clown_info;

SET foreign_key_checks =0;
drop table activities;
create table activities (
activity_id int auto_increment not null,
activity varchar(50),
primary key (activity_id)
);

show tables;
select * from activities;

-- ","로 쪼개기
select substring_index(activities,',',1) from clown_info;

-- distinct로 중복되는 것 없앨 수 있는지 확인 (1로 하면 앞에서 첫번째, -1하면 뒤에서 첫번째)
select distinct substring_index(activities,',',1) from clown_info;
select distinct substring_index(activities,',',-1) from clown_info;

-- 적용하기!! substring으로 나눈 컬럼을 distinct로 중복을 없앤 컬럼을 insert 하기
insert activities(activity) select distinct substring_index(activities,',',1) from clown_info;
insert activities(activity) select distinct substring_index(activities,',',-1) from clown_info;

-- clown_info --∈ info_activities ∋-- activities 
drop table info_activities;
create table info_activities (
clown_idx int,
activity_id int,
foreign key(clown_idx) references clown_info (clown_idx),
foreign key(activity_id) references activities (activity_id)
);

alter table clown_info 
add column 

