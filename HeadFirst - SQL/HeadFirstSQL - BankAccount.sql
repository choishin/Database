drop table users;
create table users (
	account_id int not null,
    last_name varchar(20),
    first_name varchar(20),
    phone varchar(100),
    email varchar(100),
    address varchar(200),
    constraint pk_account_id
    primary key (account_id)
);

drop table checking;
create table checking (
	account_id int not null,
    balance double not null,
    foreign key (account_id) references users(account_id)
);

drop table savings;
create table savings (
	account_id int not null,
	balance double not null,
    foreign key (account_id) references users(account_id)
);

start transaction;
commit;
rollback;