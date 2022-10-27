-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for pts
CREATE DATABASE IF NOT EXISTS `pts` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pts`;

-- Dumping structure for table pts.actualtripstopinfo
CREATE TABLE IF NOT EXISTS `actualtripstopinfo` (
  `TripNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `StopNumber` int(11) NOT NULL,
  `ScheduledArrivalTime` time DEFAULT NULL,
  `ActualStartTime` time DEFAULT NULL,
  `ActualArrivalTime` time DEFAULT NULL,
  `NumberOfPassengersIn` int(11) DEFAULT NULL,
  `NumberOfPassengersOut` int(11) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`Date`,`ScheduledStartTime`,`StopNumber`),
  KEY `FK_actualtripstopinfo_stop` (`StopNumber`),
  KEY `FK_actualtripstopinfo_tripoffering_2` (`ScheduledArrivalTime`),
  CONSTRAINT `FK_actualtripstopinfo_stop` FOREIGN KEY (`StopNumber`) REFERENCES `stop` (`StopNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_actualtripstopinfo_tripoffering` FOREIGN KEY (`TripNumber`, `Date`, `ScheduledStartTime`) REFERENCES `tripoffering` (`TripNumber`, `Date`, `ScheduledStartTime`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_actualtripstopinfo_tripoffering_2` FOREIGN KEY (`ScheduledArrivalTime`) REFERENCES `tripoffering` (`ScheduledArrivalTime`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.actualtripstopinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `actualtripstopinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `actualtripstopinfo` ENABLE KEYS */;

-- Dumping structure for table pts.bus
CREATE TABLE IF NOT EXISTS `bus` (
  `BusID` int(11) NOT NULL AUTO_INCREMENT,
  `Model` varchar(50) DEFAULT NULL,
  `Year` int(11) DEFAULT NULL,
  PRIMARY KEY (`BusID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.bus: ~0 rows (approximately)
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;

-- Dumping structure for table pts.driver
CREATE TABLE IF NOT EXISTS `driver` (
  `DriverID` int(11) NOT NULL AUTO_INCREMENT,
  `DriverName` varchar(50) DEFAULT NULL,
  `TelephoneNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DriverID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.driver: ~0 rows (approximately)
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;

-- Dumping structure for table pts.stop
CREATE TABLE IF NOT EXISTS `stop` (
  `StopNumber` int(11) NOT NULL,
  `StopAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.stop: ~0 rows (approximately)
/*!40000 ALTER TABLE `stop` DISABLE KEYS */;
/*!40000 ALTER TABLE `stop` ENABLE KEYS */;

-- Dumping structure for table pts.trip
CREATE TABLE IF NOT EXISTS `trip` (
  `TripNumber` int(11) NOT NULL,
  `StartLocationName` varchar(50) DEFAULT NULL,
  `DestinationName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.trip: ~0 rows (approximately)
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;

-- Dumping structure for table pts.tripoffering
CREATE TABLE IF NOT EXISTS `tripoffering` (
  `TripNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `ScheduledArrivalTime` time DEFAULT NULL,
  `DriverID` int(11) DEFAULT NULL,
  `BusID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`Date`,`ScheduledStartTime`),
  KEY `ScheduledArrivalTime` (`ScheduledArrivalTime`) USING BTREE,
  KEY `FK_tripoffering_driver` (`DriverID`),
  KEY `FK_tripoffering_bus` (`BusID`),
  CONSTRAINT `FK_tripoffering_bus` FOREIGN KEY (`BusID`) REFERENCES `bus` (`BusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tripoffering_driver` FOREIGN KEY (`DriverID`) REFERENCES `driver` (`DriverID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tripoffering_trip` FOREIGN KEY (`TripNumber`) REFERENCES `trip` (`TripNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.tripoffering: ~0 rows (approximately)
/*!40000 ALTER TABLE `tripoffering` DISABLE KEYS */;
/*!40000 ALTER TABLE `tripoffering` ENABLE KEYS */;

-- Dumping structure for table pts.tripstopinfo
CREATE TABLE IF NOT EXISTS `tripstopinfo` (
  `TripNumber` int(11) NOT NULL,
  `StopNumber` int(11) NOT NULL,
  `SequenceNumber` int(11) DEFAULT NULL,
  `DrivingTime` time DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`StopNumber`),
  KEY `FK_tripstopinfo_stop` (`StopNumber`),
  CONSTRAINT `FK_tripstopinfo_stop` FOREIGN KEY (`StopNumber`) REFERENCES `stop` (`StopNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tripstopinfo_trip` FOREIGN KEY (`TripNumber`) REFERENCES `trip` (`TripNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.tripstopinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `tripstopinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tripstopinfo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
