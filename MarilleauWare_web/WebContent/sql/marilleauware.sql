-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- G�n�r� le: Ven 25 Janvier 2013 � 16:01
-- Version du serveur: 5.5.16-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de donn�es: `marioware2`
--

-- --------------------------------------------------------

--
-- Structure de la table `chat_party`
--

CREATE TABLE IF NOT EXISTS `chat_party` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT,
  `idParty` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `message` varchar(255) NOT NULL,
  PRIMARY KEY (`idMessage`),
  KEY `idParty` (`idParty`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `data` varchar(500) NOT NULL,
  PRIMARY KEY (`idGame`),
  KEY `idGame_desc` (`idGame_desc`),
  KEY `idParty` (`idParty`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=848 ;


-- --------------------------------------------------------

--
-- Structure de la table `games_desc`
--

CREATE TABLE IF NOT EXISTS `games_desc` (
  `idGame_desc` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`idGame_desc`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `games_desc`
--

INSERT INTO `games_desc` (`idGame_desc`, `name`, `description`) VALUES
(1, 'FindTheDot', 'game CKawczak'),
(2, 'CheckBox', 'game PMeyer'),
(4, 'BuildPath', 'Jeu du roi des Monnet');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=237 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=156 ;

--
-- Contraintes pour les tables export�es
--

--
-- Contraintes pour la table `chat_party`
--
ALTER TABLE `chat_party`
  ADD CONSTRAINT `chat_party_ibfk_1` FOREIGN KEY (`idParty`) REFERENCES `parties` (`idParty`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chat_party_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

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
