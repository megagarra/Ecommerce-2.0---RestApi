USE `ecommerce-03`;
DROP procedure IF EXISTS `sps_consumidores_byMonth`;

USE `ecommerce-03`;
DROP procedure IF EXISTS `ecommerce-03`.`sps_consumidores_byMonth`;
;

DELIMITER $$
USE `ecommerce-03`$$
CREATE DEFINER=`soulcodeacad`@`%` PROCEDURE `sps_consumidores_byMonth`(
mes INT
)
BEGIN
CREATE TEMPORARY TABLE tmp_consumidores_mes AS
SELECT * FROM pessoa WHERE MONTH(data_criacao) = mes AND dtype = 'Consumidor';
SELECT *, DATE_FORMAT(data_criacao, '%d/%m/%Y') AS 'DATA CRIAÇÃO CONSUMIDOR' FROM tmp_consumidores_mes ORDER BY 'DATA_CRIACAO_CONSUMIDOR';
DROP TABLE tmp_consumidores_mes;

END$$

DELIMITER ;
;