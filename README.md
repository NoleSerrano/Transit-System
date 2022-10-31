# Transit-System
Transit System with GUI and database connectivity.

## Specifications
Use the following tables to design and implement the Transit System using any
database product and JDBC.

- **Trip** (<ins>TripNumber</ins>, StartLocationName, DestinationName)
- **TripOffering** (<ins>TripNumber, Date, ScheduledStartTime</ins>, SecheduledArrivalTime, DriverID, BusID)
- **Bus** (<ins>BusID</ins>, Model, Year)
- **Driver** (<ins>DriverID</ins>, DriverName, DriverTelephoneNumber)
- **Stop** (<ins>StopNumber</ins>, StopAddress)
- **ActualTripStopInfo** (<ins>TripNumber, Date, ScheduledStartTime, StopNumber</ins>, SecheduledArrivalTime, ActualStartTime, ActualArrivalTime, NumberOfPassengerIn, NumberOf PassengerOut)
- **TripStopInfo** (<ins>TripNumber, StopNumber</ins>, SequenceNumber, DrivingTime)

The system should deal with at least the following transactions:
1. Display the schedule of all trips for a given StartLocationName and DestinationName, and Date. In addition to these attributes, the schedule includes: ScheduledStartTime, ScheduledArrivalTime , DriverID, and BusID. // from tables Trip and TripOffering virtual table
2. Edit the schedule i.e. edit the table of Trip Offering as follows:
- Delete a trip offering specified by Trip#, Date, and ScheduledStartTime;
- Add a set of trip offerings assuming the values of all attributes are given (the software asks if you have more trips to enter);
- Change the driver for a given Trip offering (i.e given TripNumber, Date, ScheduledStartTime);
- Change the bus for a given Trip offering.
3. Display the stops of a given trip (i.e. the attributes of the table TripStopInfo).
4. Display the weekly schedule of a given driver and date. // from TripOffering and Driver virtual table
5. Add a driver. (Delete a driver?)
6. Add a bus.
7. Delete a bus.
8. Record (insert) the actual data of a given trip offering specified by its key. The actual data include the attributes of the table ActualTripStopInfo.

Test your program using several test data for the above transactions.
Please submit a hard copy of your program and a printout of all test data and its output in a large envelope. 

