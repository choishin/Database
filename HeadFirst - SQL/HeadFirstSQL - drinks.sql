use head_first;

drop table easy_drinks;
create table easy_drinks (
drink_name varchar(100) not null,
main varchar(100) not null,
amount1 float not null,
second varchar(100) not null,
amount2 float not null,
directions varchar(1000) not null
);

insert into easy_drinks values ('Blackthorn','tonic water', 1.5, 'pineapple juice', 1 ,'stir with ice, strain into cocktail glass with lemon twist');
insert into easy_drinks values ('Blue Moon','soda', 1.5, 'blueberry juice', 0.75 ,'stir with ice, strain into cocktail glass with lemon twist');
insert into easy_drinks values ('Oh my Gosh','peach nectar', 1, 'pineapple juice', 1 ,'stir with ice, strain into shot glass');
insert into easy_drinks values ('Lime Fizz','sprite', 1.5, 'lime juice', 0.75 ,'stir with ice, strain into cocktail glass');
insert into easy_drinks values ('Kiss on the Lips','cherry juice', 7, 'apricot juice', 1 ,'serve over ice with straw');
insert into easy_drinks values ('Hot Gold','peach nectar', 3, 'orange juice', 6 ,'pour hot orange juice in mug and add peach nectar');
insert into easy_drinks values ('Lone Tree','soda', 1.5, 'cherry juice', 0.75 ,'stir with ice, strain into cocktail glass');
insert into easy_drinks values ('Greyhound','soda', 1.5, 'grapefruit juice', 5 ,'serve over ice, stir well');
insert into easy_drinks values ('Indian Summer','apple juice', 2, 'hot tea', 6 ,'add juice to mug and top off with hot tea');
insert into easy_drinks values ('Bull Frog','iced tea', 1.5, 'lemonade', 5 ,'serve over ice with lime slice');
insert into easy_drinks values ('Soda and It','soda', 2, 'grape juice', 1 ,'shake in cocktail glass, no ice');

select * from easy_drinks where main = 'sprite';
-- varchar 타입인 것을 ''<-없이 찾을 수는 없음
select * from easy_drinks where main = soda;
select * from easy_drinks where amount2 = 6;
-- ""<-이걸로 varchar 타입을 찾을 수 있음
select * from easy_drinks where second = "orange juice";                                  
select * from easy_drinks where amount1 < 1.5;
-- 숫자형 데이터에 '<-붙이는건 문제가 안 됨.
select * from easy_drinks where amount2 <'1';
-- soda보다 정렬순으로 뒤에있는 것들을 찾아줌.
select * from easy_drinks where main > 'soda';
select * from easy_drinks where amount1 ='1.5';

-- 위의 테이블을 정규화 해 볼 것임...
select * from easy_drinks;

-- easy_drinks에 primary key (index) 지정해주기
alter table easy_drinks
add column id int not null auto_increment primary key;

alter table easy_drinks
modify column id int first;

-- main, second 를 따로 빼줄 것임
drop table info_main;
create table info_main (
	id int not null,
	main_id int not null,
	amount float,
    foreign key (main_id) references main (main_id),
    foreign key (id) references easy_drinks (id)
);

SET foreign_key_checks =0;
drop table main;
create table main (
	main_id int not null auto_increment,
    main varchar(100),
    primary key (main_id)
);

drop table info_second;
create table info_second (
	id int not null,
	second_id int not null,
	amount float,
    foreign key (id) references easy_drinks (id),
    foreign key (second_id) references _second (second_id)
);

drop table _second;
create table _second (
	second_id int not null auto_increment,
    _second varchar(100),
    primary key (second_id)
);

-- main 컬럼에서 중복을 제거하고, main 테이블에 넣어줌
select distinct main from easy_drinks;
insert main(main) select distinct main from easy_drinks;

-- 잘 들어갔나 확인하기
select * from easy_drinks;
select * from main;
select * from info_main;

-- easy_drinks와 main을 이용해서 info_main에 값 넣기
select a.id, b.main_id, a.amount1 from easy_drinks as a, main as b where a.main = b.main;  
insert info_main select a.id, b.main_id, a.amount1 from easy_drinks as a, main as b where a.main = b.main; 

-- 위와 같은 방법으로 _second에서 중복된 값 제거하고, _second 테이블에 넣기
select distinct second from easy_drinks;
insert _second(_second) select distinct _second from easy_drinks;
select * from _second;

-- second가 예약어라서 바꿔줌
alter table easy_drinks
change column second _second varchar(100);

-- easy_drinks와 _second를 이용해서 info_second에 값 넣기
select a.id, a._second, b._second, b.second_id, a.amount2 from easy_drinks as a, _second as b where a._second = b._second;  
select a.id, b.second_id, a.amount2 from easy_drinks as a, _second as b where a._second = b._second;  
insert info_second select a.id, b.second_id, a.amount2 from easy_drinks as a, _second as b where a._second = b._second;  

-- 다 잘 들어갔나 확인 후
select * from easy_drinks;
select * from main;
select * from info_main;
select * from info_second;
select * from _second;

-- 만일 칵테일별 main과 second가 얼마나 어떻게 들어가는지 알고 싶다면....
select a.id, a.drink_name, (select d.main from main as d where b.main_id = d.main_id), b.amount, (select e._second from _second as e where c.second_id = e.second_id), c.amount, a.directions from easy_drinks as a, info_main as b, info_second as c where a.id= b.id and b.id = c.id;  

-- 필요없는 컬럼 지우기
alter table easy_drinks
drop column main;

alter table easy_drinks
drop column amount1;

alter table easy_drinks
drop column _second;

alter table easy_drinks
drop column amount2;