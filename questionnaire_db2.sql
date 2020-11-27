-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Nov 26, 2020 alle 15:55
-- Versione del server: 10.4.14-MariaDB
-- Versione PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `questionnaire_db2`
--
DROP DATABASE IF EXISTS questionnaire_db2;
CREATE DATABASE questionnaire_db2;
USE questionnaire_db2;
-- --------------------------------------------------------

--
-- Struttura della tabella `Accesslog`
--

CREATE TABLE `Accesslog` (
  `UserID` int(11) NOT NULL,
  `Access_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Accesstime`
--

CREATE TABLE `Accesstime` (
  `Access_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Contain`
--

CREATE TABLE `Contain` (
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Answer_text` text NOT NULL,
  `Offensive` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Contain`
--

INSERT INTO `Contain` (`UserID`, `ProductID`, `QuestionID`, `Answer_text`, `Offensive`) VALUES
(1, 2, 2, 'si', 0),
(2, 1, 3, '12', 0),
(2, 2, 1, 'si', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `Marketingquestion`
--

CREATE TABLE `Marketingquestion` (
  `QuestionID` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Marketingquestion`
--

INSERT INTO `Marketingquestion` (`QuestionID`, `Description`) VALUES
(1, 'Ti è piaciuto il prodotto?'),
(2, 'Lo consiglieresti ad un amico?'),
(3, 'Quanto ore lo usi al giorno?');

-- --------------------------------------------------------

--
-- Struttura della tabella `Offensiveword`
--

CREATE TABLE `Offensiveword` (
  `Word` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Product`
--

CREATE TABLE `Product` (
  `ProductID` int(11) NOT NULL,
  `Name` varchar(42) NOT NULL,
  `Image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Product`
--

INSERT INTO `Product` (`ProductID`, `Name`, `Image`) VALUES
(1, 'Playstation 5', '/serverurl/Ps5.jpeg'),
(2, 'GoPro Hero 9', '/serverurl/GoPRO9.jpeg');

-- --------------------------------------------------------

--
-- Struttura della tabella `Questionnaire`
--

CREATE TABLE `Questionnaire` (
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Age` tinyint(4) DEFAULT NULL,
  `Sex` set('male','female') DEFAULT NULL,
  `Expertise_level` set('low','medium','high') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Questionnaire`
--

INSERT INTO `Questionnaire` (`UserID`, `ProductID`, `Date`, `Age`, `Sex`, `Expertise_level`) VALUES
(1, 2, '2020-11-03', NULL, 'male', 'low'),
(2, 1, '2020-11-02', NULL, NULL, NULL),
(2, 2, '2020-11-18', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `Review`
--

CREATE TABLE `Review` (
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Review_Text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Review`
--

INSERT INTO `Review` (`UserID`, `ProductID`, `Review_Text`) VALUES
(1, 1, 'Meglio la xbox'),
(1, 2, 'bella,la consiglio');

-- --------------------------------------------------------

--
-- Struttura della tabella `User`
--

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `Password` varchar(42) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Admin` tinyint(1) NOT NULL,
  `Blocked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `User`
--

INSERT INTO `User` (`UserID`, `Username`, `Password`, `Email`, `Admin`, `Blocked`) VALUES
(1, 'user1', '*668425423DB5193AF921380129F465A6425216D0', 'lorenzo1.amata@mail.polimi.it', 1, 0),
(2, 'user2', '*DC52755F3C09F5923046BD42AFA76BD1D80DF2E9', 'lorenzo2.amata@mail.polimi.it', 0, 1),
(3, 'user3', '*40C3E7D386A2FADBDF69ACEBE7AA4DC3C723D798', 'lorenzo3.amata@mail.polimi.it', 0, 0),
(4, 'user4', '*F97AEB38B3275C06D822FC9341A2151642C81988', 'lorenzo4.amata@mail.polimi.it', 1, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `Accesslog`
--
ALTER TABLE `Accesslog`
  ADD PRIMARY KEY (`UserID`,`Access_time`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `Access_time` (`Access_time`);

--
-- Indici per le tabelle `Accesstime`
--
ALTER TABLE `Accesstime`
  ADD PRIMARY KEY (`Access_time`);

--
-- Indici per le tabelle `Contain`
--
ALTER TABLE `Contain`
  ADD PRIMARY KEY (`UserID`,`ProductID`,`QuestionID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indici per le tabelle `Marketingquestion`
--
ALTER TABLE `Marketingquestion`
  ADD PRIMARY KEY (`QuestionID`);

--
-- Indici per le tabelle `Offensiveword`
--
ALTER TABLE `Offensiveword`
  ADD PRIMARY KEY (`Word`);

--
-- Indici per le tabelle `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indici per le tabelle `Questionnaire`
--
ALTER TABLE `Questionnaire`
  ADD PRIMARY KEY (`UserID`,`ProductID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indici per le tabelle `Review`
--
ALTER TABLE `Review`
  ADD PRIMARY KEY (`UserID`,`ProductID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indici per le tabelle `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `Marketingquestion`
--
ALTER TABLE `Marketingquestion`
  MODIFY `QuestionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `Product`
--
ALTER TABLE `Product`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `User`
--
ALTER TABLE `User`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Accesslog`
--
ALTER TABLE `Accesslog`
  ADD CONSTRAINT `Accesslog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Accesslog_ibfk_2` FOREIGN KEY (`Access_time`) REFERENCES `Accesstime` (`Access_time`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Contain`
--
ALTER TABLE `Contain`
  ADD CONSTRAINT `Contain_ibfk_1` FOREIGN KEY (`UserID`,`ProductID`) REFERENCES `Questionnaire` (`UserID`, `ProductID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Contain_ibfk_2` FOREIGN KEY (`QuestionID`) REFERENCES `Marketingquestion` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Questionnaire`
--
ALTER TABLE `Questionnaire`
  ADD CONSTRAINT `Questionnaire_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Questionnaire_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `Product` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Review`
--
ALTER TABLE `Review`
  ADD CONSTRAINT `Review_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Review_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `Product` (`ProductID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
