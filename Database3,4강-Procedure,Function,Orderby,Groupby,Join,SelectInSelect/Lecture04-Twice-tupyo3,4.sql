#득표수 확인하기
select
(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1) as '니연',
(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2) as '정연',
(select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3) as '모모',
(select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4) as '사나',
(select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5) as '지효',
(select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6) as '미나',
(select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7) as '다현',
(select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8) as '채영',
(select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9) as '쯔위',
(select count(*) from tupyo2 where (not kiho1=kiho2) and (not kiho1=kiho3) and (not kiho2=kiho3)) as '총합',
(select count(*) from tupyo2 where (kiho1=kiho2 and not kiho1=kiho3) or (kiho1=kiho3 and not kiho1=kiho2) or (kiho2=kiho3 and not kiho1=kiho2)) as '2중복',
(select count(*) from tupyo2 where kiho1=kiho2 and kiho1=kiho3 and kiho2=kiho3) as '3중복';

#3개 모두 다르게 고른 사람, 2개 중복되게 고른 사람, 3개 모두 같은 사람을 뽑은 사람 구하기
select * from tupyo2 where not kiho1=kiho2 and not kiho1=kiho3 and not kiho2=kiho3;
select * from tupyo2 where (kiho1=kiho2 and not kiho1=kiho3) or (kiho1=kiho3 and not kiho1=kiho2) or (kiho2=kiho3 and not kiho1=kiho2);
select * from tupyo2 where kiho1=kiho2 and kiho1=kiho3 and kiho2=kiho3;