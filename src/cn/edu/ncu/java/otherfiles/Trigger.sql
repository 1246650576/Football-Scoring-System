-- 建立触发器
-- 1.插入球队触发插入积分榜
create trigger teaminsert
after insert on team
for each row -- 这句话在mysql是固定的
insert into scoreofteam(ranking,changes,teamname,matchs,win,even,beaten,goal,lost,net,avergoal,averlost,avernet,averpoint) values(0,0,new.name,0,0,0,0,0,0,0,0,0,0,0);
$
-- 2.删除球队触发删除射手榜、球员、比赛进程、积分榜
-- delimiter $
create trigger teamdel
before delete on team
for each row
begin
    delete from shooterlist where teamname=old.name;
    delete from player where team=old.name;
    delete from schedule where hometeam=old.name or visitingteam=old.name;
    delete from scoreofteam where teamname=old.name;
end $
-- delimiter ;

-- 3.插入比赛进程时调整积分榜
-- delimiter $
create trigger schedule_insert before insert on schedule for each row
begin

-- 如果积分榜中没有主队或客队的信息，则添加一条
if not exists(select teamname from scoreofteam where teamname=new.hometeam) then
	insert into scoreofteam values(0,0,new.hometeam,0,0,0,0,0,0,0,0,0,0,0);
end if;
if not exists(select teamname from scoreofteam where teamname=new.visitingteam) then
	insert into scoreofteam values(0,0,new.visitingteam,0,0,0,0,0,0,0,0,0,0,0);
end if;

-- 主队获胜
if new.homescore>new.visitingscore then
		update scoreofteam
		set
			matchs=matchs+1,
			win=win+1,
			goal=goal+new.homescore,
			lost=lost+new.visitingscore,
			net=net+(new.homescore-new.visitingscore),
			avergoal = (goal * 1.0) / matchs,
			averlost = (lost * 1.0)/ matchs,
			avernet = (net * 1.0)/ matchs,
			averpoint = (win * 3.0 + even)/ matchs
		where teamname=new.hometeam;
		update scoreofteam
		set
			matchs=matchs+1,
			beaten = beaten+1,
			goal = goal+new.visitingscore,
			lost = lost+new.homescore,
			net=net+(new.visitingscore-new.homescore),
			avergoal = (goal * 1.0) / matchs,
			averlost = (lost * 1.0)/ matchs,
			avernet = (net * 1.0)/ matchs
		where teamname=new.visitingteam;
-- 两队打平
elseif new.homescore=new.visitingscore then
		update scoreofteam
		set
			matchs=matchs+1,
			even=even+1,
			goal=goal+new.homescore,
			lost=lost+new.visitingscore,
			avergoal = (goal * 1.0) / matchs,
			averlost = (lost * 1.0)/ matchs,
			averpoint = (win * 3.0 + even)/ matchs
		where teamname=new.hometeam;
		update scoreofteam
		set
			matchs=matchs+1,
			even=even+1,
			goal=goal+new.visitingscore,
			lost=lost+new.homescore,
			avergoal = (goal * 1.0) / matchs,
			averlost = (lost * 1.0)/ matchs,
			averpoint = (win * 3.0 + even)/ matchs
		where teamname=new.visitingteam;
-- 主队失利
else
		update scoreofteam
		set
			matchs=matchs+1,
			beaten=beaten+1,
			goal=goal+new.homescore,
			lost=lost+new.visitingscore,
			net=net+(new.homescore-new.visitingscore),
			avergoal = (goal * 1.0) / matchs,
			averlost = (lost * 1.0)/ matchs,
			avernet = (net * 1.0)/ matchs
		where teamname=new.hometeam;
		update scoreofteam
		set
			matchs=matchs+1,
			win=win+1,
			goal = goal+new.visitingscore,
			lost = lost+new.homescore,
			net=net+(new.visitingscore-new.homescore),
			avergoal = (goal * 1.0) / matchs,
			averlost = (lost * 1.0)/ matchs,
			avernet = (net * 1.0)/ matchs,
			averpoint = (win * 3.0 + even)/ matchs
		where teamname=new.visitingteam;
end if;

-- 利用游标对名次进行处理
if new.hometeam in(select distinct team from player where gender='女') then
	call updateRanking_score_for_female();
elseif new.hometeam in(select name from team where college=NULL) then
	call updateRanking_score_for_adult();
else
	call updateRanking_score_for_male();
end if;

end $
-- delimiter ;

-- 利用游标对名次进行处理
-- delimiter $
drop procedure if exists updateRanking_score $
create procedure updateRanking_score_for_female()
begin
	-- declare 必须在其它语句之前。
	declare temp_teamname varchar(20);
	declare temp_ranking int;
	declare done int default false;-- 创建结束标志变量
	declare scoreofteam_cursor cursor for select teamname from scoreofteam where teamname in(select distinct team from player where gender='女')  order by averpoint desc,net desc,goal desc;
	declare continue HANDLER for not found set done = true;-- 指定游标循环结束时的返回值
	set temp_ranking=0;
	-- 按照积分、净胜球、进球数的降序进行排名
	open scoreofteam_cursor;
	fetch scoreofteam_cursor into temp_teamname;  -- 将游标向下移1行，获取的数据放入之前定义的变量,@teamname中
		while(not done) do
  			set temp_ranking=temp_ranking+1;
  			update scoreofteam set changes = ranking-temp_ranking, ranking=temp_ranking where teamname=temp_teamname;
  			fetch scoreofteam_cursor into temp_teamname;
 		end while;
	close scoreofteam_cursor;
end $
-- delimiter ;

-- delimiter $
drop procedure if exists updateRanking_score $
create procedure updateRanking_score_for_male()
begin
	-- declare 必须在其它语句之前。
	declare temp_teamname varchar(20);
	declare temp_ranking int;
	declare done int default false;-- 创建结束标志变量

	declare scoreofteam_cursor cursor for select teamname from scoreofteam
	where teamname not in(select distinct team from player where gender='女') and teamname not in(select name from team where college='无')
	order by averpoint desc,net desc,goal desc;

	declare continue HANDLER for not found set done = true;-- 指定游标循环结束时的返回值
	set temp_ranking=0;
	-- 按照积分、净胜球、进球数的降序进行排名
	open scoreofteam_cursor;
	fetch scoreofteam_cursor into temp_teamname;  -- 将游标向下移1行，获取的数据放入之前定义的变量,@teamname中
		while(not done) do
  			set temp_ranking=temp_ranking+1;
  			update scoreofteam set changes = ranking-temp_ranking, ranking=temp_ranking where teamname=temp_teamname;
  			fetch scoreofteam_cursor into temp_teamname;
 		end while;
	close scoreofteam_cursor;
end $
-- delimiter ;

-- delimiter $
drop procedure if exists updateRanking_score $
create procedure updateRanking_score_for_adult()
begin
	-- declare 必须在其它语句之前。
	declare temp_teamname varchar(20);
	declare temp_ranking int;
	declare done int default false;-- 创建结束标志变量
	declare scoreofteam_cursor cursor for select teamname from scoreofteam where teamname in(select name from team where college='无') order by averpoint desc,net desc,goal desc;
	declare continue HANDLER for not found set done = true;-- 指定游标循环结束时的返回值
	set temp_ranking=0;
	-- 按照积分、净胜球、进球数的降序进行排名
	open scoreofteam_cursor;
	fetch scoreofteam_cursor into temp_teamname;  -- 将游标向下移1行，获取的数据放入之前定义的变量,@teamname中
		while(not done) do
  			set temp_ranking=temp_ranking+1;
  			update scoreofteam set changes = ranking-temp_ranking, ranking=temp_ranking where teamname=temp_teamname;
  			fetch scoreofteam_cursor into temp_teamname;
 		end while;
	close scoreofteam_cursor;
end $
-- delimiter ;

-- 4.插入进球时调整射手榜(进球信息，更新排名)
-- delimiter $
create trigger goal_insert after insert on goalofplayer for each row
begin

-- 如果射手榜中没有该球员的信息，则插入一条对应记录
if not exists(select name from shooterlist where teamname=new.teamname and numbers=new.numbers) then
	insert into shooterlist values(0,(select name from player where team=new.teamname and numbers=new.numbers),new.teamname,new.numbers,0,0,0);
end if;

-- 进球数加一
update shooterlist set goals=goals+1 where teamname=new.teamname and numbers=new.numbers;

if new.teamname in(select distinct team from player where gender='女') then
	call updateRanking_shooter_for_female();
elseif new.teamname in(select name from team where college=NULL) then
	call updateRanking_shooter_for_adult();
else
	call updateRanking_shooter_for_male();
end if;

end $
-- delimiter ;

-- 利用游标对名次进行处理
-- delimiter $
drop procedure if exists updateRanking_shooter $
create procedure updateRanking_shooter_for_female()
begin
	declare temp_ranking int;
	declare temp_teamname varchar(20);
	declare temp_numbers varchar(20);
	declare done int default false;-- 创建结束标志变量
	declare shooterlist_cursor cursor for select teamname,numbers from shooterlist where teamname in(select distinct team from player where gender='女') order by goals desc,name;
	declare continue HANDLER for not found set done = true;-- 指定游标循环结束时的返回值
	set temp_ranking=0;
	open shooterlist_cursor;
	fetch shooterlist_cursor into temp_teamname,temp_numbers;
		while(not done) do
  			set temp_ranking=temp_ranking+1;
  			update shooterlist set ranking=temp_ranking where teamname=temp_teamname and numbers=temp_numbers;
  			fetch shooterlist_cursor into temp_teamname,temp_numbers;
 		end while;
 	close shooterlist_cursor;
end $
-- delimiter ;

-- delimiter $
drop procedure if exists updateRanking_shooter $
create procedure updateRanking_shooter_for_adult()
begin
	declare temp_ranking int;
	declare temp_teamname varchar(20);
	declare temp_numbers varchar(20);
	declare done int default false;-- 创建结束标志变量
	declare shooterlist_cursor cursor for select teamname,numbers from shooterlist where teamname in(select name from team where college='无') order by goals desc,name;
	declare continue HANDLER for not found set done = true;-- 指定游标循环结束时的返回值
	set temp_ranking=0;
	open shooterlist_cursor;
	fetch shooterlist_cursor into temp_teamname,temp_numbers;
		while(not done) do
  			set temp_ranking=temp_ranking+1;
  			update shooterlist set ranking=temp_ranking where teamname=temp_teamname and numbers=temp_numbers;
  			fetch shooterlist_cursor into temp_teamname,temp_numbers;
 		end while;
 	close shooterlist_cursor;
end $
-- delimiter ;

-- delimiter $
drop procedure if exists updateRanking_shooter $
create procedure updateRanking_shooter_for_male()
begin
	declare temp_ranking int;
	declare temp_teamname varchar(20);
	declare temp_numbers varchar(20);
	declare done int default false;-- 创建结束标志变量
	declare shooterlist_cursor cursor for select teamname,numbers from shooterlist where teamname not in(select name from team where college='无') and teamname not in(select distinct team from player where gender='女') order by goals desc,name;
	declare continue HANDLER for not found set done = true;-- 指定游标循环结束时的返回值
	set temp_ranking=0;
	open shooterlist_cursor;
	fetch shooterlist_cursor into temp_teamname,temp_numbers;
		while(not done) do
  			set temp_ranking=temp_ranking+1;
  			update shooterlist set ranking=temp_ranking where teamname=temp_teamname and numbers=temp_numbers;
  			fetch shooterlist_cursor into temp_teamname,temp_numbers;
 		end while;
 	close shooterlist_cursor;
end $
-- delimiter ;

-- 6.插入比赛处罚信息时调整射手榜
-- delimiter $
create trigger judge_insert after insert on judge for each row
begin

-- 如果射手榜中没有该球员的信息，则插入一条对应记录
if not exists(select name from shooterlist where teamname=new.teamname and numbers=new.numbers) then
	insert into shooterlist values(0,(select name from player where team=new.teamname and numbers=new.numbers),new.teamname,new.numbers,0,0,0);
end if;

-- 更新红黄牌
update shooterlist set redcard=new.redcard+redcard, yellowcard=new.yellowcard+yellowcard where teamname=new.teamname and numbers=new.numbers;

end $
-- delimiter ;