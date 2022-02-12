-- MySQL Script generated by MySQL Workbench
-- Wed Feb  2 01:19:40 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paymentsapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paymentsapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymentsapp` DEFAULT CHARACTER SET utf8 ;
USE `paymentsapp` ;

-- -----------------------------------------------------
-- Table `paymentsapp`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymentsapp`.`roles` (
  `r_id` INT NOT NULL AUTO_INCREMENT,
  `r_title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`r_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsapp`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymentsapp`.`users` (
  `u_id` INT NOT NULL AUTO_INCREMENT,
  `u_login` VARCHAR(100) NOT NULL,
  `u_password` VARCHAR(200) NOT NULL,
  `u_name` VARCHAR(100) NOT NULL,
  `u_surname` VARCHAR(100) NOT NULL,
  `u_passport` VARCHAR(100) NOT NULL,
  `roles_r_id` INT NOT NULL,
  PRIMARY KEY (`u_id`),
  INDEX `fk_users_roles1_idx` (`roles_r_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roles_r_id`)
    REFERENCES `paymentsapp`.`roles` (`r_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsapp`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymentsapp`.`accounts` (
  `a_id` INT NOT NULL AUTO_INCREMENT,
  `a_number` VARCHAR(45) NOT NULL,
  `a_balance` DOUBLE NOT NULL,
  `a_openning_date` DATE NOT NULL,
  `a_status` CHAR(1) NOT NULL,
  `users_u_id` INT NOT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE INDEX `a_number_UNIQUE` (`a_number` ASC) VISIBLE,
  INDEX `fk_accounts_users1_idx` (`users_u_id` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_users1`
    FOREIGN KEY (`users_u_id`)
    REFERENCES `paymentsapp`.`users` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsapp`.`transaction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymentsapp`.`transaction_type` (
  `tt_id` INT NOT NULL AUTO_INCREMENT,
  `tt_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tt_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsapp`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymentsapp`.`transactions` (
  `t_id` INT NOT NULL AUTO_INCREMENT,
  `t_date` DATE NOT NULL,
  `t_amount` DOUBLE NOT NULL,
  `t_from_account` VARCHAR(45) NOT NULL,
  `t_before_acc_balance` VARCHAR(45) NULL,
  `t_after_acc_balance` VARCHAR(45) NULL,
  `t_to_account` VARCHAR(45) NOT NULL,
  `users_u_id` INT NOT NULL,
  `transaction_type_tt_id` INT NOT NULL,
  PRIMARY KEY (`t_id`),
  INDEX `fk_transactions_users1_idx` (`users_u_id` ASC) VISIBLE,
  INDEX `fk_transactions_transaction_type1_idx` (`transaction_type_tt_id` ASC) VISIBLE,
  CONSTRAINT `fk_transactions_users1`
    FOREIGN KEY (`users_u_id`)
    REFERENCES `paymentsapp`.`users` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_transaction_type1`
    FOREIGN KEY (`transaction_type_tt_id`)
    REFERENCES `paymentsapp`.`transaction_type` (`tt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsapp`.`cash_requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paymentsapp`.`cash_requests` (
  `cr_id` INT NOT NULL AUTO_INCREMENT,
  `cr_date` DATE NOT NULL,
  `cr_amount` DOUBLE NOT NULL,
  `cr_status` CHAR(1) NOT NULL,
  `accounts_a_id` INT NOT NULL,
  PRIMARY KEY (`cr_id`),
  INDEX `fk_cash_requests_accounts1_idx` (`accounts_a_id` ASC) VISIBLE,
  CONSTRAINT `fk_cash_requests_accounts1`
    FOREIGN KEY (`accounts_a_id`)
    REFERENCES `paymentsapp`.`accounts` (`a_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
