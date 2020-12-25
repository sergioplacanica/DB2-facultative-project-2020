-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: db_questionnaire
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `accesslog`
--

DROP TABLE IF EXISTS `accesslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesslog` (
  `UserID` int NOT NULL,
  `Access_time` datetime NOT NULL,
  PRIMARY KEY (`UserID`,`Access_time`),
  KEY `UserID` (`UserID`),
  KEY `Access_time` (`Access_time`),
  CONSTRAINT `Accesslog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Accesslog_ibfk_2` FOREIGN KEY (`Access_time`) REFERENCES `accesstime` (`Access_time`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesslog`
--

LOCK TABLES `accesslog` WRITE;
/*!40000 ALTER TABLE `accesslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `accesslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accesstime`
--

DROP TABLE IF EXISTS `accesstime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesstime` (
  `Access_time` datetime NOT NULL,
  PRIMARY KEY (`Access_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesstime`
--

LOCK TABLES `accesstime` WRITE;
/*!40000 ALTER TABLE `accesstime` DISABLE KEYS */;
/*!40000 ALTER TABLE `accesstime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `UserID` int NOT NULL,
  `ProductID` int NOT NULL,
  `QuestionID` int NOT NULL,
  `Answer_text` text NOT NULL,
  `Offensive` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`,`ProductID`,`QuestionID`),
  KEY `QuestionID` (`QuestionID`),
  CONSTRAINT `Contain_ibfk_1` FOREIGN KEY (`UserID`, `ProductID`) REFERENCES `questionnaire` (`UserID`, `ProductID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Contain_ibfk_2` FOREIGN KEY (`QuestionID`) REFERENCES `marketingquestion` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,2,2,'si',0),(2,1,3,'12',0),(2,2,1,'si',0);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marketingquestion`
--

DROP TABLE IF EXISTS `marketingquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marketingquestion` (
  `QuestionID` int NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) NOT NULL,
  `ProductID` int NOT NULL,
  PRIMARY KEY (`QuestionID`),
  KEY `product_id` (`ProductID`),
  CONSTRAINT `product_id_fk` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketingquestion`
--

LOCK TABLES `marketingquestion` WRITE;
/*!40000 ALTER TABLE `marketingquestion` DISABLE KEYS */;
INSERT INTO `marketingquestion` VALUES (1,'Ti è piaciuto il prodotto?',1),(2,'Lo consiglieresti ad un amico?',1),(3,'Quanto ore lo usi al giorno?',1),(4,'“Do you know the product',1),(5,'Do you like the product?',8),(6,'Have you tried it?',8),(7,'Are you happy with the product?',10),(8,'It is too expensive for you?',10);
/*!40000 ALTER TABLE `marketingquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offensiveword`
--

DROP TABLE IF EXISTS `offensiveword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offensiveword` (
  `Word` varchar(255) NOT NULL,
  PRIMARY KEY (`Word`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offensiveword`
--

LOCK TABLES `offensiveword` WRITE;
/*!40000 ALTER TABLE `offensiveword` DISABLE KEYS */;
INSERT INTO `offensiveword` VALUES ('cazzo'),('culo');
/*!40000 ALTER TABLE `offensiveword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(42) NOT NULL,
  `Image` varchar(255) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `Date_UNIQUE` (`Date`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Playstation 5','/serverurl/Ps5.jpeg','2020-12-12'),(2,'GoPro Hero 9','/serverurl/GoPRO9.jpeg','2020-12-13'),(3,'lamborghini','ampl.exe','2020-12-14'),(4,'Ferrari','heuristics.pdf','2020-12-15'),(5,'Pagani','ILP.pdf','2020-12-16'),(6,'Pagani','day by day activities (on line) (1).pdf','2020-12-19'),(7,'Ferrari','2015.02.09_THEORY.pdf','2020-12-24'),(8,'Fiat','05-4-JPA-JPQL.pdf','2020-12-20'),(10,'Mazda','statement of the transportation problem.pdf','2020-12-22');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaire` (
  `UserID` int NOT NULL,
  `ProductID` int NOT NULL,
  `Date` date NOT NULL,
  `Age` int DEFAULT NULL,
  `Sex` set('male','female') DEFAULT NULL,
  `Expertise_level` set('low','medium','high') DEFAULT NULL,
  PRIMARY KEY (`UserID`,`ProductID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `Questionnaire_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Questionnaire_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` VALUES (1,2,'2020-11-03',15,'','low'),(2,1,'2020-11-02',NULL,NULL,NULL),(2,2,'2020-11-18',NULL,NULL,NULL),(8,1,'2020-12-12',28,'male','low'),(9,1,'2020-12-12',15,'female','low'),(11,1,'2020-12-12',16,'male','low');
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `UserID` int NOT NULL,
  `ProductID` int NOT NULL,
  `Review_Text` varchar(255) NOT NULL,
  PRIMARY KEY (`UserID`,`ProductID`),
  KEY `UserID` (`UserID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `Review_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Review_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'Meglio la xbox'),(1,2,'bella,la consiglio');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(40) NOT NULL,
  `Password` varchar(42) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT '0',
  `Blocked` tinyint(1) NOT NULL DEFAULT '0',
  `Points` int DEFAULT '0',
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user1','*668425423DB5193AF921380129F465A6425216D0','lorenzo1.amata@mail.polimi.it',1,0,7),(2,'user2','*DC52755F3C09F5923046BD42AFA76BD1D80DF2E9','lorenzo2.amata@mail.polimi.it',0,1,7),(3,'user3','*40C3E7D386A2FADBDF69ACEBE7AA4DC3C723D798','lorenzo3.amata@mail.polimi.it',0,0,4),(4,'user4','*F97AEB38B3275C06D822FC9341A2151642C81988','lorenzo4.amata@mail.polimi.it',1,1,3),(8,'deep','deep98','deep98@gmail.com',0,0,25),(9,'Antonio','deep123412','tony45@yahoo.it',0,0,21),(11,'ronny67','asfgas','sostancozii@mcdonald.it',0,0,45),(15,'deep98','km','tony45@yahoo.it',0,0,24),(23,'andrea123','asdfqas','andrea123@gmail.com',0,0,3),(31,'alessandro','ale1234','ale@yahoo.it',0,0,4),(32,'alex123','asd123as','alexa@gasda.it',0,0,78);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-23 19:15:34
