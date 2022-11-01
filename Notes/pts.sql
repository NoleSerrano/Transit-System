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
  CONSTRAINT `FK_actualtripstopinfo_stop` FOREIGN KEY (`StopNumber`) REFERENCES `stop` (`StopNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.actualtripstopinfo: ~0 rows (approximately)
/*!40000 ALTER TABLE `actualtripstopinfo` DISABLE KEYS */;
INSERT INTO `actualtripstopinfo` (`TripNumber`, `Date`, `ScheduledStartTime`, `StopNumber`, `ScheduledArrivalTime`, `ActualStartTime`, `ActualArrivalTime`, `NumberOfPassengersIn`, `NumberOfPassengersOut`) VALUES
	(1, '2000-06-09', '01:00:00', 7, '02:00:00', '01:02:00', '01:29:00', 13, 13),
	(1, '2000-06-09', '03:00:00', 7, '04:00:00', '03:02:00', '03:15:00', 13, 2);
/*!40000 ALTER TABLE `actualtripstopinfo` ENABLE KEYS */;

-- Dumping structure for table pts.bus
CREATE TABLE IF NOT EXISTS `bus` (
  `BusID` int(11) NOT NULL AUTO_INCREMENT,
  `Model` varchar(50) DEFAULT NULL,
  `Year` int(11) DEFAULT NULL,
  PRIMARY KEY (`BusID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Dumping data for table pts.bus: ~5 rows (approximately)
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` (`BusID`, `Model`, `Year`) VALUES
	(1, 'Solaris Urbino 12', 1996),
	(2, 'Solaris Urbino 12', 1999),
	(3, 'Solaris Urbino 13', 2020),
	(4, 'Solaris Urbino 13', NULL),
	(5, 'Solaris Urbino 13', 1995);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;

-- Dumping structure for table pts.driver
CREATE TABLE IF NOT EXISTS `driver` (
  `DriverID` int(11) NOT NULL AUTO_INCREMENT,
  `DriverName` varchar(50) DEFAULT NULL,
  `TelephoneNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DriverID`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;

-- Dumping data for table pts.driver: ~8 rows (approximately)
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` (`DriverID`, `DriverName`, `TelephoneNumber`) VALUES
	(1, 'Tyler Durden', '1125050123'),
	(5, 'John Tahoe', NULL),
	(6, 'John Doe', '8085523300'),
	(7, 'Gordon Moon', '8856263030'),
	(8, 'John Sholtz', '1234567890'),
	(10, 'Daniel Shink', '1231231234'),
	(97, 'Daniel Hinlen', '9098851234'),
	(98, 'Michael Bay', '5551231234');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;

-- Dumping structure for table pts.stop
CREATE TABLE IF NOT EXISTS `stop` (
  `StopNumber` int(11) NOT NULL,
  `StopAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.stop: ~18 rows (approximately)
/*!40000 ALTER TABLE `stop` DISABLE KEYS */;
INSERT INTO `stop` (`StopNumber`, `StopAddress`) VALUES
	(1, '189 Edgewater Lane'),
	(2, '9497 Spring Drive'),
	(3, '9497 Spring Drive'),
	(4, '42 Selby Road'),
	(5, '16 Cedarwood Drive'),
	(6, '954 Cross Street'),
	(7, '389 Sutor Lane'),
	(8, '9313 Forest Street'),
	(9, '50 Willow Avenue'),
	(10, '103 Kirkland Street'),
	(11, '850 Dunbar Street'),
	(12, '8778 University Road'),
	(13, '47 Peninsula Street'),
	(14, '9277 Clinton Lane'),
	(15, '686 Princeton Drive'),
	(16, '24 Creek Street'),
	(17, '381 Littleton Avenue'),
	(18, '29 Helen Drive'),
	(19, '326 Devon Avenue'),
	(20, '60 East Race St');
/*!40000 ALTER TABLE `stop` ENABLE KEYS */;

-- Dumping structure for table pts.trip
CREATE TABLE IF NOT EXISTS `trip` (
  `TripNumber` int(11) NOT NULL,
  `StartLocationName` varchar(50) DEFAULT NULL,
  `DestinationName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.trip: ~4 rows (approximately)
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` (`TripNumber`, `StartLocationName`, `DestinationName`) VALUES
	(1, 'Daystead', 'Rosedol'),
	(2, 'Readingcester', 'South Sweetford'),
	(3, 'Foxton', 'Winterside'),
	(4, 'Readingcester', 'Fishport'),
	(5, 'Highbury', 'Duckburg');
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
  KEY `FK_tripoffering_driver` (`DriverID`),
  KEY `FK_tripoffering_bus` (`BusID`),
  CONSTRAINT `FK_tripoffering_bus` FOREIGN KEY (`BusID`) REFERENCES `bus` (`BusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tripoffering_driver` FOREIGN KEY (`DriverID`) REFERENCES `driver` (`DriverID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_tripoffering_trip` FOREIGN KEY (`TripNumber`) REFERENCES `trip` (`TripNumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pts.tripoffering: ~10 rows (approximately)
/*!40000 ALTER TABLE `tripoffering` DISABLE KEYS */;
INSERT INTO `tripoffering` (`TripNumber`, `Date`, `ScheduledStartTime`, `ScheduledArrivalTime`, `DriverID`, `BusID`) VALUES
	(1, '2000-06-09', '01:00:00', '02:00:00', 1, 2),
	(1, '2000-06-09', '03:00:00', '04:00:00', 1, 2),
	(1, '2000-06-09', '05:00:00', '06:00:00', 1, 2),
	(1, '2000-06-09', '07:00:00', '08:00:00', 98, 2),
	(1, '2000-06-10', '01:00:00', '02:00:00', 1, 2),
	(1, '2000-10-27', '12:00:00', '01:00:00', 1, 1),
	(1, '2005-06-10', '12:00:00', '02:00:00', 1, 1),
	(1, '2022-11-01', '00:35:38', '02:00:00', 5, 2),
	(2, '2000-05-10', '12:56:00', '13:45:00', 1, 1);
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

-- Dumping data for table pts.tripstopinfo: ~3 rows (approximately)
/*!40000 ALTER TABLE `tripstopinfo` DISABLE KEYS */;
INSERT INTO `tripstopinfo` (`TripNumber`, `StopNumber`, `SequenceNumber`, `DrivingTime`) VALUES
	(1, 7, 2, '00:30:00'),
	(1, 18, 1, '00:30:00'),
	(1, 20, 3, '00:10:00');
/*!40000 ALTER TABLE `tripstopinfo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
