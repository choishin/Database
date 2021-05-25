#투표데이터 지우기
delete from typyo2;
#투표 데이터 생성
call insert_tupyo2(1000);
#테이블 보기
select * from tupyo2;
#update 명령어로 내용 바꾸기
update tupyo2 set kiho1 = replace (kiho1, '9', '쯔위');
update tupyo2 set kiho2 = replace (kiho2, '9', '쯔위');
update tupyo2 set kiho3 = replace (kiho3, '9', '쯔위');
select age, kiho1 as 투표1, kiho2 as 투표2, kiho3 as 투표3 from tupyo2;
#select in select 로 바꾸기
select (select name from hubo as a where a.kiho=b.kiho1) as 투표1,
(select name from hubo as a where a.kiho=b.kiho2) as 투표2,
(select name from hubo as a where a.kiho=b.kiho3) as 투표3,
age from tupyo2 as b;

#join으로 바꾸기
select a.name, a.name, a.name, b.age from tupyo2 as b, hubo as a where a.kiho=b.kiho1, a.kiho=b.kiho2, a.kiho=b.kiho3; 
select h1.name as 투표1, h2.name as 투표2, h3.name as 투표3, b.age from tupyo2 as b, hubo as h1, hubo as h2, hubo as h3 where h1.kiho=b.kiho1 and h2.kiho=b.kiho2 and h3.kiho=b.kiho3;