drop table fish_info;
create table fish_info (
	common varchar(200) not null,
    species varchar(100) not null,
    location varchar(100) not null,
    weight varchar(100) not null
);

insert into fish_info values ('bass,largemouth','M.salmoides','Montgomery Lake,GA','22 lb 4 oz');
insert into fish_info values ('walleye','S.vitres','Old Hockory Lake,TN','25 lb 0 oz');
insert into fish_info values ('trout,cutthroat','O.Clarki','Pyramid Lake,NV','41 lb 0 oz');
insert into fish_info values ('perch,yellow','P.Flavescens','Boardentown, NJ','4 lb 3 oz');
insert into fish_info values ('bluegill','L.Macrochirus','Ketona Lake,AL','4 lb 12 oz');
insert into fish_info values ('gar, longnose','L.Osseus','Trinity River,TX','50 lb 5 oz');
insert into fish_info values ('crappie,white','P.annularis','Enid Dam,MS','5 lb 3 oz');
insert into fish_info values ('pickerel,grass','E.americanus','Dewart Lake,IN','1 lb 0 oz');
insert into fish_info values ('goldfish','C.auratus','Lake Hodges,CA','6 lb 10 oz');
insert into fish_info values ('salmon,chinook','O.Tshawytscha','Kenai River,AK','97 lb 4 oz');

drop table fish_records;
create table fish_records (
	first_name varchar(100),
    last_name varchar(100),
    common varchar(200),
    location varchar(100),
    state char(2),
    weight varchar(100),
    date varchar(100)
);

insert into fish_records values ('George','Perry','bass, largemouth', 'Montgomery Lake', 'GA','22 lb 4 oz', date_format('1932/6/2','%m/%d/%Y'));
insert into fish_records values ('Mabry','Harper','Walleye', 'Old Hockory Lake', 'TN','25 lb 0 oz', date_format('1960/8/2','%m/%d/%Y'));
insert into fish_records values ('John','Skimmerhorn','trout,cutthroat', 'Pyramid Lake', 'NV','41 lb 0 oz', date_format('1925/12/1','%m/%d/%Y'));
insert into fish_records values ('C.C.','Abbot','perch,yellow', 'Boardentown', 'NJ','4 lb 3 oz', date_format('1865/5/1','%m/%d/%Y'));
insert into fish_records values ('T.S.','Hudson','bluegill', 'Ketona Lake', 'AL','4 lb 12 oz', date_format('1950/4/9','%m/%d/%Y'));
insert into fish_records values ('Townsend','Miller','gar,longnose', 'Trinity River', 'TX','50 lb 5 oz', date_format('1954//7/30','%m/%d/%Y'));
insert into fish_records values ('Fred','Bright','crappie,white', 'Enid Dam', 'MS','5 lb 3 oz', date_format('1957/7/31','%m/%d/%Y'));
insert into fish_records values ('Mike','Berg','pickerel,grass', 'Dewart Lake', 'IN','1 lb 0 oz', date_format('1990/6/9','%m/%d/%Y'));
insert into fish_records values ('Florentino','Abena','goldfish', 'Lake Hodges', 'CA','6 lb 10 oz', date_format('1996/4/17','%m/%d/%Y'));
insert into fish_records values ('Les','Anderson','salmon,chinook', 'Kenai River', 'AK','97 lb 4 oz', date_format('1985/5/17','%m/%d/%Y'));

select * from fish_info where location like'%NJ';
select * from fish_records where state='NJ';



