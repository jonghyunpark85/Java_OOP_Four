# Java_OOP_Four

## travel management system 

### Problem
The travel agency is impressed with the look and feel of the travel management system you created in your previous assignment. However, some changes to the flight database had to be made causing the previous backend JAR file to become unusable. You are to implement a new flight reservation system that works with the graphical user interface you designed and developed previously. The new system is to replace the CPRG251A3Backend.jar. <br/><br/>
The flight reservation management system will do the following:<br/>
1.	Load the airports from the existing database.<br/>
2.	Load the flights from the existing database.<br/>
3.	Find flights<br/>
•	Travel agent can find a flight by providing the origin airport, the destination airport, and a day of the week the flight is departing.<br/>
4.	Make a reservation<br/>
•	The travel agent can make a flight reservation for a traveler. A reservation code will be generated and assigned to the traveler’s name and citizenship. <br/>
5.	Find reservations<br/>
•	A travel agent can find existing flight reservations using the reservation code, airline, and traveler name. The criteria can match any combination of the three fields.<br/>
6.	Modify a reservation<br/>
•	An existing flight reservation can be modified. The travelers name and citizenship can be updated.<br/>
•	An existing flight reservation can be soft-deleted, marking it as inactive and freeing up a seat on the flight.<br/>

#### Requirements
1.	The graphical user interface will be in working condition.<br/>
2.	You will use a random-access file to access and manipulate the reservation database.<br/>
3.	The flight, and reservation records will be stored as objects in memory.<br/>
4.	Appropriate exception handling techniques will be used to validate data.<br/>
 
#### Details
Using the graphical user interface that was created for the previous assignment, provide your own functional backend. There will be some differences between your functionality and the functionality that was provided in the previous assignment. Therefore, your user interaction handling (controller) will be modified and will not work as it is.<br/>
The UML Class Diagram shown below outlines the design of each of the manager and problem domain classes. You will need to provide the exception classes and determine where they are to be thrown. <br/>

##### Airports
The airports are included in the attached “airports.csv”. Each line in the file represents an airport with the following format:<br/>
Code,Airport<br/>
i.e.: YYC,Calgary International Airport<br/>
Store airports in an ArrayList<String>. Do not store airports in a random-access file.<br/>

##### Flights
The flights are included in the attached “flights.csv”. Each line in the file represents a flight record with the following format:<br/>
Flight Code,Departing Airport Code,Arrival Airport Code,Weekday,Time,Seats,Cost Per Seat<br/>
i.e.: CA-8346,YYC,ATL,Thursday,20:15,174,501.00<br/>
Store flight objects in an ArrayList<Flight>. Do not store flights in a random-access file.<br/><br/>
The flight code uses the format LL-DDDD (L meaning Letter, D meaning Digit) and this needs to be validated. This can be done using regular expressions or by testing each character using the Character wrapper class. A flight cannot exist without a valid flight code. In all valid flight codes, the first two letters are an abbreviation and are used to determine the full name of the airline. The following is a list of possible airlines:<br/>
•	OA – Otto Airlines<br/>
•	CA – Conned Air<br/>
•	TB – Try a Bus Airways<br/>
•	VA - Vertical Airways<br/>
 
##### Reservations
The travel agent makes the reservations using the management system. There are no predefined reservations that need to be loaded. Each reservation will be saved to a random-access file. Each reservation record will have the following:<br/>
•	Reservation code – The reservation code will be generated when a reservation is created. <br/>
•	Flight code – The flight code the reservation is for.<br/>
•	Airline – The airline who owns and operates the flight.<br/>
•	Name – The name of the traveler.<br/>
•	Citizenship – The citizenship of the traveler.<br/>
•	Cost – The cost of the reservation.<br/><br/>
You are responsible for determining the data types and record size of each reservation in the random-access file. Save the random-access file in the “res” folder.<br/>
The reservation code must use the format LDDDD, where L is either D for Domestic or I for International and DDDD is a random number between 1000 and 9999. A domestic flight is a flight going from one Canadian airport to another Canadian airport. Only the Canadian airports start with the letter Y. You are not required to check if the reservation code is unique.<br/>
The name and citizenship of the traveler do not need to meet any specific format. However, they cannot be null or an empty string. Each flight has a limited number of seats and a check is needed to determine if a flight is full or not. Use proper exception handling techniques when creating reservations.<br/>
Once a reservation is made, the only information that can be changed is the travelers name and citizenship. These must be validated to ensure not null and not empty. The reservation record must be updated in the random-access file. <br/>
To cancel an existing reservation a soft or hard delete can be used. If you are using a soft delete, the cancelled reservation will not be included in the number of seats used on a flight. You may also use a hybrid delete, meaning a new reservation can overwrite an inactive reservation. <br/>
 
