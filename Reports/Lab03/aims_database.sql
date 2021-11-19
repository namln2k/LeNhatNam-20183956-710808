-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema aims
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema aims
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aims` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `aims` ;

-- -----------------------------------------------------
-- Table `aims`.`Media_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Media_NamLN` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `quantity` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `value` INT NOT NULL,
  `imageUrl` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`Book_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Book_NamLN` (
  `id` INT NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `coverType` VARCHAR(45) NOT NULL,
  `publisher` VARCHAR(45) NOT NULL,
  `publishDate` DATETIME NOT NULL,
  `numOfPages` INT NOT NULL,
  `language` VARCHAR(45) NOT NULL,
  `bookCategory` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Book_Media1`
    FOREIGN KEY (`id`)
    REFERENCES `aims`.`Media_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`Card_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Card_NamLN` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cardCode` VARCHAR(15) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `cvvCode` VARCHAR(3) NOT NULL,
  `dateExpired` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`CD_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`CD_NamLN` (
  `id` INT NOT NULL,
  `artist` VARCHAR(45) NOT NULL,
  `recordLabel` VARCHAR(45) NOT NULL,
  `musicType` VARCHAR(45) NOT NULL,
  `releasedDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_CD_Media1`
    FOREIGN KEY (`id`)
    REFERENCES `aims`.`Media_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`DeleveryInfo_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`DeleveryInfo_NamLN` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `province` VARCHAR(45) NULL DEFAULT NULL,
  `instructions` VARCHAR(200) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`DVD_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`DVD_NamLN` (
  `id` INT NOT NULL,
  `discType` VARCHAR(45) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `runtime` INT NOT NULL,
  `studio` VARCHAR(45) NOT NULL,
  `subtitle` VARCHAR(45) NOT NULL,
  `releasedDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_DVD_Media1`
    FOREIGN KEY (`id`)
    REFERENCES `aims`.`Media_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`Order_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Order_NamLN` (
  `id` INT NOT NULL,
  `shippingFees` VARCHAR(45) NULL DEFAULT NULL,
  `deleveryInfoId` INT NOT NULL,
  PRIMARY KEY (`id`, `deleveryInfoId`),
  INDEX `idx_fk_Order_DeleveryInfo1` (`deleveryInfoId` ASC) VISIBLE,
  CONSTRAINT `fk_Order_DeleveryInfo1`
    FOREIGN KEY (`deleveryInfoId`)
    REFERENCES `aims`.`DeleveryInfo_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`Invoice_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`Invoice_NamLN` (
  `id` INT NOT NULL,
  `totalAmount` INT NOT NULL,
  `orderId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_fk_Invoice_Order1` (`orderId` ASC) VISIBLE,
  CONSTRAINT `fk_Invoice_Order1`
    FOREIGN KEY (`orderId`)
    REFERENCES `aims`.`Order_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`OrderMedia_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`OrderMedia_NamLN` (
  `orderID` INT NOT NULL,
  `price` INT NOT NULL,
  `quantity` INT NOT NULL,
  `mediaId` INT NOT NULL,
  PRIMARY KEY (`orderID`, `mediaId`),
  INDEX `idx_fk_ordermedia_order` (`orderID` ASC) VISIBLE,
  INDEX `idx_fk_OrderMedia_Media1` (`mediaId` ASC) VISIBLE,
  CONSTRAINT `fk_OrderMedia_Media1`
    FOREIGN KEY (`mediaId`)
    REFERENCES `aims`.`Media_NamLN` (`id`),
  CONSTRAINT `fk_ordermedia_order`
    FOREIGN KEY (`orderID`)
    REFERENCES `aims`.`Order_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `aims`.`PaymentTransaction_NamLN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aims`.`PaymentTransaction_NamLN` (
  `id` INT NOT NULL,
  `createAt` DATETIME NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `method` VARCHAR(45) NULL DEFAULT NULL,
  `cardId` INT NOT NULL,
  `invoiceId` INT NOT NULL,
  PRIMARY KEY (`id`, `cardId`, `invoiceId`),
  INDEX `idx_fk_PaymentTransaction_Card1` (`cardId` ASC) VISIBLE,
  INDEX `idx_fk_PaymentTransaction_Invoice1` (`invoiceId` ASC) VISIBLE,
  CONSTRAINT `fk_PaymentTransaction_Card1`
    FOREIGN KEY (`cardId`)
    REFERENCES `aims`.`Card_NamLN` (`id`),
  CONSTRAINT `fk_PaymentTransaction_Invoice1`
    FOREIGN KEY (`invoiceId`)
    REFERENCES `aims`.`Invoice_NamLN` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
