-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 14 Janvier 2013 à 22:56
-- Version du serveur: 5.5.16-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `marioware2`
--

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
  `sequence` int(11) NOT NULL,
  PRIMARY KEY (`idGame`),
  KEY `idGame_desc` (`idGame_desc`),
  KEY `idParty` (`idParty`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=226 ;

-- --------------------------------------------------------

--
-- Structure de la table `games_desc`
--

CREATE TABLE IF NOT EXISTS `games_desc` (
  `idGame_desc` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`idGame_desc`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

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
  PRIMARY KEY (`idParty`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

-- --------------------------------------------------------

--
-- Structure de la table `tj_games_users`
--

CREATE TABLE IF NOT EXISTS `tj_games_users` (
  `idGame` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`idGame`,`idUser`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(32) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `isAdmin` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `idParty` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  KEY `idParty` (`idParty`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `games_ibfk_1` FOREIGN KEY (`idGame_desc`) REFERENCES `games_desc` (`idGame_desc`),
  ADD CONSTRAINT `games_ibfk_2` FOREIGN KEY (`idParty`) REFERENCES `parties` (`idParty`);

--
-- Contraintes pour la table `tj_games_users`
--
ALTER TABLE `tj_games_users`
  ADD CONSTRAINT `tj_games_users_ibfk_1` FOREIGN KEY (`idGame`) REFERENCES `games` (`idGame`),
  ADD CONSTRAINT `tj_games_users_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`idParty`) REFERENCES `parties` (`idParty`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
