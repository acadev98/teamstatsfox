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
-- Table structure for table `tournments`
--

DROP TABLE IF EXISTS `tournments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tournments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournments`
--

LOCK TABLES `tournments` WRITE;
/*!40000 ALTER TABLE `tournments` DISABLE KEYS */;
INSERT INTO `tournments` VALUES (1,_binary '\0','Torneo Clausura 2018 - Nazario Torneos','CL2018 - NAZARIO','2018-08-04'),(2,_binary '','Torneo de Verano 2019 - Nazario Torneos','TV2019 - NAZARIO','2019-02-09'),(3,_binary '','Torneo Clausura 2021 - CI FUTBOL','CL2021 - CI FUTBOL','2021-10-31'),(4,_binary '','Torneo Apertura 2022 - TVN TORNEOS','AP2022 - TVN TORNEOS','2022-05-22'),(5,_binary '','Torneo Clausura 2022 - TVN TORNEOS','CL2022 - TVN TORNEOS','2022-08-21'),(6,_binary '','Torneo de Verano 2023 - CI FUTBOL','TV2023 - CI FUTBOL','2023-01-15'),(7,_binary '','Torneo Apertura 2023 - CI FUTBOL','AP2023 - CI FUTBOL','2023-03-12'),(8,_binary '','Torneo Clausura 2023 - TVN TORNEOS','CL2023 - TVN TORNEOS','2023-08-20'),(9,_binary '','Torneo de Verano 2024 - Torneos Virgen Niñas','TV2024 - TVN TORNEOS','2024-01-14'),(17,_binary '','Amistosos','AMISTOSOS','2018-08-04');
/*!40000 ALTER TABLE `tournments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-01 15:30:45
