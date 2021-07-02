#show tables;
#최단거리 찾기
#SQRT(POWER(37.386052 - latitude,2) + POWER (127.1214038 - longtitude,2)) = (select MIN(SQRT(POWER(37.386052 - latitude,2) + POWER(127.1214038 - longtitude,2)))
select number,place_addr_land,latitude,longtitude, SQRT(POWER(37.386052 - latitude,2) + POWER (127.1214038 - longtitude,2))as distance from freewifi;