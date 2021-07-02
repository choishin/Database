select * from examtable;
DROP view IF EXISTS examview;
create view examview(name,id,kor,eng,mat,tot,ave,ran) 
as select *, b.kor+b.eng+b.mat, (b.kor+b.eng+b.mat)/3, (select count(*)+1 from examtable as a 
where (a.kor+a.eng+a.mat) >(b.kor+b.eng+b.mat)) from examtable as b;
#전부 보기 : 오케이
select * from examview;
#컬럼별로 보기 : 오케이
select name,ran from examview;
#조건별로 보기 : 오케이
select * from examview where ran <5;
select * from examview where ran >5;
#자료 넣기는 오류!!!
insert into examview values ("나연",309933,100,100,100,300,100,1);