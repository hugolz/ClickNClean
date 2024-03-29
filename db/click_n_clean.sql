-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : lun. 19 fév. 2024 à 15:29
-- Version du serveur : 8.0.32
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `click_n_clean`
--

-- --------------------------------------------------------

--
-- Structure de la table `activity`
--

CREATE TABLE `activity` (
  `id_activity` int NOT NULL,
  `type` int NOT NULL,
  `opened` tinyint(1) NOT NULL,
  `id_owner` int UNSIGNED DEFAULT NULL,
  `id_cleaner` int UNSIGNED DEFAULT NULL,
  `id_mission` int UNSIGNED DEFAULT NULL,
  `id_dispute` int UNSIGNED DEFAULT NULL,
  `id_admin` int UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `cleaner`
--

CREATE TABLE `cleaner` (
  `id_cleaner` int UNSIGNED NOT NULL,
  `address_display` varchar(100) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `km_range` int NOT NULL,
  `hourly_rate` int NOT NULL,
  `biography` varchar(100) NOT NULL,
  `photo` varchar(36) NOT NULL,
  `photo_profile` varchar(36) NOT NULL,
  `photo_live` varchar(36) NOT NULL,

  `motivation` varchar(250) NOT NULL,
  `experience` varchar(250) NOT NULL,
  `confirmed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `dispute`
--

CREATE TABLE `dispute` (
  `id_dispute` int UNSIGNED NOT NULL,
  `content` varchar(200) NOT NULL,
  `decision` varchar(200) NOT NULL,
  `id_owner` int UNSIGNED NOT NULL,
  `id_cleaner` int UNSIGNED NOT NULL,
  `id_mission` int UNSIGNED NOT NULL,
  `id_dispute_creator` int UNSIGNED NOT NULL,
  `id_admin` int UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

CREATE TABLE `mission` (
  `id_mission` int UNSIGNED NOT NULL,
  `date_start` date NOT NULL,
  `cost` double DEFAULT NULL,
  `duration` double NOT NULL,
  `commision` double DEFAULT NULL,
  `state` int NOT NULL,
  `before_photo` varchar(50) DEFAULT NULL,
  `after_photo` int DEFAULT NULL,
  `id_owner` int UNSIGNED NOT NULL,
  `id_cleaner` int UNSIGNED DEFAULT NULL,
  `id_property` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `mission_proposal`
--

CREATE TABLE `mission_proposal` (
  `id_mission` int NOT NULL,
  `id_cleaner` int NOT NULL,
  `starting_hour` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `owner`
--

CREATE TABLE `owner` (
  `id_owner` int UNSIGNED NOT NULL,
  `account_date` date NOT NULL,
  `type_service` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE `planning` (
  `id_cleaner` int UNSIGNED NOT NULL,
  `datetime` datetime NOT NULL,
  `durationH` double not NULL,
  `id_mission` int
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `property`
--

CREATE TABLE `property` (
  `id_property` int UNSIGNED NOT NULL,
  `address_display` varchar(100) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `surface` int NOT NULL,
  `id_owner` int UNSIGNED NOT NULL,
  `acces_code` int DEFAULT NULL,
  `key_box_code` int DEFAULT NULL,
  `special_instructon` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `id_review` int UNSIGNED NOT NULL,
  `content` varchar(100) NOT NULL,
  `grade` int NOT NULL,
  `id_user` int UNSIGNED NOT NULL,
  `id_mission` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `id_status` int UNSIGNED NOT NULL,
  `name_status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`id_status`, `name_status`) VALUES
(1, 'Admin'),
(2, 'Cleaner'),
(3, 'Owner');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int UNSIGNED NOT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(35) NOT NULL,
  `surname` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `birth_date` date NOT NULL,
  `account_date` date NOT NULL,
  `suspended` tinyint(1) NOT NULL DEFAULT '0',
  `status` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id_activity`),
  ADD KEY `owner_of_the_activity` (`id_owner`),
  ADD KEY `cleaner_of_the_activity` (`id_cleaner`),
  ADD KEY `mission_of_the_activity` (`id_mission`),
  ADD KEY `dispute_of_the_mission` (`id_dispute`),
  ADD KEY `admin_of_the_mission` (`id_admin`);

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD KEY `admin_is_a_user` (`id_admin`);

--
-- Index pour la table `cleaner`
--
ALTER TABLE `cleaner`
  ADD PRIMARY KEY (`id_cleaner`),
  ADD KEY `cleaner_is_an_user` (`id_cleaner`);

--
-- Index pour la table `dispute`
--
ALTER TABLE `dispute`
  ADD PRIMARY KEY (`id_dispute`),
  ADD KEY `owner_of_the_mission_disputed` (`id_owner`),
  ADD KEY `cleaner_of_the_mission_disputed` (`id_cleaner`),
  ADD KEY `mission_being_disputed` (`id_mission`),
  ADD KEY `creator_of_the_dispute` (`id_dispute_creator`),
  ADD KEY `admin_of_the_dispute` (`id_admin`);

--
-- Index pour la table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`id_mission`),
  ADD KEY `owner_of_the_mission` (`id_owner`),
  ADD KEY `property_being_cleaned` (`id_property`),
  ADD KEY `cleaner_of_the_mission` (`id_cleaner`);

--
-- Index pour la table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`id_owner`),
  ADD KEY `owner_is_a_user` (`id_owner`);

--
-- Index pour la table `planning`
--
ALTER TABLE `planning`
  ADD KEY `cleaner_owns_a_planning` (`id_cleaner`);

--
-- Index pour la table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`id_property`),
  ADD KEY `owner_own_property` (`id_owner`);

--
-- Index pour la table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id_review`),
  ADD KEY `mission_of_the_review` (`id_mission`),
  ADD KEY `target_of_the_review` (`id_user`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id_status`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `status` (`status`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activity`
--
ALTER TABLE `activity`
  MODIFY `id_activity` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `dispute`
--
ALTER TABLE `dispute`
  MODIFY `id_dispute` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `mission`
--
ALTER TABLE `mission`
  MODIFY `id_mission` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `property`
--
ALTER TABLE `property`
  MODIFY `id_property` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `review`
--
ALTER TABLE `review`
  MODIFY `id_review` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `id_status` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `admin_of_the_mission` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`),
  ADD CONSTRAINT `cleaner_of_the_activity` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`),
  ADD CONSTRAINT `dispute_of_the_mission` FOREIGN KEY (`id_dispute`) REFERENCES `dispute` (`id_dispute`),
  ADD CONSTRAINT `mission_of_the_activity` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id_mission`),
  ADD CONSTRAINT `owner_of_the_activity` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`);

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_is_a_user` FOREIGN KEY (`id_admin`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `cleaner`
--
ALTER TABLE `cleaner`
  ADD CONSTRAINT `cleaner_is_an_user` FOREIGN KEY (`id_cleaner`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `dispute`
--
ALTER TABLE `dispute`
  ADD CONSTRAINT `admin_of_the_dispute` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`),
  ADD CONSTRAINT `cleaner_of_the_mission_disputed` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`),
  ADD CONSTRAINT `creator_of_the_dispute` FOREIGN KEY (`id_dispute_creator`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `mission_being_disputed` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id_mission`),
  ADD CONSTRAINT `owner_of_the_mission_disputed` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`);

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `cleaner_of_the_mission` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`),
  ADD CONSTRAINT `owner_of_the_mission` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`),
  ADD CONSTRAINT `property_being_cleaned` FOREIGN KEY (`id_property`) REFERENCES `property` (`id_property`);

--
-- Contraintes pour la table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `owner_is_a_user` FOREIGN KEY (`id_owner`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `owner_own_property` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`);

--
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `mission_of_the_review` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id_mission`),
  ADD CONSTRAINT `target_of_the_review` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`status`) REFERENCES `status` (`id_status`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
