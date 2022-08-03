USE `ecommerce-03`;
DROP procedure IF EXISTS `SPS_CRIACAO_PRODUTOS_MES`;

USE `ecommerce-03`;
DROP procedure IF EXISTS `ecommerce-03`.`SPS_CRIACAO_PRODUTOS_MES`;
;

DELIMITER $$
USE `ecommerce-03`$$
CREATE DEFINER=`soulcodeacad`@`%` PROCEDURE `SPS_CRIACAO_PRODUTOS_MES`(mes int)
BEGIN
CREATE TEMPORARY TABLE tmp_produtos_mes AS
SELECT *  FROM PRODUTOS WHERE MONTH(data_criacao) = mes;
SELECT *, DATE_FORMAT(data_criacao, '%d/%m/%Y') as 'DATA CRIAÇÃO PRODUTO' FROM tmp_produtos_mes order by 'DATA_CRIACAO_PRODUTO';
DROP TABLE tmp_produtos_mes;
END$$

DELIMITER ;
;
