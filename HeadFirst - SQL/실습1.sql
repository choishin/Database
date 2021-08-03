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
    constraint pk_st_val_en_val_validate_dt_expire_dt
	primary key (st_val, en_val, validate_dt, expire_dt)
);

-- pk를 4개를 잡아야함 (시작값, 종료값,적용시작일,적용종료일) <- 이렇게 4개가 복합키
-- 복합키를 만드는 것은 primary key를 두개 이상 선언하면 됨.

drop table charge;
create table charge (
st_val bigint not null,
en_val bigint not null,
validate_dt int not null,
expire_dt int not null,
foreign_rate_val bigint not null,
foreign_unit_val bigint not null,
discount_rate float not null,
constraint st_val_en_val_validate_dt_expire_dt_fk
foreign key (st_val, en_val, validate_dt, expire_dt) references rate_info (st_val, en_val, validate_dt, expire_dt)
ON UPDATE CASCADE ON DELETE CASCADE,
constraint pk_st_val_en_val_validate_dt_exprire_dt
primary key (st_val, en_val, validate_dt, expire_dt)
);

-- pk를 연결을 해두지 않으면 관계가 성립이 안 됨.

drop table free;
create table free (
st_val bigint not null,
en_val bigint not null,
validate_dt int not null,
expire_dt int not null,
deduct_val bigint not null,
constraint fk_st_val_en_val_validate_dt_expire_dt
foreign key (st_val, en_val, validate_dt, expire_dt) references rate_info (st_val, en_val, validate_dt, expire_dt)
ON UPDATE CASCADE ON DELETE CASCADE,
constraint pk_st_val_en_val_validate_dt_exprire_dt_
primary key (st_val, en_val, validate_dt, expire_dt)
);

drop table discount;
create table discount (
st_val bigint not null,
en_val bigint not null,
validate_dt int not null,
expire_dt int not null,
discount_rate float not null,
foreign key (st_val, en_val, validate_dt, expire_dt) references rate_info (st_val, en_val, validate_dt, expire_dt)
ON UPDATE CASCADE ON DELETE CASCADE,
constraint pk_st_val_en_val_validate_dt_exprire_dt
primary key (st_val, en_val, validate_dt, expire_dt)
);


insert into rate_info values (1,1,1,1,1,1);
insert into charge(st_val, en_val,validate_dt,expire_dt,foreign_rate_val, foreign_unit_val,discount_rate) value (1,1,1,1,1,1,1);
insert into free(st_val, en_val,validate_dt,expire_dt,deduct_val) value (1,1,1,1,1);
insert into discount(st_val, en_val,validate_dt,expire_dt,discount_rate) value (1,1,1,1,1);
insert into discount(st_val, en_val,validate_dt,expire_dt,discount_rate) value (2,1,1,1,1);
select * from charge;
select * from rate_info;
desc rate_info;
desc charge;