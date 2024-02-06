-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 06, 2024 at 09:53 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `click_n_clean`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `id_activity` int NOT NULL,
  `type` int NOT NULL,
  `opened` tinyint(1) NOT NULL,
  `id_owner` int NOT NULL,
  `id_cleaner` int NOT NULL,
  `id_mission` int NOT NULL,
  `id_dispute` int NOT NULL,
  `id_admin` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int NOT NULL,
  `id_user` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `cleaner`
--

CREATE TABLE `cleaner` (
  `id_cleaner` int NOT NULL,
  `address` varchar(25) NOT NULL,
  `km_range` int NOT NULL,
  `hourly_rate` int NOT NULL,
  `biography` varchar(100) NOT NULL,
  `photo` varchar(36) NOT NULL,
  `motivation` varchar(50) NOT NULL,
  `experience` varchar(50) NOT NULL,
  `id_user` int NOT NULL,
  `confirmed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `dispute`
--

CREATE TABLE `dispute` (
  `id_dispute` int NOT NULL,
  `content` varchar(50) NOT NULL,
  `decision` varchar(50) NOT NULL,
  `id_owner` int NOT NULL,
  `id_cleaner` int NOT NULL,
  `id_mission` int NOT NULL,
  `dispute_creator_id` int NOT NULL,
  `id_admin` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `mission`
--

CREATE TABLE `mission` (
  `id_mission` int NOT NULL,
  `date_start` date NOT NULL,
  `cost` double NOT NULL,
  `duration` double NOT NULL,
  `commision` double NOT NULL,
  `state` int NOT NULL,
  `acces_code` int NOT NULL,
  `key_box_code` int NOT NULL,
  `special_instructon` varchar(50) NOT NULL,
  `before_photo` varchar(36) NOT NULL,
  `after_photo` int NOT NULL,
  `id_owner` int NOT NULL,
  `id_cleaner` int NOT NULL,
  `id_property` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `id_owner` int NOT NULL,
  `account_date` date NOT NULL,
  `type_service` int NOT NULL,
  `id_user` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `id_property` int NOT NULL,
  `address` varchar(25) NOT NULL,
  `surface` int NOT NULL,
  `id_owner` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id_review` int NOT NULL,
  `content` varchar(100) NOT NULL,
  `grade` int NOT NULL,
  `id_user` int NOT NULL,
  `id_mission` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int NOT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(35) NOT NULL,
  `surname` varchar(15) NOT NULL,
  `email` int NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `birth_date` date NOT NULL,
  `accunt_date` date NOT NULL,
  `suspended` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id_activity`),
  ADD KEY `owner_of_the_activity` (`id_owner`),
  ADD KEY `cleaner_of_the_activity` (`id_cleaner`),
  ADD KEY `mission_of_the_activity` (`id_mission`),
  ADD KEY `dispute_of_the_mission` (`id_dispute`),
  ADD KEY `admin_of_the_mission` (`id_admin`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD KEY `admin_is_a_user` (`id_user`);

--
-- Indexes for table `cleaner`
--
ALTER TABLE `cleaner`
  ADD PRIMARY KEY (`id_cleaner`),
  ADD KEY `cleaner_is_an_user` (`id_user`);

--
-- Indexes for table `dispute`
--
ALTER TABLE `dispute`
  ADD PRIMARY KEY (`id_dispute`),
  ADD KEY `owner_of_the_mission_disputed` (`id_owner`),
  ADD KEY `cleaner_of_the_mission_disputed` (`id_cleaner`),
  ADD KEY `mission_being_disputed` (`id_mission`),
  ADD KEY `creator_of_the_dispute` (`dispute_creator_id`),
  ADD KEY `admin_of_the_dispute` (`id_admin`);

--
-- Indexes for table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`id_mission`),
  ADD KEY `owner_of_the_mission` (`id_owner`),
  ADD KEY `property_being_cleaned` (`id_property`),
  ADD KEY `cleaner_of_the_mission` (`id_cleaner`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`id_owner`),
  ADD KEY `owner_is_a_user` (`id_user`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`id_property`),
  ADD KEY `owner_own_property` (`id_owner`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id_review`),
  ADD KEY `mission_of_the_review` (`id_mission`),
  ADD KEY `target_of_the_review` (`id_user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `id_activity` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cleaner`
--
ALTER TABLE `cleaner`
  MODIFY `id_cleaner` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dispute`
--
ALTER TABLE `dispute`
  MODIFY `id_dispute` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
  MODIFY `id_mission` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `id_owner` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `id_property` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id_review` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `admin_of_the_mission` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `cleaner_of_the_activity` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `dispute_of_the_mission` FOREIGN KEY (`id_dispute`) REFERENCES `dispute` (`id_dispute`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `mission_of_the_activity` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id_mission`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `owner_of_the_activity` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_is_a_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `cleaner`
--
ALTER TABLE `cleaner`
  ADD CONSTRAINT `cleaner_is_an_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `dispute`
--
ALTER TABLE `dispute`
  ADD CONSTRAINT `admin_of_the_dispute` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `cleaner_of_the_mission_disputed` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `creator_of_the_dispute` FOREIGN KEY (`dispute_creator_id`) REFERENCES `user` (`id_user`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `mission_being_disputed` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id_mission`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `owner_of_the_mission_disputed` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `cleaner_of_the_mission` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `owner_of_the_mission` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `property_being_cleaned` FOREIGN KEY (`id_property`) REFERENCES `property` (`id_property`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `owner_is_a_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `owner_own_property` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `mission_of_the_review` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id_mission`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `target_of_the_review` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
