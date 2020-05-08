-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 08 mai 2020 à 14:51
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `baseoeuvre`
--
CREATE DATABASE IF NOT EXISTS `baseoeuvre` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `baseoeuvre`;

-- --------------------------------------------------------

--
-- Structure de la table `adherent`
--

CREATE TABLE `adherent` (
  `id_adherent` int(10) UNSIGNED NOT NULL,
  `nom_adherent` varchar(100) NOT NULL,
  `prenom_adherent` varchar(100) DEFAULT NULL,
  `ville_adherent` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `adherent`
--

INSERT INTO `adherent` (`id_adherent`, `nom_adherent`, `prenom_adherent`, `ville_adherent`) VALUES
(3, 'DUPONT', 'Didier', 'Lyon'),
(4, 'Michel', 'DURANT', 'Givors'),
(5, 'CHOPIN', 'Daniel', 'Villefranche'),
(6, 'MOZART', 'Albert', 'Craponne'),
(7, 'FRUCCI', 'Fraise', 'Craponne'),
(8, 'BRUN', 'Pierre', 'Lyon'),
(9, 'Blanc', 'Serge', 'Oullins'),
(10, 'Salaun', 'AmÃƒÂ©lie', 'Madrid'),
(11, 'VIAL', 'albert  ', 'Lyon'),
(12, 'VIAL', 'fg  ', 'Lyon'),
(13, 'Grand', 'Alain', 'Tours'),
(15, 'THOAR', 'Ingrid', 'BERLIN'),
(16, 'Aupoil', 'Lucas', 'Lyon'),
(17, 'Iron', 'Man', 'New York');

-- --------------------------------------------------------

--
-- Structure de la table `oeuvrepret`
--

CREATE TABLE `oeuvrepret` (
  `id_oeuvrepret` int(10) UNSIGNED NOT NULL,
  `titre_oeuvrepret` varchar(200) NOT NULL,
  `id_proprietaire` int(10) UNSIGNED DEFAULT NULL,
  `id_adherent` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `oeuvrepret`
--

INSERT INTO `oeuvrepret` (`id_oeuvrepret`, `titre_oeuvrepret`, `id_proprietaire`, `id_adherent`) VALUES
(1, 'Oeuvre en pret 1', 16, NULL),
(4, 'Le Seigneur des Anneaux III', 16, NULL),
(5, 'Seigneur des Anneaux II', 17, 16),
(6, 'Seigneur des Anneaux I', 17, NULL),
(8, 'Green Book', 16, 9);

-- --------------------------------------------------------

--
-- Structure de la table `oeuvrevente`
--

CREATE TABLE `oeuvrevente` (
  `id_oeuvrevente` int(10) UNSIGNED NOT NULL,
  `titre_oeuvrevente` varchar(200) NOT NULL,
  `etat_oeuvrevente` varchar(1) NOT NULL,
  `prix_oeuvrevente` float NOT NULL,
  `id_proprietaire` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `oeuvrevente`
--

INSERT INTO `oeuvrevente` (`id_oeuvrevente`, `titre_oeuvrevente`, `etat_oeuvrevente`, `prix_oeuvrevente`, `id_proprietaire`) VALUES
(10000, 'lala', 'V', 17, 16),
(10003, 'Crepuscule', 'R', 111, 16),
(10004, 'Hiver', 'R', 234, 17),
(10005, 'Aurore', 'L', 400, 16),
(10006, 'Nuit de printemps', 'V', 789, 16),
(10007, 'nouvelle Oeuvre 2', 'R', 12, 16),
(10010, 'Le Hobbit', 'L', 550, 16);

-- --------------------------------------------------------

--
-- Structure de la table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `id_proprietaire` int(10) UNSIGNED NOT NULL,
  `nom_proprietaire` varchar(100) CHARACTER SET latin1 NOT NULL,
  `prenom_proprietaire` varchar(100) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `proprietaire`
--

INSERT INTO `proprietaire` (`id_proprietaire`, `nom_proprietaire`, `prenom_proprietaire`) VALUES
(16, 'Aupoil', 'Lucas'),
(17, 'Iron', 'Man');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_oeuvrevente` int(10) UNSIGNED NOT NULL,
  `id_adherent` int(10) UNSIGNED NOT NULL,
  `date_reservation` date NOT NULL,
  `statut` varchar(20) NOT NULL,
  `id_proprietaire` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_oeuvrevente`, `id_adherent`, `date_reservation`, `statut`, `id_proprietaire`) VALUES
(10003, 3, '2013-02-22', 'confirmee', 16),
(10004, 7, '2013-02-22', 'confirmee', 17),
(10007, 17, '2020-05-05', 'confirmee', 16);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `NumUtil` int(11) NOT NULL,
  `NomUtil` varchar(100) COLLATE utf8_bin NOT NULL,
  `PrenomUtil` varchar(100) COLLATE utf8_bin NOT NULL,
  `MotPasse` varchar(100) COLLATE utf8_bin NOT NULL,
  `salt` varchar(100) COLLATE utf8_bin NOT NULL,
  `role` varchar(100) COLLATE utf8_bin NOT NULL,
  `solde` float(10,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`NumUtil`, `NomUtil`, `PrenomUtil`, `MotPasse`, `salt`, `role`, `solde`) VALUES
(1, 'Merlot', 'Albert', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'admin', 0.00),
(2, 'Lalande', 'Marie', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'visiteur', 0.00),
(3, 'Pinot', 'Hugo', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'visiteur', 0.00),
(5, 'Aupoil', 'Lucas', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'visiteur', 1922.00),
(6, 'Grand', 'Alain', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'visiteur', 1100.00);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherent`
--
ALTER TABLE `adherent`
  ADD PRIMARY KEY (`id_adherent`);

--
-- Index pour la table `oeuvrepret`
--
ALTER TABLE `oeuvrepret`
  ADD PRIMARY KEY (`id_oeuvrepret`),
  ADD KEY `fk_op_proprietaire` (`id_proprietaire`),
  ADD KEY `fk_op_adherent` (`id_adherent`);

--
-- Index pour la table `oeuvrevente`
--
ALTER TABLE `oeuvrevente`
  ADD PRIMARY KEY (`id_oeuvrevente`),
  ADD KEY `id_proprietaire` (`id_proprietaire`);

--
-- Index pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`id_proprietaire`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_oeuvrevente`,`id_adherent`),
  ADD KEY `id_adherent` (`id_adherent`),
  ADD KEY `reservation_fk_proprietaire` (`id_proprietaire`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`NumUtil`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adherent`
--
ALTER TABLE `adherent`
  MODIFY `id_adherent` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `oeuvrepret`
--
ALTER TABLE `oeuvrepret`
  MODIFY `id_oeuvrepret` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `oeuvrevente`
--
ALTER TABLE `oeuvrevente`
  MODIFY `id_oeuvrevente` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10011;

--
-- AUTO_INCREMENT pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `id_proprietaire` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `NumUtil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `oeuvrepret`
--
ALTER TABLE `oeuvrepret`
  ADD CONSTRAINT `fk_op_adherent` FOREIGN KEY (`id_adherent`) REFERENCES `adherent` (`id_adherent`),
  ADD CONSTRAINT `fk_op_proprietaire` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaire` (`id_proprietaire`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `oeuvrevente`
--
ALTER TABLE `oeuvrevente`
  ADD CONSTRAINT `oeuvrevente_ibfk_1` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaire` (`id_proprietaire`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD CONSTRAINT `fk_prorietaire_adherent` FOREIGN KEY (`id_proprietaire`) REFERENCES `adherent` (`id_adherent`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_fk_proprietaire` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaire` (`id_proprietaire`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_oeuvrevente`) REFERENCES `oeuvrevente` (`id_oeuvrevente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`id_adherent`) REFERENCES `adherent` (`id_adherent`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
