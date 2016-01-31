-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: league_database_schema
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `basic_summoner_info`
--

DROP TABLE IF EXISTS `basic_summoner_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basic_summoner_info` (
  `count` int(11) NOT NULL AUTO_INCREMENT,
  `summonerID` bigint(50) NOT NULL,
  `summoner_name` varchar(255) NOT NULL,
  `summoner_icon` bigint(50) NOT NULL,
  `revision_date` bigint(50) NOT NULL,
  `summoner_level` int(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`count`,`summonerID`),
  UNIQUE KEY `count` (`count`),
  UNIQUE KEY `summonerID` (`summonerID`),
  UNIQUE KEY `summoner_name` (`summoner_name`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_summoner_info`
--

LOCK TABLES `basic_summoner_info` WRITE;
/*!40000 ALTER TABLE `basic_summoner_info` DISABLE KEYS */;
INSERT INTO `basic_summoner_info` VALUES (1,123,'name',123,123124123,30,'2015-12-07 22:22:17'),(3,12345,'secondName',50,80808080,30,'2015-12-07 22:22:17'),(11,39328503,'Korabalus',666,1443483445000,30,'2015-12-22 01:40:26'),(12,33611731,'Calham',983,1451187716000,30,'2015-12-27 18:29:54'),(19,29438747,'Coaran',982,1451851346000,30,'2016-01-03 20:30:45'),(52,39653835,'ALoadOfBarnacles',666,1453160644000,30,'2016-01-18 23:46:28'),(55,41626810,'BigBattyFam',26,1453409116000,30,'2016-01-21 21:19:06'),(57,19195640,'Alod',26,1297465131000,11,'2016-01-21 21:20:47'),(58,37656713,'kaibai',749,1453408363000,30,'2016-01-21 21:20:51'),(59,38584682,'ripolly',786,1453585728000,30,'2016-01-23 23:10:15'),(60,155767,'L O L',7,1443795620000,30,'2016-01-24 18:10:41'),(61,50831249,'pastelpancake',936,1454162036000,30,'2016-01-30 14:22:20'),(62,44402295,'hhhhhhhhhhh',981,1454183786000,30,'2016-01-30 23:34:10'),(63,23159458,'hhhhhhhhh',8,1326533593000,1,'2016-01-30 23:53:26');
/*!40000 ALTER TABLE `basic_summoner_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-31 16:42:02