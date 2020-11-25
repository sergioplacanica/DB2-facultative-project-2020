-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Nov 25, 2020 alle 12:40
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
-- Struttura della tabella `Answer`
--

CREATE TABLE `Answer` (
  `UserID` int(11) NOT NULL,
  `QuestionnaireID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Answer_text` text NOT NULL,
  `Offensive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Contain`
--

CREATE TABLE `Contain` (
  `QuestionnaireID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Marketingquestion`
--

CREATE TABLE `Marketingquestion` (
  `QuestionID` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Struttura della tabella `Questionnaire`
--

CREATE TABLE `Questionnaire` (
  `QuestionnaireID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Age` tinyint(4) NOT NULL,
  `Sex` set('male','female') NOT NULL,
  `Expertise_level` set('low','medium','high') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Review`
--

CREATE TABLE `Review` (
  `ReviewID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Review_Text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  ADD KEY `UserID` (`UserID`),
  ADD KEY `Access_time` (`Access_time`);

--
-- Indici per le tabelle `Accesstime`
--
ALTER TABLE `Accesstime`
  ADD PRIMARY KEY (`Access_time`);

--
-- Indici per le tabelle `Answer`
--
ALTER TABLE `Answer`
  ADD KEY `UserID` (`UserID`),
  ADD KEY `QuestionnaireID` (`QuestionnaireID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indici per le tabelle `Contain`
--
ALTER TABLE `Contain`
  ADD KEY `QuestionnaireID` (`QuestionnaireID`),
  ADD KEY `QuestionID` (`QuestionID`);

--
-- Indici per le tabelle `Marketingquestion`
--
ALTER TABLE `Marketingquestion`
  ADD PRIMARY KEY (`QuestionID`);

--
-- Indici per le tabelle `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indici per le tabelle `Questionnaire`
--
ALTER TABLE `Questionnaire`
  ADD PRIMARY KEY (`QuestionnaireID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indici per le tabelle `Review`
--
ALTER TABLE `Review`
  ADD PRIMARY KEY (`ReviewID`),
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
  MODIFY `QuestionID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `Product`
--
ALTER TABLE `Product`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `Questionnaire`
--
ALTER TABLE `Questionnaire`
  MODIFY `QuestionnaireID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `Review`
--
ALTER TABLE `Review`
  MODIFY `ReviewID` int(11) NOT NULL AUTO_INCREMENT;

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
-- Limiti per la tabella `Answer`
--
ALTER TABLE `Answer`
  ADD CONSTRAINT `Answer_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Answer_ibfk_2` FOREIGN KEY (`QuestionnaireID`) REFERENCES `Questionnaire` (`QuestionnaireID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Answer_ibfk_3` FOREIGN KEY (`QuestionID`) REFERENCES `Marketingquestion` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Contain`
--
ALTER TABLE `Contain`
  ADD CONSTRAINT `Contain_ibfk_1` FOREIGN KEY (`QuestionnaireID`) REFERENCES `Questionnaire` (`QuestionnaireID`) ON DELETE CASCADE ON UPDATE CASCADE,
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
