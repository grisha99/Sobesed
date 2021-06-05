-- Задание 1
-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

select f.name as film_name1, SEC_TO_TIME(se.begin * 60) as film_begin1, SEC_TO_TIME(f.duration * 60) as film_duration1, SEC_TO_TIME((se.begin + f.duration) * 60) as film_end1,
        fi.name as film_name2, SEC_TO_TIME(sea.begin * 60) as film_begin2, SEC_TO_TIME(f.duration * 60) as film_duration2
from shedule as sh
left join seance as se on sh.seance_id=se.id
left join films as f on sh.film_id=f.id
inner join seance as sea on sea.begin < (se.begin + f.duration) and sea.begin > se.begin
left join shedule as she on she.seance_id = sea.id
left join films as fi on fi.id = she.film_id
order by se.begin;

-- РЕЗУЛЬТАТ
-- film_name1	        film_begin1	    film_duration1	film_end1	film_name2	    film_begin2	    film_duration2
-- Один дома	        13:40:00	    01:30:00	    15:10:00	Форсаж	        14:50:00	    01:30:00
-- На гребне волны	    18:50:00	    01:30:00	    20:20:00	Один дома	    20:00:00	    01:30:00


-- Задание 2
-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

select f.name as film_name1, SEC_TO_TIME(se.begin * 60) as film_begin1, SEC_TO_TIME(f.duration * 60) as film_duration1,
SEC_TO_TIME(sea.begin * 60) as film_begin2, (sea.begin - (se.begin + f.duration)) as pause
from shedule as sh
left join seance as se on sh.seance_id=se.id
left join films as f on sh.film_id=f.id
inner join seance as sea on sea.begin >= (se.begin + f.duration + 30) and se.begin =
(select seance.begin from seance where seance.begin < (sea.begin) order by seance.begin desc limit 1)
order by pause desc;

--РЕЗУЛЬТАТ
-- film_name1	        film_begin1	    film_duration1	film_begin2	    pause
-- Тачки-2	            17:10:00	    01:00:00	    18:50:00	    40
-- На гребне волны	    11:40:00	    01:30:00	    13:40:00	    30


-- Задание 3
-- список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

(select f.name as fim_name, count(*) as people_count, sum(sh.price) as price, (count(*) / sea_count.sea_cnt) as people_average from shedule as sh
left join seance as se on se.id = sh.seance_id
left join films as f on f.id = sh.film_id
left join tickets as t on t.schedule_id = sh.id
left join
		(
			select f.id, count(*) as sea_cnt from films as f
			left join shedule as sh on sh.film_id = f.id
			group by f.id
		)
	as sea_count on sea_count.id = f.id
group by f.id)
union all
(select 'Итого:', sum(amount.people_count), sum(amount.price), sum(amount.people_average) from
	(
		select f.name as fim_name, count(*) as people_count, sum(sh.price) as price, (count(*) / sea_count.sea_cnt + 1) as people_average from shedule as sh
		left join seance as se on se.id = sh.seance_id
		left join films as f on f.id = sh.film_id
		left join tickets as t on t.schedule_id = sh.id
		left join
				(select f.id, count(*) as sea_cnt from films as f
				left join shedule as sh on sh.film_id = f.id
				group by f.id)
			as sea_count on sea_count.id = f.id
		group by f.id
    )
as amount)
order by price;

-- РЕЗУЛЬТАТ
-- fim_name	            people_count	price	people_average
-- Форсаж	            3	            1050	1.5000
-- Тачки-2	            5	            1200	2.5000
-- Один дома	        5	            1320	1.6667
-- На гребне волны	    6	            1840	2.0000
-- Итого:	            19	            5410	11.6667


-- Задание 4
-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00
-- (сколько посетителей пришло с 9 до 15 часов и т.д.).

select case when se.begin between 540 and 900
              then "Сенс с 9:00 до 15:00"
			when se.begin between 900 and 1080
              then "Сенс с 15:00 до 18:00"
			when se.begin between 1080 and 1260
              then "Сенс с 18:00 до 21:00"
			else    "Сенс с 21:00 до 00:00"
      end as time_between, count(*) as people_count, sum(price) as sum_price
from shedule as sh
left join seance as se on se.id = sh.seance_id
left join tickets as t on t.schedule_id = sh.id
group by time_between;

-- РЕЗУЛЬТАТ
-- time_between	            people_count	sum_price
-- Сенс с 9:00 до 15:00	    12	            2870
-- Сенс с 15:00 до 18:00	2	            600
-- Сенс с 18:00 до 21:00	3	            1040
-- Сенс с 21:00 до 00:00	2	            900



