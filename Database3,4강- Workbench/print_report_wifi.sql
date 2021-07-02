drop procedure if exists  print_report_wifi;
delimiter $$
create procedure print_report_wifi(_page integer, _result integer)
begin
	declare _start integer;
	declare _end integer;
    declare _home_latitude integer;
	declare _home_longtitude integer;
    
	if _page < 1 then
    set _page =1;
    
    else if _page > (7401/_result) then
    set _page = (7401/_result);
    
    end if;
    end if;
    set _start = (_page-1)*_result;
    set _home_latitude = 37.38677917766775;
    set _home_longtitude = 127.1262991000785;
 
	select number,place_addr_land,latitude,longtitude, SQRT(POWER(_home_latitude - latitude,2) + POWER (_home_longtitude - longtitude,2))as distance from freewifi limit _start,_result;
    
end $$
delimiter ;