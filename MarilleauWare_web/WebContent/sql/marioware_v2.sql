-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 18 Janvier 2013 à 14:17
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `chat_party`
--

INSERT INTO `chat_party` (`idMessage`, `idParty`, `idUser`, `date`, `message`) VALUES
(1, 27, 20, '2013-01-15 03:54:32', 'salut'),
(2, 27, 20, '2013-01-15 03:54:39', 'tu vas bien ?'),
(3, 27, 20, '2013-01-15 03:54:51', 'Moi c''est trop cool le chat'),
(4, 27, 20, '2013-01-15 03:54:57', 'HAHAHAHAHAHAH'),
(5, 27, 20, '2013-01-15 03:55:09', 'il est beau notre chat'),
(6, 27, 20, '2013-01-15 03:55:13', 'heyhey'),
(7, 27, 20, '2013-01-15 03:55:28', 'heyhey c''est bien'),
(8, 27, 20, '2013-01-15 03:55:33', 'hihihihii'),
(9, 27, 20, '2013-01-15 03:55:37', 'hahahahaah'),
(10, 27, 22, '2013-01-18 13:54:38', 'Hiiiiiii'),
(11, 27, 22, '2013-01-18 13:54:45', 'Hiiiiiiipipi'),
(12, 27, 22, '2013-01-18 13:54:51', 'Hiiiiiiipipimimi');

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

--
-- Contenu de la table `games`
--

INSERT INTO `games` (`idGame`, `idGame_desc`, `idParty`, `startGame`, `endGame`, `sequence`) VALUES
(211, 1, 23, 0, 0, 0),
(212, 2, 23, 0, 0, 1),
(213, 2, 23, 0, 0, 2),
(214, 2, 24, 0, 0, 0),
(215, 1, 24, 0, 0, 1),
(216, 1, 24, 0, 0, 2),
(217, 1, 25, 0, 0, 0),
(218, 2, 25, 0, 0, 1),
(219, 2, 25, 0, 0, 2),
(220, 2, 26, 0, 0, 0),
(221, 1, 26, 0, 0, 1),
(222, 2, 26, 0, 0, 2),
(223, 1, 27, 0, 0, 0),
(224, 2, 27, 0, 0, 1),
(225, 2, 27, 0, 0, 2);

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

--
-- Contenu de la table `games_desc`
--

INSERT INTO `games_desc` (`idGame_desc`, `name`, `description`) VALUES
(1, 'game1', 'game CKawczak'),
(2, 'gameCheckBox', 'game PMeyer');

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

--
-- Contenu de la table `parties`
--

INSERT INTO `parties` (`idParty`, `name`, `description`, `idUserCreate`, `startParty`, `endParty`, `idCurrentGame`) VALUES
(23, 'Ma partie', 'hihi', 13, 0, 0, 211),
(24, 'Ma partie 2', 'aaa', 14, 0, 0, 214),
(25, 'ejb', 'tee', 14, 0, 0, 217),
(26, 'azer', 'azerd', 14, 0, 0, 220),
(27, 'New', 'aaaz', 14, 0, 0, 223);

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

--
-- Contenu de la table `tj_games_users`
--

INSERT INTO `tj_games_users` (`idGame`, `idUser`, `score`) VALUES
(223, 20, -1),
(223, 22, 500000);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`idUser`, `pseudo`, `password`, `isAdmin`, `score`, `idParty`) VALUES
(13, 'Pierre', '', 0, 0, 23),
(14, 'Thibaud', '', 0, 0, 27),
(15, 'Piou', '', 0, 0, 27),
(16, 'Pii', '', 0, 0, 27),
(17, 'Le pure peunj', '', 0, 0, 27),
(18, 'daz', '', 0, 0, 27),
(19, 'qdwxc', '', 0, 0, 27),
(20, 'qsdw', '', 0, 0, 27),
(21, 'Pierrette', '', 0, 0, NULL),
(22, 'climent', '', 0, 0, 27);

--
-- Contraintes pour les tables exportées
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
