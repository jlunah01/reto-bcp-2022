-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: exchangeratedb
-- ------------------------------------------------------
-- Server version	5.7.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `exchange_rate`
--

DROP TABLE IF EXISTS `exchange_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exchange_rate` (
  `exchange_rate_id` int(11) NOT NULL AUTO_INCREMENT,
  `exchange_rate_currency` varchar(4) NOT NULL,
  `exchange_rate` decimal(20,10) DEFAULT '0.0000000000',
  `type` varchar(1) NOT NULL,
  `origin_currency` varchar(4) NOT NULL,
  `destination_currency` varchar(4) NOT NULL,
  `ind_del` varchar(1) DEFAULT '0',
  `fec_reg` datetime DEFAULT NULL,
  `fec_act` datetime DEFAULT NULL,
  `usu_reg` varchar(50) DEFAULT NULL,
  `usu_act` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`exchange_rate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchange_rate`
--

LOCK TABLES `exchange_rate` WRITE;
/*!40000 ALTER TABLE `exchange_rate` DISABLE KEYS */;
INSERT INTO `exchange_rate` VALUES (1,'PEN',4.0085000000,'C','PEN','USD','1','2022-03-22 19:48:28',NULL,'ADMIN',NULL),(2,'PEN',4.1095000000,'V','PEN','USD','1','2022-03-22 19:49:28',NULL,'ADMIN',NULL),(8,'PEN',3.2000000000,'C','PEN','JPN','1','2022-03-25 00:00:00','2022-03-25 00:00:00',NULL,'jlunah'),(9,'PEN',2.9000000000,'C','PEN','CAN','1','2022-03-25 00:00:00','2022-03-25 00:00:00',NULL,'jlunah'),(10,'PEN',2.0000000000,'C','PEN','ORG','1','2022-03-25 00:00:00','2022-03-25 00:00:00','jlunah','jlunah');
/*!40000 ALTER TABLE `exchange_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'exchangeratedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-25 15:04:48
