SELECT CONCAT(
alojamiento_id,CONCAT('-',CONCAT(
numOfertas,CONCAT('-',numOcupacion))))
from( 
Select alojamiento_id,count(alojamiento_id) AS numOfertas 
from oferta group by alojamiento_id) 
natural join( 
SELECT alojamiento_id,count(alojamiento_id) AS numOcupacion 
FROM oferta 
where reserva_id is not null 
group by alojamiento_id);