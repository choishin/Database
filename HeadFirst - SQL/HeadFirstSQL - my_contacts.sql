use head_first;

drop table my_contacts;
create table my_contacts (
	contact_id int not null,
    last_name varchar(20),
    first_name varchar(20),
    phone varchar(20),
    email varchar(50),
    gender char(1),
    birthday date,
    prof_id int not null,
    zip_code int not null,
    status_id int not null,
    constraint pk_contact_id
    primary key (contact_id),
    constraint profession_profession_id_fk
    foreign key (prof_id) references profession(profession_id),
	constraint zip_code_zip_code_fk
    foreign key (zip_code) references zip_code(zip_code),
	constraint _status_status_id_fk
    foreign key (status_id) references _status(status_id)
);

-- 관심사는 한 사람이 여러개를 가질 수가 있음. 그래서  多:多 관계를 피하기 위해 관계 테이블을 만들어 준것.
drop table contact_interest;
create table contact_interest (
	contact_id int not null,
    interest_id int not null,
    constraint my_contacts_contact_id_fk
    foreign key (contact_id) references my_contacts(contact_id) ON UPDATE CASCADE ON DELETE CASCADE,
	constraint interests_interest_id_fk
    foreign key (interest_id) references interests (interest_id) ON UPDATE CASCADE ON DELETE CASCADE,
    primary key (contact_id, interest_id)
);

drop table interests;
create table interests (
	interest_id int not null auto_increment,
    interest varchar(20),
	constraint pk_interest_id
    primary key (interest_id)
); 

-- 찾는것 역시 마찬가지
drop table contact_seeking;
create table contact_seeking (
	contact_id int not null,
	seeking_id int not null,
    foreign key (contact_id) references my_contacts(contact_id) ON UPDATE CASCADE ON DELETE CASCADE,
	constraint seeking_seeking_id_fk
    foreign key (seeking_id) references seeking(seeking_id) ON UPDATE CASCADE ON DELETE CASCADE,
    primary key (contact_id, seeking_id)
);

drop table seeking;
create table seeking (
	seeking_id int not null auto_increment,
    seeking varchar(20),
    constraint pk_seeking_id
    primary key(seeking_id)
);

drop table profession;
create table profession (
	profession_id int not null auto_increment,
    profession varchar(20),
	constraint pk_profession_id
    primary key(profession_id)
);

drop table zip_code;
create table zip_code (
	zip_code int not null,
    city varchar(20),
    state char(4),
	constraint pk_zipcode
    primary key(zip_code)
);

drop table _status;
create table _status (
	status_id int not null auto_increment,
    _status varchar(20),
	constraint pk_status_id
    primary key(status_id)
);

select * from my_contacts;
select * from contact_interest;
select * from contact_seeking;
select * from interests;
select * from seeking;
select * from profession;
select * from zip_code;
select * from _status;

-- 데이터 넣기
insert into _status(_status) values ('single');
insert into _status(_status) values ('married');
insert into seeking(seeking) values ('man');
insert into seeking(seeking) values ('woman');
insert into profession(profession) values ('programmer');
insert into profession(profession) values ('teacher');
insert into profession(profession) values ('lawyer');
insert into profession(profession) values ('lancher');
insert into profession(profession) values ('veterinarian');
insert into profession(profession) values ('Artist');
insert into profession(profession) values ('Professor');
insert into profession(profession) values ('Chef');
insert into interests(interest) values ('books');
insert into interests(interest) values ('pets');
insert into interests(interest) values ('sports');
insert into interests(interest) values ('animals');
insert into interests(interest) values ('horseback');
insert into interests(interest) values ('movies');
insert into interests(interest) values ('mystery novels');
insert into interests(interest) values ('hiking');
insert into interests(interest) values ('Sailing');
insert into interests(interest) values ('Cooking');
insert into interests(interest) values ('dogs');
insert into interests(interest) values ('spelunking');
insert into interests(interest) values ('reading');
insert into zip_code values(1010, 'Austin','TX');
insert into zip_code values(1011, 'Round Rock','TX');
insert into zip_code values(1012, 'Salt Lake City','UT');
insert into zip_code values(1013, 'New york city','NY');
insert into zip_code values(1014, 'Boston','MA');

-- 나이젤의 데이터 넣어보기
insert into my_contacts values(341, 'Moore','Nigel','5552311111','nigelmoore@ranchersrule.com','M', date_format('1975-08-28','%Y-%m-%d'),4,1010,1);
insert into contact_interest values (341,5);
insert into contact_interest values (341,6);
insert into contact_interest values (341,7);
select * from interests where interest = 'movies'; 
select * from seeking;
insert into contact_seeking values (341,2);

-- 데이터 조회 해보기
select * from my_contacts;
select a.contact_id, a.interest_id, b.interest from contact_interest as a , interests as b where a.interest_id=b.interest_id ;

-- 피오레 데이터 넣어보기
insert into my_contacts values(1854,'Fiore','Carla','5557894855','cfiore@fioreanimalclinic.com','F',date_format('1974-01-07','%Y-%m-%d'),5,1011,1);
select * from interests where interest = 'hiking'; 
insert into contact_interest value(1854,6);
insert into contact_interest value(1854,7);
insert into contact_interest value(1854,5);
insert into contact_interest value(1854,8);
insert into contact_interest value(1854,9);
insert into contact_seeking values (1854,1);

-- 아래에 있는 세포스트잇의 정보가 테이블 내에 있다고 가장하고 결과 테이블이 어떠한 모습일지 그려보세요.
insert into my_contacts values (1,'Joan', 'Everett','5555559870','jeverette@mightygumball.net','F',date_format('1978-03-04','%Y-%m-%d'),6,1012,1);
select * from interests where interest='cooking';
insert into contact_interest values (1,10);
insert into contact_interest values (1,9);
insert into contact_interest values (1,11);

insert into my_contacts values (2,'Paul', 'Singh','5555558222','ps@tikibeanlounge.com','M',date_format('1980-10-12','%Y-%m-%d'),7,1014,2);
select * from my_contacts where contact_id = 1;
select * from profession where profession='chef';
select * from interests where interest='cooking';
insert into contact_interest values (2,12);
insert into contact_interest values (2,13);

insert into my_contacts values (3,'Tara', 'Baldwin','5555553432','tara@breakneckpizza.com','F',date_format('1970-1-9','%Y-%m-%d'),8,1014,2);
insert into contact_interest values (3,7);
insert into contact_interest values (3,14);
insert into contact_interest values (3,11);

select mc.last_name, mc.first_name,p.profession from my_contacts as mc INNER JOIN profession as p ON mc.prof_id = p.profession_id;

-- 동등조인 예제
-- mycontacts에 있는 각 사람의 이메일 주소와 직업을 반환하는 쿼리 
select a.email, a.prof_id, b.profession  from my_contacts as a, profession as b where a.prof_id = b.profession_id;

-- mycontacts에 있는 각 사람의 이름(first_name), 성(last_name) 그리고 결혼여부를 반환하는 쿼리 
select a.first_name, a.last_name, b._status from my_contacts as a, _status as b where a.status_id = b.status_id;

-- mycontacts에 있는 각 사람의 이름(first_name), 성(last_name) 그리고 주(state)를 반환하는 쿼리 
select a.first_name, a.last_name, b.state from my_contacts as a, zip_code as b where a.zip_code = b.zip_code; 

-- my_contacts에 있는 각 사람의 이메일 주소와 직업을 반환하는 쿼리
select my_contacts.email, my_contacts.zip_code, zip_code.city,zip_code.state from my_contacts Inner Join zip_code on my_contacts.zip_code = zip_code.zip_code;

-- my_contacts에 있는 각 사람의 이름, 성 그리고 그 사람의 결혼여부가 아닌 결혼여부를 반환하는 쿼리
select my_contacts.last_name, my_contacts.first_name, _status._status from my_contacts Inner Join _status on my_contacts.status_id <> _status.status_id;

-- my_contacts에 있는 각 사람의 이름,성 그리고 주를 반환하는 쿼리
select my_contacts.last_name, my_contacts.first_name, zip_code.state from my_contacts Inner Join zip_code on my_contacts.zip_code = zip_code.zip_code;

