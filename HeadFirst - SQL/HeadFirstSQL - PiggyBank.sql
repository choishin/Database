drop table piggy_bank;
create table piggy_bank (
	id int not null auto_increment,
    coin char(1) check (coin IN ('P','N','D','Q')),
    coin_year char(4),
    constraint pk_id
    primary key (id)
);

insert into piggy_bank(coin,coin_year) values('Q','1950');
insert into piggy_bank(coin,coin_year) values('P','1972');
insert into piggy_bank(coin,coin_year) values('N','2005');
insert into piggy_bank(coin,coin_year) values('Q','1999');
insert into piggy_bank(coin,coin_year) values('Q','1981');
insert into piggy_bank(coin,coin_year) values('D','1940');
insert into piggy_bank(coin,coin_year) values('Q','1980');
insert into piggy_bank(coin,coin_year) values('P','2001');
insert into piggy_bank(coin,coin_year) values('D','1926');
insert into piggy_bank(coin,coin_year) values('P','1999');

-- 쿼터만을 가진 행을 보여주는 뷰를 작성합시다.
create view pb_quarters as 
select * from piggy_bank where coin='Q';

-- pb_dimes 라는 뷰를 만들기
create view pb_dimes as 
select * from piggy_bank where coin='D' with check option;

-- Insert, Delete, Update 쿼리를 실행하면 어떻게 되는지 써보세요. 
insert into pb_quarters(coin,coin_year) values('Q','1993');
insert into pb_quarters(coin,coin_year) values('D','1942');
delete from pb_quarters where coin = 'N' or coin = 'P' or coin = 'D';
update pb_quarters set coin = 'Q' where coin = 'D';

-- 검증결과 with check option은 아주 잘 작동함. (mysql)
insert into pb_dimes(coin,coin_year) values('Q','2005');
update pb_dimes set coin = 'x';

select * from pb_quarters;
-- transaction 테스트
start transaction;
commit;
rollback;

-- rollbck
set sql_safe_updates=0;
start transaction;
select * from piggy_bank;
update piggy_bank set coin ='Q' where coin ='P';
select * from piggy_bank;
rollback;
select * from piggy_bank;

-- commit
start transaction;
select * from piggy_bank;
update piggy_bank set coin ='Q' where coin ='P';
select * from piggy_bank;
commit;
select * from piggy_bank;

