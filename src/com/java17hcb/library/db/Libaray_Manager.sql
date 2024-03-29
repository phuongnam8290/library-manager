-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema library_manager
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `library_manager` ;

-- -----------------------------------------------------
-- Schema library_manager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library_manager` DEFAULT CHARACTER SET utf8 ;
USE `library_manager` ;

-- -----------------------------------------------------
-- Table `library_manager`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`book` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`book` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `TYPE` INT(11) NOT NULL,
  `AUTHOR` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `PUBLISH_YEAR` DATETIME NOT NULL,
  `PUBLISHER` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL,
  `PRICE` DECIMAL(10,0) NOT NULL,
  `REMAIN_COPY` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`TITLE` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`staff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`staff` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`staff` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(150) NOT NULL,
  `FULLNAME` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `DATE_OF_BIRTH` DATETIME NOT NULL,
  `DIPLOMA` INT(11) NOT NULL,
  `POSITION` INT(11) NOT NULL,
  `DIVISION` INT(11) NOT NULL,
  `ADDRESS` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`book_import`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`book_import` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`book_import` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `BOOK_ID` INT(11) NOT NULL,
  `IMPORT_BY` INT(11) NOT NULL,
  `IMPORT_DATE` DATETIME NULL DEFAULT NULL,
  `COPY` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_BOOK_has_STAFF_STAFF1_idx` (`IMPORT_BY` ASC),
  INDEX `fk_BOOK_has_STAFF_BOOK1_idx` (`BOOK_ID` ASC),
  CONSTRAINT `fk_BOOK_has_STAFF_BOOK1`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `library_manager`.`book` (`ID`),
  CONSTRAINT `fk_BOOK_has_STAFF_STAFF1`
    FOREIGN KEY (`IMPORT_BY`)
    REFERENCES `library_manager`.`staff` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`library_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`library_card` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`library_card` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CREATED_BY` INT(11) NOT NULL,
  `FULLNAME` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `TYPE` INT(11) NOT NULL,
  `DATE_OF_BIRTH` DATETIME NOT NULL,
  `ADDRESS` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `CREATE_DATE` DATETIME NOT NULL,
  `EXPIRE_DATE` DATETIME NOT NULL,
  `FINES_FEE` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_LIBRARY-CARD_STAFF_idx` (`CREATED_BY` ASC),
  CONSTRAINT `fk_LIBRARY-CARD_STAFF`
    FOREIGN KEY (`CREATED_BY`)
    REFERENCES `library_manager`.`staff` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`rent_receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`rent_receipt` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`rent_receipt` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `LIBRARY_CARD_ID` INT(11) NOT NULL,
  `RENT_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RENT-RECEIPT_LIBRARY-CARD1_idx` (`LIBRARY_CARD_ID` ASC),
  CONSTRAINT `fk_RENT-RECEIPT_LIBRARY-CARD1`
    FOREIGN KEY (`LIBRARY_CARD_ID`)
    REFERENCES `library_manager`.`library_card` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`return_receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`return_receipt` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`return_receipt` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `RETURN_DATE` DATETIME NOT NULL,
  `LATE_FEE` DECIMAL(10,0) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`book_rent_receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`book_rent_receipt` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`book_rent_receipt` (
  `BOOK_ID` INT(11) NOT NULL,
  `RENT_RECEIPT_ID` INT(11) NOT NULL,
  `RETURN_RECEIPT_ID` INT(11) NULL DEFAULT NULL,
  `STATUS` INT(10) NOT NULL,
  PRIMARY KEY (`BOOK_ID`, `RENT_RECEIPT_ID`),
  INDEX `fk_BOOK_has_RENT-RECEIPT_RENT-RECEIPT1_idx` (`RENT_RECEIPT_ID` ASC),
  INDEX `fk_BOOK_has_RENT-RECEIPT_BOOK1_idx` (`BOOK_ID` ASC),
  INDEX `fk_return_receipt_id_idx` (`RETURN_RECEIPT_ID` ASC),
  CONSTRAINT `fk_BOOK_has_RENT-RECEIPT_BOOK1`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `library_manager`.`book` (`ID`),
  CONSTRAINT `fk_BOOK_has_RENT-RECEIPT_RENT-RECEIPT1`
    FOREIGN KEY (`RENT_RECEIPT_ID`)
    REFERENCES `library_manager`.`rent_receipt` (`ID`),
  CONSTRAINT `fk_return_receipt_id`
    FOREIGN KEY (`RETURN_RECEIPT_ID`)
    REFERENCES `library_manager`.`return_receipt` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`fines_receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`fines_receipt` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`fines_receipt` (
  `PAYED_BY` INT(11) NOT NULL,
  `TAKED_BY` INT(11) NOT NULL,
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CURRENT_FINES_FEE` DECIMAL(10,0) NOT NULL,
  `PAYMENT` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `fk_LIBRARY-CARD_has_STAFF_STAFF1_idx` (`TAKED_BY` ASC),
  INDEX `fk_LIBRARY-CARD_has_STAFF_LIBRARY-CARD1_idx` (`PAYED_BY` ASC),
  CONSTRAINT `fk_LIBRARY-CARD_has_STAFF_LIBRARY-CARD1`
    FOREIGN KEY (`PAYED_BY`)
    REFERENCES `library_manager`.`library_card` (`ID`),
  CONSTRAINT `fk_LIBRARY-CARD_has_STAFF_STAFF1`
    FOREIGN KEY (`TAKED_BY`)
    REFERENCES `library_manager`.`staff` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`liquidate_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`liquidate_history` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`liquidate_history` (
  `BOOK_ID` INT(11) NOT NULL,
  `LIQUIDATE_BY` INT(11) NOT NULL,
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `LIQUIDATE_DATE` DATETIME NOT NULL,
  `REASON` INT(11) NOT NULL,
  `COPY` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `fk_BOOK_has_STAFF_STAFF2_idx` (`LIQUIDATE_BY` ASC),
  INDEX `fk_BOOK_has_STAFF_BOOK2_idx` (`BOOK_ID` ASC),
  CONSTRAINT `fk_BOOK_has_STAFF_BOOK2`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `library_manager`.`book` (`ID`),
  CONSTRAINT `fk_BOOK_has_STAFF_STAFF2`
    FOREIGN KEY (`LIQUIDATE_BY`)
    REFERENCES `library_manager`.`staff` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library_manager`.`lost_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_manager`.`lost_history` ;

CREATE TABLE IF NOT EXISTS `library_manager`.`lost_history` (
  `BOOK_ID` INT(11) NOT NULL,
  `LIBRARY_CARD_ID` INT(11) NOT NULL,
  `RECORD_BY` INT(11) NOT NULL,
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `LOST_DATE` DATETIME NOT NULL,
  `FINES_FEE` DECIMAL(10,0) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `fk_BOOK_has_LIBRARY-CARD_LIBRARY-CARD1_idx` (`LIBRARY_CARD_ID` ASC),
  INDEX `fk_BOOK_has_LIBRARY-CARD_BOOK1_idx` (`BOOK_ID` ASC),
  INDEX `fk_LOST-HISTORY_STAFF1_idx` (`RECORD_BY` ASC),
  CONSTRAINT `fk_BOOK_has_LIBRARY-CARD_BOOK1`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `library_manager`.`book` (`ID`),
  CONSTRAINT `fk_BOOK_has_LIBRARY-CARD_LIBRARY-CARD1`
    FOREIGN KEY (`LIBRARY_CARD_ID`)
    REFERENCES `library_manager`.`library_card` (`ID`),
  CONSTRAINT `fk_LOST-HISTORY_STAFF1`
    FOREIGN KEY (`RECORD_BY`)
    REFERENCES `library_manager`.`staff` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
