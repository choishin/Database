#examtableEX : insert into select로 내용 채워 넣을 테이블 만들기
drop table if exists examtableEX;
create table examtableEX(
	id int not null primary key,
    name varchar(20),
    kor int, eng int, mat int, sum int, ave double, ranking int);
desc examtableEX;

#insert안에 select 문으로 테이블에 내용 채우기
insert into examtableEX select *,b.kor+b.eng+b.mat, (b.kor+b.eng+b.mat)/3, (select count(*)+1 from examtable as a where(a.kor+a.eng+a.mat)> (b.kor+b.eng+b.mat)) from examtable as b;

select * from examtableEX order by ranking desc;