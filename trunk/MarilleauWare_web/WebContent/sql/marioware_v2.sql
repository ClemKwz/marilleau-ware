-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Genere le : Ven 14 Decembre 2012 a 14:32
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de donnees: `marioware`
--

-- --------------------------------------------------------

--
-- Structure de la table `games_desc`
--

CREATE TABLE IF NOT EXISTS `games_desc` (
  `idGame_desc` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`idGame_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `parties`
--

CREATE TABLE IF NOT EXISTS `parties` (
  `idParty` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(128) NOT NULL,
  `idUserCreate` int(11) NOT NULL,
  `startParty` int(1) NOT NULL,
  `endParty` int(1) NOT NULL,
  `idCurrentGame` int(11) NOT NULL,
  PRIMARY KEY (`idParty`),
  FOREIGN KEY(idUserCreate) references users(idUser)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `games`
--

CREATE TABLE IF NOT EXISTS `games` (
  `idGame` int(11) NOT NULL AUTO_INCREMENT,
  `idGame_desc` int(11) NOT NULL,
  `idParty` int(11) NOT NULL,
  `startGame` int(1) NOT NULL,
  `endGame` int(1) NOT NULL,
  PRIMARY KEY (`idGame`),
  FOREIGN KEY(idGame_desc) references games_desc(idGame_desc),
  FOREIGN KEY(idParty) references parties(idParty)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------


--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(32) NOT NULL,
  `password` varchar(64),
  `isAdmin` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `idParty` int(11),
  PRIMARY KEY (`idUser`),
  FOREIGN KEY(idParty) references parties(idParty)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Structure de la table `tj_games_users`
--

CREATE TABLE IF NOT EXISTS `tj_games_users` (
  `idGame` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`idGame`,`idUser`),
  FOREIGN KEY(idGame) references games(idGame),
  FOREIGN KEY(idUser) references users(idUser)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
