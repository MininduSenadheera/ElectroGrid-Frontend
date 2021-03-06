-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 14, 2022 at 11:27 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ElectroGrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `Bill`
--

CREATE TABLE `Bill` (
  `billID` int(11) NOT NULL,
  `connectionID` int(11) NOT NULL,
  `paymentID` int(11) DEFAULT NULL,
  `issueDate` date NOT NULL,
  `dueDate` date NOT NULL,
  `status` varchar(100) NOT NULL,
  `units` int(11) NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Bill`
--

INSERT INTO `Bill` (`billID`, `connectionID`, `paymentID`, `issueDate`, `dueDate`, `status`, `units`, `amount`) VALUES
(31, 3, NULL, '2022-05-05', '2022-06-04', 'Pending', 200, 4963.5),
(80, 1, 1, '2022-01-31', '2022-02-28', 'Paid', 300, 3500),
(81, 1, 2, '2022-02-28', '2022-03-31', 'Paid', 200, 2800),
(82, 1, 3, '2022-03-31', '2022-04-30', 'Paid', 280, 3400),
(83, 1, NULL, '2022-04-30', '2022-05-31', 'Pending', 350, 4800),
(84, 1, NULL, '2022-05-31', '2022-06-30', 'Pending', 270, 3300),
(86, 2, NULL, '2022-05-15', '2022-06-14', 'Pending', 100, 1528.5);

-- --------------------------------------------------------

--
-- Table structure for table `complaint`
--

CREATE TABLE `complaint` (
  `complainID` int(11) NOT NULL,
  `customerID` int(255) NOT NULL,
  `heading` varchar(100) NOT NULL,
  `specification` varchar(20) NOT NULL,
  `content` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint`
--

INSERT INTO `complaint` (`complainID`, `customerID`, `heading`, `specification`, `content`) VALUES
(5059, 1, 'wifi not working', 'slow data', 'service not working'),
(5061, 1, 'wifi working', 'slow not data', 'service working'),
(5062, 1, 'wifi not working', 'slow data', 'service not working');

-- --------------------------------------------------------

--
-- Table structure for table `Connection`
--

CREATE TABLE `Connection` (
  `connectionID` int(11) NOT NULL,
  `customerID` int(255) NOT NULL,
  `status` varchar(200) NOT NULL,
  `type` varchar(200) NOT NULL,
  `units` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Connection`
--

INSERT INTO `Connection` (`connectionID`, `customerID`, `status`, `type`, `units`) VALUES
(1, 1, 'Active', '3 Phase', 5800),
(2, 1, 'Active', 'Domestic', 5300),
(3, 2, 'Active', 'Domestic', 400);

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `customerID` int(255) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  `lastName` varchar(200) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`customerID`, `firstName`, `lastName`, `NIC`, `phone`, `email`, `address`) VALUES
(1, 'Minindu', 'Senadheera', '200024201270', '0767041198', 'minindusenadheera@gmail.com', '199/1, Bolabotuwa, Bandaragama'),
(2, 'Onara', 'Pathiraja', '2000769699', '0771229876', 'onara@gmail.com', 'No.69 Gampaha');

-- --------------------------------------------------------

--
-- Table structure for table `notices`
--

CREATE TABLE `notices` (
  `noticeId` int(255) NOT NULL,
  `nmessage` varchar(10000) NOT NULL,
  `duration` varchar(2000) NOT NULL,
  `aparty` varchar(2000) NOT NULL,
  `heading` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notices`
--

INSERT INTO `notices` (`noticeId`, `nmessage`, `duration`, `aparty`, `heading`) VALUES
(2, 'red notice', 'three weeks', 'suppliers', 'special'),
(56, 'test2', '4days', 'eng', 'topic'),
(456, 'test1', '4days', 'eng', 'topic');

-- --------------------------------------------------------

--
-- Table structure for table `Payment`
--

CREATE TABLE `Payment` (
  `paymentID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `paymentDateTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `amount` float NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Payment`
--

INSERT INTO `Payment` (`paymentID`, `customerID`, `paymentDateTime`, `amount`, `type`) VALUES
(1, 1, '2022-02-09 07:36:48', 3500, 'Online'),
(2, 1, '2022-05-14 20:35:08', 2800, 'Cash'),
(3, 1, '2022-04-14 17:28:21', 3400, 'Cash');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Bill`
--
ALTER TABLE `Bill`
  ADD PRIMARY KEY (`billID`),
  ADD KEY `connectionID` (`connectionID`),
  ADD KEY `paymentID` (`paymentID`);

--
-- Indexes for table `complaint`
--
ALTER TABLE `complaint`
  ADD PRIMARY KEY (`complainID`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `Connection`
--
ALTER TABLE `Connection`
  ADD PRIMARY KEY (`connectionID`),
  ADD KEY `customerID` (`customerID`);

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `notices`
--
ALTER TABLE `notices`
  ADD PRIMARY KEY (`noticeId`);

--
-- Indexes for table `Payment`
--
ALTER TABLE `Payment`
  ADD PRIMARY KEY (`paymentID`),
  ADD KEY `customerID` (`customerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Bill`
--
ALTER TABLE `Bill`
  MODIFY `billID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT for table `complaint`
--
ALTER TABLE `complaint`
  MODIFY `complainID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5063;

--
-- AUTO_INCREMENT for table `Connection`
--
ALTER TABLE `Connection`
  MODIFY `connectionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `Customer`
--
ALTER TABLE `Customer`
  MODIFY `customerID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Payment`
--
ALTER TABLE `Payment`
  MODIFY `paymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Bill`
--
ALTER TABLE `Bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`connectionID`) REFERENCES `Connection` (`connectionID`),
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`paymentID`) REFERENCES `Payment` (`paymentID`);

--
-- Constraints for table `complaint`
--
ALTER TABLE `complaint`
  ADD CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`);

--
-- Constraints for table `Connection`
--
ALTER TABLE `Connection`
  ADD CONSTRAINT `connection_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`);

--
-- Constraints for table `Payment`
--
ALTER TABLE `Payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
