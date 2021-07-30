use kopoctc;
show tables;

drop table rate_info;
create table rate_info (
	st_val bigint not null,
	en_val bigint not null,
	validate_dt int not null,
	expire_dt int not null,
	charging_val bigint not null,
	charging_cd_type int not null,
	primary key (st_val, en_val, validate_dt, expire_dt)
);

-- pk를 4개를 잡아야함 (시작값, 종료값,적용시작일,적용종료일) <- 이렇게 4개가 복합키

drop table charge;
create table charge (
st_val bigint not null,
en_val bigint not null,
validate_dt int not null,
expire_dt int not null,
foreign_rate_val bigint not null,
foreign_unit_val bigint not null,
discount_rate float not null,
foreign key (st_val, en_val, validate_dt, expire_dt) references rate_info (st_val, en_val, validate_dt, expire_dt)
ON UPDATE CASCADE ON DELETE CASCADE
);

-- pk를 연결을 해두지 않으면 관계가 성립이 안 됨.

insert into charge(st_val, en_val,foreign_rate_val, foreign_unit_val,discount_rate) value (1,2,1,2,3);
select * from charge;
insert into rate_info values(1,2,210730,210801,12,34);
select * from rate_info;
desc rate_info;
desc charge;


drop table free;
create table free (
idx int not null auto_increment,

deduct_val bigint not null,
primary key (idx),
foreign key (cd_type) references rate_info (rate_cd_type)
);

drop table discount;
create table discount (
idx int not null auto_increment primary key,
cd_type int not null references rate_cd_type,
discount_rate float not null,
primary key (idx),
foreign key (cd_type) references rate_info (rate_cd_type)
);

-- 복합키를 만드는 것은 primary key를 두개 이상 선언하면 됨.
