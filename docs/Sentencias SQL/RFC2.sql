SELECT CONCAT(
operador_id,CONCAT('-',suma)) 
FROM(SELECT Operador_ID,SUM(valor_total)AS SUMA 
FROM reserva ofe 
JOIN (SELECT reserva_id,alojamiento_id  
FROM oferta 
where reserva_id is not null 
group by reserva_id, alojamiento_id) temp on temp.reserva_id=ofe.id 
JOIN alojamiento on temp.alojamiento_id=alojamiento.id 
GROUP BY operador_id);