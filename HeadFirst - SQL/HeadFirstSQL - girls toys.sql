use head_first;
drop table girls;
create table girls (
	girl_id int not null auto_increment,
	girl varchar(100) not null,
    toy_id int not null,
    constraint pk_girls
    primary key (girl_id)
);

drop table toys;
create table toys (
	toy_id int not null auto_increment,
    toy varchar(100) not null,
    constraint pk_toy_id
    primary key (toy_id,toy)
);

alter table toys
drop primary key;

insert into girls(girl, toy_id) values('Jane',3);
insert into girls(girl, toy_id) values('Sally',4);
insert into girls(girl, toy_id) values('Cindy',1);

insert into toys(toy) values('hula hoop');
insert into toys(toy) values('balsa glider');
insert into toys(toy) values('toy sodiers');
insert into toys(toy) values('harmonica');
insert into toys(toy) values('baseball cards');
insert into toys(toy) values('tinker toys');
insert into toys(toy) values('etch-a-sketch');
insert into toys(toy) values('slinky');

select * from girls;
select * from toys;

-- 내부조인 : INNER JOIN
select g.girl, t.toy from girls g INNER JOIN toys t ON g.toy_id = t.toy_id;
-- 왼쪽 외부조인 : LEFT OUTER JOIN (왼쪽 테이블의 모든 행과 오른쪽 테이블의 행을 비교)
select g.girl, t.toy from girls g LEFT OUTER JOIN toys t ON g.toy_id = t.toy_id;
-- ※만일 이렇게 toys를  왼쪽에 놓게되면 빈곳에는 null값이 나옴.
select g.girl, t.toy from toys t LEFT OUTER JOIN girls g ON g.toy_id = t.toy_id;
-- 오른쪽 외부조인 : RIGHT OUTER JOIN (오른족 외부조인은 오른쪽 테이블을 왼쪽 테이블과 비교합니다.)
select g.girl, t.toy from girls g  RIGHT OUTER JOIN toys t ON g.toy_id = t.toy_id;
select g.girl, t.toy from toys t RIGHT OUTER JOIN girls g ON g.toy_id = t.toy_id;
-- 테이블의 이름을 이리저리 바꾸는 것보다 그냥 LEFT, RIGHT 바꿔서 쓰는게 훨씬 편함.

-- 외부조인과 여러개의 일치
insert into girls(girl,toy_id) values('Jen',1);
insert into girls(girl,toy_id) values('Cleo',1);
insert into girls(girl,toy_id) values('Sally',3);
insert into girls(girl,toy_id) values('Martha',3);

insert into toys(toy) values('squirt gun');
insert into toys(toy) values('crazy straw');
insert into toys(toy) values('slinky');

select g.girl, t.toy from toys t LEFT OUTER JOIN girls g ON g.toy_id = t.toy_id;
select g.girl, t.toy from girls g  LEFT OUTER JOIN toys t ON g.toy_id = t.toy_id;


