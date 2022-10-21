-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.10-MariaDB - mariadb.org binary distribution
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
  `StopNumber` int(11) NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `ScheduledArrivalTime` time DEFAULT NULL,
  `ActualStartTime` time DEFAULT NULL,
  `ActualArrivalTime` time DEFAULT NULL,
  `NumberOfPassengersIn` int(11) DEFAULT NULL,
  `NumberOfPassengersOut` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table pts.bus
CREATE TABLE IF NOT EXISTS `bus` (
  `BusID` int(11) NOT NULL,
  `Model` varchar(50) DEFAULT NULL,
  `Year` int(11) DEFAULT NULL,
  PRIMARY KEY (`BusID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table pts.driver
CREATE TABLE IF NOT EXISTS `driver` (
  `DriverID` int(11) NOT NULL,
  `DriverName` varchar(50) DEFAULT NULL,
  `DriverTelephoneNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DriverID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table pts.stop
CREATE TABLE IF NOT EXISTS `stop` (
  `StopNumber` int(11) NOT NULL,
  `StopAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table pts.trip
CREATE TABLE IF NOT EXISTS `trip` (
  `TripNumber` int(11) NOT NULL,
  `StartLocationName` varchar(50) DEFAULT NULL,
  `DestinationName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table pts.tripoffering
CREATE TABLE IF NOT EXISTS `tripoffering` (
  `TripNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `ScheduledArrivalTime` time DEFAULT NULL,
  `DriverID` int(11) DEFAULT NULL,
  `BusID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

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

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
