create database kopoctc;
use kopoctc;

create database drink;

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



