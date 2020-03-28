SELECT * FROM (
select CONCAT(
alojamiento_id,CONCAT('-', count(*))) 
from oferta 
where Reserva_id is not null 
group by alojamiento_id 
order by Count(*) DESC) 
WHERE rownum <= 20;