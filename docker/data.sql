CREATE DATABASE IF NOT EXISTS `card_creator_directory` ;
USE `card_creator_directory` ;

DROP TABLE IF EXISTS card;


CREATE TABLE card (
     id int NOT NULL AUTO_INCREMENT,
     first_name varchar(50) NOT NULL,
     last_name varchar(50) NOT NULL,
     status varchar(50) NOT NULL,
     oib varchar(11) NOT NULL,
     created_at DATETIME NOT NULL,
     PRIMARY KEY (id)
);