# Transit-System
Transit System with GUI and database connectivity with JDBC.

## Design Specifications
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

## Graphical User Interfaces
### Create Connection
![image](https://user-images.githubusercontent.com/43283288/199344960-54046e0d-d1c8-4607-a807-24f590d8a14f.png)
### Main Menu GUIs
![image](https://user-images.githubusercontent.com/43283288/199193521-8254525c-fd66-4e52-aef3-5503f8029ed0.png)
![image](https://user-images.githubusercontent.com/43283288/199194393-f7374917-7a3b-49bb-bbfb-ab7e3e97dc52.png)
![image](https://user-images.githubusercontent.com/43283288/199194417-acae7d04-adcd-43ce-ba69-6f3779b34c9b.png)
![image](https://user-images.githubusercontent.com/43283288/199194434-0bedf0cf-f117-4eb1-8d97-8f4bc9cd5145.png)
![image](https://user-images.githubusercontent.com/43283288/199194460-d75fdd35-ce43-4905-a21d-a72601595d30.png)
### Trip Offering GUIs
![image](https://user-images.githubusercontent.com/43283288/199194485-f5608295-e994-4113-9be8-1189010b9d7a.png)
![image](https://user-images.githubusercontent.com/43283288/199194521-0276870c-1e36-4abd-bf8a-7309ea9df3a5.png)
![image](https://user-images.githubusercontent.com/43283288/199194564-f1495121-b14f-4c02-b6be-cb968360069c.png)
### Driver GUIs
![image](https://user-images.githubusercontent.com/43283288/199194634-5a738e5a-4c52-4d84-9a18-e282713f9023.png)
![image](https://user-images.githubusercontent.com/43283288/199194651-d356a4e8-3a04-4415-9991-b9b62b2646c5.png)
![image](https://user-images.githubusercontent.com/43283288/199194663-139566ca-53d5-43ce-baba-f054e5d47d00.png)
### Bus GUIs
![image](https://user-images.githubusercontent.com/43283288/199194689-2178551e-ec62-46f7-9c0e-00c22059823a.png)
![image](https://user-images.githubusercontent.com/43283288/199194700-c3c1b0e4-a244-48b8-a339-2f2338383322.png)
![image](https://user-images.githubusercontent.com/43283288/199194738-7ccd927a-f328-4791-9153-61943738f324.png)




