-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 15. Jun 2014 um 14:52
-- Server Version: 5.6.16
-- PHP-Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `gruppe5db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abonnement`
--

CREATE TABLE IF NOT EXISTS `abonnement` (
  `Abonnement_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Nutzer_ID` int(5) NOT NULL,
  `Pinnwand_ID` int(5) NOT NULL,
  PRIMARY KEY (`Abonnement_ID`),
  UNIQUE KEY `Index_uq_abo` (`Nutzer_ID`,`Pinnwand_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `beitrag`
--

CREATE TABLE IF NOT EXISTS `beitrag` (
  `Beitrag_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Nutzer_ID` int(5) NOT NULL,
  `Like_ID` int(5) NOT NULL,
  `Text` text COLLATE latin1_german2_ci NOT NULL,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Beitrag_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kommentar`
--

CREATE TABLE IF NOT EXISTS `kommentar` (
  `Kommentar_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Nutzer_ID` int(5) NOT NULL,
  `Beitrag_ID` int(5) NOT NULL,
  `Text` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Kommentar_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `likee`
--

CREATE TABLE IF NOT EXISTS `likee` (
  `Like_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Nutzer_ID` int(5) NOT NULL,
  `Beitrag_ID` int(5) NOT NULL,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Like_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `nutzer`
--

CREATE TABLE IF NOT EXISTS `nutzer` (
  `Nutzer_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Vorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Nachname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Nickname` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Passwort` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Email` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Nutzer_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pinnwand`
--

CREATE TABLE IF NOT EXISTS `pinnwand` (
  `Pinnwand_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Datum` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Nutzer_ID` int(5) NOT NULL,
  `Abonnement_ID` int(5) NOT NULL,
  PRIMARY KEY (`Pinnwand_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
