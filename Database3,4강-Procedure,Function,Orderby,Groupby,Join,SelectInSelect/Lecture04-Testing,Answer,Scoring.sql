select * from Testing;
select * from Answer;

delete from Scoring;
select * from Scoring;

#채점표 결과
create table Scoring_tmp (
	subjectID int not null,
    stu_name varchar(20),
    stu_id int not null,
	a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,
    a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,
    primary key(subjectID,stu_id)
    );

#채점결과+총점
insert into Scoring_tmp select b.subjectID, b.stu_name, b.stu_id,
(a.a01=b.a01),(a.a02=b.a02),(a.a03=b.a03),(a.a04=b.a04),(a.a05=b.a05),
(a.a06=b.a06),(a.a07=b.a07),(a.a08=b.a08),(a.a09=b.a09),(a.a10=b.a10),
(a.a11=b.a11),(a.a12=b.a12),(a.a13=b.a13),(a.a14=b.a14),(a.a15=b.a15),
(a.a16=b.a16),(a.a17=b.a17),(a.a18=b.a18),(a.a19=b.a19),(a.a20=b.a20)
from Testing as b, Answer as a where a.subjectID=b.subjectID;

insert into Scoring select *, (a01+a02+a03+a04+a05+a06+a07+a08+a09+a10+a11+a12+a13+a14+a15+a16+a17+a18+a19+a20)*5 from Scoring_tmp;