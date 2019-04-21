-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: library_manager
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TYPE` int(11) NOT NULL,
  `AUTHOR` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PUBLISH_YEAR` datetime NOT NULL,
  `PUBLISHER` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PRICE` decimal(10,0) NOT NULL,
  `REMAIN_COPY` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_import`
--

DROP TABLE IF EXISTS `book_import`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book_import` (
  `BOOK_ID` int(11) NOT NULL,
  `IMPORT_BY` int(11) NOT NULL,
  `IMPORT_DATE` datetime DEFAULT NULL,
  `COPY` int(11) DEFAULT NULL,
  PRIMARY KEY (`BOOK_ID`,`IMPORT_BY`),
  KEY `fk_BOOK_has_STAFF_STAFF1_idx` (`IMPORT_BY`),
  KEY `fk_BOOK_has_STAFF_BOOK1_idx` (`BOOK_ID`),
  CONSTRAINT `fk_BOOK_has_STAFF_BOOK1` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`ID`),
  CONSTRAINT `fk_BOOK_has_STAFF_STAFF1` FOREIGN KEY (`IMPORT_BY`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `book_rent_receipt`
--

DROP TABLE IF EXISTS `book_rent_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book_rent_receipt` (
  `BOOK_ID` int(11) NOT NULL,
  `RENT-RECEIPT_ID` int(11) NOT NULL,
  `STATUS` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY (`BOOK_ID`,`RENT-RECEIPT_ID`),
  KEY `fk_BOOK_has_RENT-RECEIPT_RENT-RECEIPT1_idx` (`RENT-RECEIPT_ID`),
  KEY `fk_BOOK_has_RENT-RECEIPT_BOOK1_idx` (`BOOK_ID`),
  CONSTRAINT `fk_BOOK_has_RENT-RECEIPT_BOOK1` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`ID`),
  CONSTRAINT `fk_BOOK_has_RENT-RECEIPT_RENT-RECEIPT1` FOREIGN KEY (`RENT-RECEIPT_ID`) REFERENCES `rent_receipt` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fines_receipt`
--

DROP TABLE IF EXISTS `fines_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fines_receipt` (
  `PAYED_BY` int(11) NOT NULL,
  `TAKED_BY` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CURRENT_FINES_FEE` decimal(10,0) NOT NULL,
  `PAYMENT` decimal(10,0) NOT NULL,
  PRIMARY KEY (`PAYED_BY`,`TAKED_BY`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_LIBRARY-CARD_has_STAFF_STAFF1_idx` (`TAKED_BY`),
  KEY `fk_LIBRARY-CARD_has_STAFF_LIBRARY-CARD1_idx` (`PAYED_BY`),
  CONSTRAINT `fk_LIBRARY-CARD_has_STAFF_LIBRARY-CARD1` FOREIGN KEY (`PAYED_BY`) REFERENCES `library_card` (`ID`),
  CONSTRAINT `fk_LIBRARY-CARD_has_STAFF_STAFF1` FOREIGN KEY (`TAKED_BY`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `library_card`
--

DROP TABLE IF EXISTS `library_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `library_card` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_BY` int(11) NOT NULL,
  `FULLNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TYPE` int(11) NOT NULL,
  `DATE_OF_BIRTH` datetime NOT NULL,
  `ADDRESS` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `EXPIRE_DATE` datetime NOT NULL,
  `FINES_FEE` decimal(10,0) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_LIBRARY-CARD_STAFF_idx` (`CREATED_BY`),
  CONSTRAINT `fk_LIBRARY-CARD_STAFF` FOREIGN KEY (`CREATED_BY`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `liquidate_history`
--

DROP TABLE IF EXISTS `liquidate_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `liquidate_history` (
  `BOOK_ID` int(11) NOT NULL,
  `LIQUIDATE_BY` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIQUIDATE_DATE` datetime NOT NULL,
  `REASON` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COPY` int(11) NOT NULL,
  PRIMARY KEY (`BOOK_ID`,`LIQUIDATE_BY`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_BOOK_has_STAFF_STAFF2_idx` (`LIQUIDATE_BY`),
  KEY `fk_BOOK_has_STAFF_BOOK2_idx` (`BOOK_ID`),
  CONSTRAINT `fk_BOOK_has_STAFF_BOOK2` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`ID`),
  CONSTRAINT `fk_BOOK_has_STAFF_STAFF2` FOREIGN KEY (`LIQUIDATE_BY`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lost_history`
--

DROP TABLE IF EXISTS `lost_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lost_history` (
  `BOOK_ID` int(11) NOT NULL,
  `LIBRARY-CARD_ID` int(11) NOT NULL,
  `RECORD_BY` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOST_DATE` datetime NOT NULL,
  `FINES_FEE` decimal(10,0) unsigned zerofill NOT NULL,
  PRIMARY KEY (`BOOK_ID`,`LIBRARY-CARD_ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_BOOK_has_LIBRARY-CARD_LIBRARY-CARD1_idx` (`LIBRARY-CARD_ID`),
  KEY `fk_BOOK_has_LIBRARY-CARD_BOOK1_idx` (`BOOK_ID`),
  KEY `fk_LOST-HISTORY_STAFF1_idx` (`RECORD_BY`),
  CONSTRAINT `fk_BOOK_has_LIBRARY-CARD_BOOK1` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`ID`),
  CONSTRAINT `fk_BOOK_has_LIBRARY-CARD_LIBRARY-CARD1` FOREIGN KEY (`LIBRARY-CARD_ID`) REFERENCES `library_card` (`ID`),
  CONSTRAINT `fk_LOST-HISTORY_STAFF1` FOREIGN KEY (`RECORD_BY`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rent_receipt`
--

DROP TABLE IF EXISTS `rent_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent_receipt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIBRARY-CARD_ID` int(11) NOT NULL,
  `RENT_DATE` datetime NOT NULL,
  `STATUS` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_RENT-RECEIPT_LIBRARY-CARD1_idx` (`LIBRARY-CARD_ID`),
  CONSTRAINT `fk_RENT-RECEIPT_LIBRARY-CARD1` FOREIGN KEY (`LIBRARY-CARD_ID`) REFERENCES `library_card` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `return_receipt`
--

DROP TABLE IF EXISTS `return_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `return_receipt` (
  `RENT-RECEIPT_ID` int(11) NOT NULL,
  `RETURN_DATE` datetime NOT NULL,
  `LATE_FEE` decimal(10,0) unsigned zerofill NOT NULL,
  `TOTAL_FEE` decimal(10,0) unsigned zerofill NOT NULL,
  PRIMARY KEY (`RENT-RECEIPT_ID`),
  CONSTRAINT `fk_RETURN-RECEIPT_RENT-RECEIPT1` FOREIGN KEY (`RENT-RECEIPT_ID`) REFERENCES `rent_receipt` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(150) NOT NULL,
  `FULLNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATE_OF_BIRTH` datetime NOT NULL,
  `DIPLOMA` int(11) NOT NULL,
  `POSITION` int(11) NOT NULL,
  `DIVISION` int(11) NOT NULL,
  `ADDRESS` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PHONE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-21 10:06:01
