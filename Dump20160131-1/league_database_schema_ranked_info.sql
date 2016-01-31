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
-- Table structure for table `ranked_info`
--

DROP TABLE IF EXISTS `ranked_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranked_info` (
  `summonerID` bigint(20) NOT NULL,
  `ranked_information_for_summoner` varchar(8000) DEFAULT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`summonerID`),
  UNIQUE KEY `summonerID_UNIQUE` (`summonerID`),
  CONSTRAINT `summonerID_FK` FOREIGN KEY (`summonerID`) REFERENCES `basic_summoner_info` (`summonerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranked_info`
--

LOCK TABLES `ranked_info` WRITE;
/*!40000 ALTER TABLE `ranked_info` DISABLE KEYS */;
INSERT INTO `ranked_info` VALUES (123,NULL,'2015-11-21 18:50:05'),(12345,'rankedInformation','2015-11-21 16:08:21'),(29438747,'{\"29438747\":[{\"entries\":[{\"division\":\"IV\",\"isFreshBlood\":false,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":71,\"miniSeries\":null,\"playerOrTeamId\":\"29438747\",\"playerOrTeamName\":\"Coaran\",\"wins\":163,\"losses\":149}],\"name\":\"Warwick\'s Blackguards\",\"queue\":\"RANKED_SOLO_5x5\",\"tier\":\"GOLD\"},{\"entries\":[{\"division\":\"II\",\"isFreshBlood\":false,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":0,\"miniSeries\":null,\"playerOrTeamId\":\"TEAM-20437ea0-bdfc-11e4-9e51-c81f66dba0e7\",\"playerOrTeamName\":\"BoostMePlis\",\"wins\":4,\"losses\":1}],\"name\":\"Jayce\'s Duelists\",\"queue\":\"RANKED_TEAM_5x5\",\"tier\":\"SILVER\"},{\"entries\":[{\"division\":\"III\",\"isFreshBlood\":false,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":0,\"miniSeries\":null,\"playerOrTeamId\":\"TEAM-48918900-f1a6-11e4-b18b-c81f66db920c\",\"playerOrTeamName\":\"gankmid420times\",\"wins\":3,\"losses\":4}],\"name\":\"Veigar\'s Spellslingers\",\"queue\":\"RANKED_TEAM_5x5\",\"tier\":\"BRONZE\"}]}','2016-01-03 20:30:45'),(37656713,'{\"37656713\":[{\"entries\":[{\"division\":\"V\",\"isFreshBlood\":true,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":17,\"miniSeries\":null,\"playerOrTeamId\":\"37656713\",\"playerOrTeamName\":\"kaibai\",\"wins\":22,\"losses\":14}],\"name\":\"Riven\'s Zealots\",\"queue\":\"RANKED_SOLO_5x5\",\"tier\":\"DIAMOND\"}]}','2016-01-31 00:13:24'),(38584682,'{\"38584682\":[{\"entries\":[{\"division\":\"I\",\"isFreshBlood\":true,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":0,\"miniSeries\":null,\"playerOrTeamId\":\"38584682\",\"playerOrTeamName\":\"ripolly\",\"wins\":5,\"losses\":5}],\"name\":\"Alistar\'s Tricksters\",\"queue\":\"RANKED_SOLO_5x5\",\"tier\":\"SILVER\"}]}','2016-01-23 23:10:15'),(39653835,'{\"39653835\":[{\"entries\":[{\"division\":\"V\",\"isFreshBlood\":false,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":19,\"miniSeries\":null,\"playerOrTeamId\":\"39653835\",\"playerOrTeamName\":\"ALoadOfBarnacles\",\"wins\":108,\"losses\":110}],\"name\":\"Nasus\'s Soldiers\",\"queue\":\"RANKED_SOLO_5x5\",\"tier\":\"PLATINUM\"},{\"entries\":[{\"division\":\"III\",\"isFreshBlood\":false,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":0,\"miniSeries\":null,\"playerOrTeamId\":\"TEAM-6c8fc440-a8ac-11e4-b65b-c81f66db920c\",\"playerOrTeamName\":\"TopBlokesNeverToke\",\"wins\":20,\"losses\":18}],\"name\":\"Jayce\'s Duelists\",\"queue\":\"RANKED_TEAM_5x5\",\"tier\":\"SILVER\"}]}','2016-01-18 23:46:28'),(41626810,'{\"41626810\":[{\"entries\":[{\"division\":\"II\",\"isFreshBlood\":true,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":0,\"miniSeries\":null,\"playerOrTeamId\":\"41626810\",\"playerOrTeamName\":\"BigBattyFam\",\"wins\":8,\"losses\":8}],\"name\":\"Rumble\'s Scouts\",\"queue\":\"RANKED_SOLO_5x5\",\"tier\":\"BRONZE\"}]}','2016-01-21 21:19:06'),(50831249,'{\"50831249\":[{\"entries\":[{\"division\":\"IV\",\"isFreshBlood\":false,\"isHotStreak\":false,\"isInactive\":false,\"isVeteran\":false,\"leaguePoints\":35,\"miniSeries\":null,\"playerOrTeamId\":\"50831249\",\"playerOrTeamName\":\"PastelPancake\",\"wins\":20,\"losses\":23}],\"name\":\"Graves\'s Gladiators\",\"queue\":\"RANKED_SOLO_5x5\",\"tier\":\"SILVER\"}]}','2016-01-30 14:22:35');
/*!40000 ALTER TABLE `ranked_info` ENABLE KEYS */;
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
