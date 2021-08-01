use head_first;

drop table super_heroes;
create table super_heroes (
	name varchar(50) not null,
    porwer varchar(50) not null,
    weakness varchar(50),
    city varchar(20) not null,
    country varchar(20) not null,
    arch_enemy varchar(20) not null,
    initials varchar(10) not null,
    primary key (name,porwer)
);

insert into super_heroes values ('Super Trashman', 'Cleans quickly', 'bleach','Gotham','US','Verminator','ST');
insert into super_heroes values ('The Broker', 'Make money from nothing', '','New York','US','Mister Taxman','TB');
insert into super_heroes values ('Super Guy', 'Files', 'birds','Metropolis','US','Super Fella','SG');
insert into super_heroes values ('Wonder Waiter', 'Never forgets an order', 'insects','Paris','France','All You Can Eat Girl','WW');
insert into super_heroes values ('Dirtyman', 'Creates dust storms', 'bleach','Tulsa','US','Hoover','D');
insert into super_heroes values ('Super Guy', 'Super Strength', 'aluminum','Metropolis','US','Badman','SG');
insert into super_heroes values ('Furious Woman', 'Get really, really angry', '','Rome','Italy','The Therapist','FW');
insert into super_heroes values ('The Toad', 'Tongue of justice', 'insects','London','England','Heron','T');
insert into super_heroes values ('Librarian', 'Can find anything', 'children','Springfield','US','Chaos Creep','L');
insert into super_heroes values ('Goose Girl', 'Files', '','Minneapolis','US','The Quilter','GG');
insert into super_heroes values ('Stick Man', 'stands in for humans', 'hang man','London','England','Eraserman','SM');

-- 복합키의 이점 : 이름이 중복되어도 가진 힘이 다르면 서로 유일함을 유지할 수 있음. 
-- 복합키 똑같이는 넣을 수 없음.
insert into super_heroes values ('Super Trashman', 'Cleans quickly', 'bleach','Gotham','US','Verminator','ST');
-- 하지만 복합키 중 하나가 달라지면 넣을 수 있음.
insert into super_heroes values ('Super Trashman', 'Wipe perfect', 'bleach','Gotham','US','Verminator','ST');


drop table books;
create table books (
	author varchar(20) not null,
    title varchar(50) not null,
    copyright int not null,
    pub_id int not null,
    pub_city varchar(50),
    primary key (author, title)
);

insert into books values ('John Deere','Easy Being Green',1930,2,'New York');
insert into books values ('Fred Mertz','I Hate Lucy',1968,5,'Boston');
insert into books values ('Lassie','Help Timmy!',1950,3,'San Francisco');
insert into books values ('Timmy','Lassie, Calm Down',1951,1,'New York');

select * from books;

-- 이 테이블에서 author과 title copyright 는 서로가 바뀌면 값이 따라서 바뀌어야 하는 종속관계를 가지고 있음
-- 하지만 pub_id와 pub_city는 딱히 큰 관계가 없음. 



