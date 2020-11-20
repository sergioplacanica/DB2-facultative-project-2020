CREATE DATABASE IF NOT EXISTS db_project;
USE db_project;


DROP TABLE IF EXISTS users;
CREATE TABLE users (
    userID int NOT NULL AUTO_INCREMENT,
    email varchar(320) NOT NULL UNIQUE,
    username varchar(64) NOT NULL UNIQUE, 
    pass varchar(255) NOT NULL,
    blocked boolean NOT NULL DEFAULT 0,
    PRIMARY KEY (userID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS products;
CREATE TABLE products (
    productID int NOT NULL AUTO_INCREMENT,
    imageURI varchar(255),
    PRIMARY KEY (productID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS productReviews;
CREATE TABLE productReviews (
    reviewID int NOT NULL AUTO_INCREMENT,
    customerID int NOT NULL REFERENCES users(userID),
    productID int NOT NULL,
    reviewText varchar(1000),
    reviewVote tinyint NOT NULL,
    PRIMARY KEY (reviewID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS offensiveWords;
CREATE TABLE offensiveWords (
    wordID int NOT NULL AUTO_INCREMENT,
    word varchar(50) NOT NULL,
    PRIMARY KEY (wordID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS productOfTheDay;
CREATE TABLE productOfTheDay (
    productOfTheDayID int NOT NULL AUTO_INCREMENT,
    productID int NOT NULL REFERENCES products(productID),
    programmedDate date NOT NULL UNIQUE,
    PRIMARY KEY (productOfTheDayID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS accessLog;
CREATE TABLE accessLog (
    logID int NOT NULL AUTO_INCREMENT,
    userID int NOT NULL REFERENCES users(userID),
    logTime datetime NOT NULL,
    PRIMARY KEY (logID),
    CONSTRAINT singleLogin UNIQUE (userID, logTime)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
	adminID int NOT NULL AUTO_INCREMENT,
    email varchar(320) NOT NULL UNIQUE,
    username varchar(64) NOT NULL UNIQUE,
    pass varchar(255) NOT NULL,
    PRIMARY KEY (adminID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS questions;
CREATE TABLE questions (
	questionID int NOT NULL AUTO_INCREMENT,
    description varchar(255) NOT NULL,
    PRIMARY KEY (questionID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS questionnaire;
CREATE TABLE questionnaire (
	questionnaireID int NOT NULL AUTO_INCREMENT ,
    productID int NOT NULL REFERENCES products(productID),
    PRIMARY KEY (questionnaireID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE questionnaireQuestions (
	questionnaireID int NOT NULL REFERENCES questionnaire(questionnaireID),
    questionID int NOT NULL REFERENCES questions(questionID),
    PRIMARY KEY (questionnaireID, questionID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE questionnaireResults (
	questionnaireID int NOT NULL REFERENCES questionnaire(questionnaireID),
    questionID int NOT NULL REFERENCES questions(questionID),
    userID int NOT NULL REFERENCES users(userID),
    answer boolean DEFAULT NULL,
    PRIMARY KEY (questionnaireID, questionID, userID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;