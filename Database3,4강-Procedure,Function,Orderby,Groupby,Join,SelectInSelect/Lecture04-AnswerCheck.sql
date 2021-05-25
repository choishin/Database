drop table if exists Answer;  -- 정답지   
create table Answer (
	subjectID int not null primary key,
    a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int
    );
    
drop table if exists Testing; -- 학생 답안 
create table Testing (
	subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
	a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    primary key(subjectID,stu_id)
    );
    
drop table if exists Scoring; -- 문항별 채점 결과
create table Scoring (
	subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
	a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    score int,
    primary key(subjectID,stu_id)
    );
    
drop table if exists Reporttable;  -- 한반의 시험결과 
create table Reporttable (
stu_name varchar(20),
stu_id int not null primary key,
kor int, eng int, mat int
);

DESC Answer;
DESC Testing;
DESC Scoring;
DESC Reporttable;

#정답테이블 만들기
delete from Answer;
insert into Answer value(1,floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
						   floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
                           floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
                           floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2)
                           );
insert into Answer value(2,floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
						   floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
                           floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
                           floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2)
                           );
insert into Answer value(3,floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
						   floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
						   floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),
						   floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2),floor(rand()*2)
                           );
select * from Answer;
