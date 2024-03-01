-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: teamstatsfox
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `opponents_tournments`
--

DROP TABLE IF EXISTS `opponents_tournments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opponents_tournments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `opponent_id` bigint DEFAULT NULL,
  `tournment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opponents_tournments`
--

LOCK TABLES `opponents_tournments` WRITE;
/*!40000 ALTER TABLE `opponents_tournments` DISABLE KEYS */;
INSERT INTO `opponents_tournments` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(16,12,9),(17,13,9),(18,14,9),(19,15,9),(20,16,9),(21,17,9),(22,7,2),(23,11,2),(24,18,2),(25,19,2),(26,20,6),(27,21,6),(28,22,6),(29,23,6),(30,24,6),(31,66,9),(32,31,3),(33,32,3),(34,33,3),(35,15,3),(36,24,3),(37,25,3),(38,34,3),(39,26,3),(40,35,3),(41,36,3),(42,32,3),(43,37,3),(44,38,4),(45,14,4),(46,39,4),(47,40,4),(48,15,4),(49,13,4),(50,41,4),(51,41,5),(52,13,5),(53,43,5),(54,44,5),(55,45,5),(56,46,5),(57,47,5),(58,14,5),(59,48,5),(61,15,5),(62,49,7),(63,50,7),(64,51,7),(65,52,7),(66,26,7),(67,53,7),(68,54,7),(69,55,7),(70,32,7),(71,56,7),(72,57,7),(73,58,7),(80,65,8),(81,60,8),(82,61,8),(83,62,8),(84,14,8),(85,63,8),(86,3,8),(87,26,8),(88,38,8),(89,64,8),(90,67,8),(91,42,5),(154,6,17),(155,15,17),(156,69,17),(157,70,17);
/*!40000 ALTER TABLE `opponents_tournments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-01 15:30:46
