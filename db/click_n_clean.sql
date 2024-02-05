-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 05, 2024 at 03:54 PM
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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`id_mission`),
  ADD KEY `owner_of_the_mission` (`id_owner`),
  ADD KEY `property_being_cleaned` (`id_property`),
  ADD KEY `cleaner_of_the_mission` (`id_cleaner`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
  MODIFY `id_mission` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `cleaner_of_the_mission` FOREIGN KEY (`id_cleaner`) REFERENCES `cleaner` (`id_cleaner`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `owner_of_the_mission` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`id_owner`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `property_being_cleaned` FOREIGN KEY (`id_property`) REFERENCES `property` (`id_property`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
