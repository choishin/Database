use head_first;

drop table boys;
create table boys (
	boy_id int not null auto_increment,
    boy varchar(10),
    toy_id int not null,
	constraint pk_boy_id
    primary key(boy_id),
    constraint fk_toy_id
    foreign key (toy_id) references toys (toy_id)
);

SET foreign_key_checks =1;
drop table toys;
create table toys (
	toy_id int not null auto_increment,
    toy varchar(20),
    constraint pk_toy_id
    primary key(toy_id)
);

insert into toys(toy) values('hula hoop');
insert into toys(toy) values('balsa glider');
insert into toys(toy) values('toy soldiers');
insert into toys(toy) values('harmonica');
insert into toys(toy) values('baseball cards');
select * from toys;

insert into boys(boy,toy_id) values('Davey',3);
insert into boys(boy,toy_id) values('Bobby',5);
insert into boys(boy,toy_id) values('Beaver',2);
insert into boys(boy,toy_id) values('Richie',1);
select * from boys;

-- 내부조인:동등조인
select boys.boy, toys.toy from boys INNER JOIN toys on boys.toy_id = toys.toy_id;
select a.boy, b.toy from boys as a, toys as b where a.toy_id = b.toy_id;

-- 내부조인:비동등조인 (없는 것들을 알려줌)
select boys.boy, toys.toy from boys INNER JOIN toys on boys.toy_id <> toys.toy_id;
select boys.boy, toys.toy from toys INNER JOIN boys on boys.toy_id <> toys.toy_id;

-- 내부조인:자연조인 (두 테이블의 같은 열이름을 알아내서 일치하는 행을 반환함)
select boys.boy, toys.toy from boys Natural JOIN toys;


