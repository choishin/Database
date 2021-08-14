drop table clown_description;
create table clown_description (
	id int not null auto_increment,
    gender char(1) not null,
    descript varchar(200) not null,
    _when date,
    constraint pk_id
    primary key(id)
);

drop table clown_info;
create table clown_info (
	id int not null auto_increment,
    name varchar(100),
	constraint pk_id
    primary key(id)
);

drop table info_activities;
create table info_activities (
	id int not null,
    activity_id int not null,
	foreign key (id) references clown_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (activity_id) references activities(activity_id) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table activities;
create table activities (
	activity_id int not null auto_increment,
    activity varchar(200),
	constraint pk_activity_id
    primary key(activity_id)
);

drop table info_location;
create table info_location (
	id int not null,
    location_id int not null,
    _when date,
	foreign key (id) references clown_info(id) ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (location_id) references location(location_id) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table location;
create table location (
	location_id int not null auto_increment,
    location varchar(100),
	constraint pk_location_id
    primary key(location_id)
);

-- 만일 여기서 조직체계가 생긴다면 어떻게 할 것인가.
insert into clown_info(name) values('Elsie');
insert into clown_info(name) values('Pickles');
insert into clown_info(name) values('Snuggles');
insert into clown_info(name) values('Mr.Hobo');
insert into clown_info(name) values('Clarabelle');
insert into clown_info(name) values('Scooter');
insert into clown_info(name) values('Zippo');
insert into clown_info(name) values('Babe');
insert into clown_info(name) values('Bonzo');
insert into clown_info(name) values('Mister Sniffles');

-- 우선 각각의 광대에게 boss_id를 부여하고 테이블을 만들어 연결한다.
drop table clown_boss;
create table clown_boss (
	clown_id int not null auto_increment,
    boss_id int not null,
	foreign key (clown_id) references clown_info(id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into clown_boss(boss_id) values(3);
insert into clown_boss(boss_id) values(5);
insert into clown_boss(boss_id) values(10);
insert into clown_boss(boss_id) values(3);
insert into clown_boss(boss_id) values(10);
insert into clown_boss(boss_id) values(3);
insert into clown_boss(boss_id) values(3);
insert into clown_boss(boss_id) values(5);
insert into clown_boss(boss_id) values(5);
insert into clown_boss(boss_id) values(10);

select * from clown_info;
select * from clown_boss;

alter table clown_info
change column name _name varchar(100);
-- 자신을 참조하는 참조키 (최종보스는 자기 자신을 참조하도록 함)
select b.clown_id,i._name from clown_boss b LEFT OUTER JOIN clown_info i ON b.boss_id = i.id;

-- 같은 테이블로 조인
-- clown_info1과 clown_info2 라는 동일한 테이블이 있다고 가정합시다. 
drop table clown_info1;
create table clown_info1 (
	id int not null,
    name varchar(200) not null,
    boss_id int not null,
    constraint pk_id
    primary key (id)
);

drop table clown_info2;
create table clown_info2 (
	id int not null,
    name varchar(200) not null,
    boss_id int not null,
    constraint pk_id
    primary key (id)
);

select i.id, i._name, b.boss_id from clown_info i LEFT OUTER JOIN clown_boss b ON b.clown_id = i.id;
insert into clown_info1 select i.id, i._name, b.boss_id from clown_info i LEFT OUTER JOIN clown_boss b ON b.clown_id = i.id;
insert into clown_info2 select i.id, i._name, b.boss_id from clown_info i LEFT OUTER JOIN clown_boss b ON b.clown_id = i.id;
select * from clown_info1;
select * from clown_info2;

-- 각 광대의 이름과 보스 광대의 이름을 포함한 결과 테이블을 가져오는 하나의 조인을 작성하세요.
select c1.id, c1.name, c2.name from clown_info1 c1 LEFT OUTER JOIN clown_info2 c2 ON c1.boss_id = c2.id;







