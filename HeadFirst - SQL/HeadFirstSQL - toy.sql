use head_first;

drop table toys_tmp;
create table toys_tmp( 
	toy_id int not null,
    store_id int not null,
    color varchar(20) not null,
    inventory int not null,
    store_address varchar(50),
    primary key (toy_id, store_id,color)
);

insert into toys_tmp values(5,1,'white',34,'23 Maple');
insert into toys_tmp values(5,3,'yellow',12,'100 E.North St.');
insert into toys_tmp values(5,1,'blue',5,'23 Maple');
insert into toys_tmp values(6,2,'green',10,'1902 Amber Ln');
insert into toys_tmp values(6,4,'yellow',24,'17 Engleside');
insert into toys_tmp values(9,1,'red',50,'23 Maple');
insert into toys_tmp values(9,2,'blue',2,'1902 Amber Ln');
insert into toys_tmp values(9,2,'green',18,'1902 Amber Ln');
insert into toys_tmp values(12,4,'White',28,'17 Engleside');
insert into toys_tmp values(12,4,'yellow',11,'17 Engleside');

drop table toy_tmp;
create table toy_tmp (
	toy_id int not null,
    toy varchar(20) not null
);

insert into toy_tmp values (5,'whiffleball');
insert into toy_tmp values (6,'frisbee');
insert into toy_tmp values (9,'kite');
insert into toy_tmp values (12,'yoyo');

select * from toys_tmp;
select * from toy_tmp;

-- 위의 테이블을 2NF인 테이블 세개로 다시 설계하기
-- 테이블 하나는 장난감에 대한 정보를 가지고, 또 하나는 상점 정보를 하기고, 세번째 테이블은 두 테이블을 연결하는 테이블임.
-- 마지막으로 phone(전화번호), manager(관리자), weight(무게) 열을 적당한 테이블에 추가 할 것.

drop table toys;
create table toys (
	toy_id int not null,
    toy varchar(20) not null,
    color varchar(20) not null,
    weight float,
    cost float,
	constraint pk_toy_id_toy_color
    primary key (toy_id,toy,color)
);

-- 기존 테이블에서 필요한 데이터 뽑아서 넣기
select b.toy_id, a.toy, b.color from toy_tmp as a, toys_tmp as b where a.toy_id = b.toy_id;
insert toys(toy_id, toy, color) select b.toy_id, a.toy, b.color from toy_tmp as a, toys_tmp as b where a.toy_id = b.toy_id;
-- 잘 들어갔나 확인하기
select * from toys;

SET foreign_key_checks =0;
drop table store;
create table store (
	store_id int not null,
    store_address varchar(50) not null,
	manager varchar(30),
    phone varchar(30),
    constraint pk_store_id_store_address
    primary key (store_id, store_address)
);
-- 기존 테이블에서 필요한 데이터 뽑아서 넣기
select distinct store_id, store_address from toys_tmp;
insert store(store_id,store_address) select distinct store_id, store_address from toys_tmp;

-- 잘 들어갔나 확인하기
select * from toys_tmp;
select * from store;

drop table toy_store_relation;
create table toy_store_relation (
	toy_id int not null,
    store_id int not null,
    color varchar(20) not null,
    inventory int not null,
    foreign key (toy_id) references toys(toy_id) ON UPDATE CASCADE ON DELETE CASCADE,
    foreign key (color) references toys(color) ON UPDATE CASCADE ON DELETE CASCADE,
    foreign key (store_id) references store(store_id) ON UPDATE CASCADE ON DELETE CASCADE,
	primary key(toy_id,color,store_id)
);

-- 테이블에 primary key를 추가해줬음
alter table toy_store_relation
add primary key(toy_id,store_id);

alter table toy_store_relation
add constraint toys_toy_id_pk foreign key (toy_id) references toys(toy_id) ON UPDATE CASCADE ON DELETE CASCADE;

alter table toy_store_relation
add constraint store_store_id_pk foreign key (store_id) references store(store_id) ON UPDATE CASCADE ON DELETE CASCADE;

-- 기존 테이블에서 필요한 데이터 뽑아서 넣기
select * from toys_tmp;
insert toy_store_relation select toy_id, store_id,inventory from toys_tmp;

-- PRIMARY KEY 제약 조건을 설정하면, 해당 필드는 NOT NULL과 UNIQUE 제약 조건의 특징을 모두 가집니다.
-- 따라서 이 제약 조건이 설정된 필드는 NULL 값을 가질 수 없으며, 또한 중복된 값을 가져서도 안 됩니다.
-- 이러한 PRIMARY KEY 제약 조건을 기본 키라고 합니다.

-- FOREIGN KEY 제약 조건을 설정한 필드는 외래 키라고 부르며, 한 테이블을 다른 테이블과 연결해주는 역할을 합니다.
-- 외래 키가 설정된 테이블에 레코드를 입력하면, 기준이 되는 테이블의 내용을 참조해서 레코드가 입력됩니다.
-- 즉, FOREIGN KEY 제약 조건은 하나의 테이블을 다른 테이블에 의존하게 만듭니다.
-- FOREIGN KEY 제약 조건을 설정할 때 참조되는 테이블의 필드는 반드시 UNIQUE나 PRIMARY KEY 제약 조건이 설정되어 있어야 합니다.

-- 잘 들어갔나 확인하기
select * from toys_tmp;
select * from toy_tmp;
select * from toys;
select * from store;
select * from toy_store_relation;

-- 의문...분명 제약조건을 넣었는데, 부모테이블에 없는 값도 잘 들어감...
insert into toy_store_relation value(7,1,1);
delete from toy_store_relation where toy_id =7 and inventory =1;


