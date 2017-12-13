/*
 * Chapter No. 08      	Group Project: Airline Reservation System
 * File Name:          	ResSysDriver.h
 * Programmer:         	Angel Tapia, Sahni Coe, David whang, ...
 * Date Last Modified: 	November 30, 2017
 *
 * Problem Statement:	This program...
 *
 * Algorithm:
 * 1.Create a ReservationSystem class:
 * 	-Create data member:
 * 	 	int seats[10] 			//represents each seat for a passenger
 * 	 	string currentName 		//represents the current passenger's name
 * 	 	int currentSeatClassA 	//current available set in First class
 * 	 	int currentSeatClassB	//current available set in Economy class
 * 	 	int selected			//current selected class
 * 	-Create a default Constructor:
 * 	 	Initialize each index of seats[i] = 0
 * 	-Create a destructor
 * 	-Create a function setClassA that return a bool value:
 *
 *
 *
 * 	-Create a function setClassB that return a bool value:
 *
 *
 *
 * 	-Create a function printReceipt:
 *
 *
 *
 * 	-Create a function setName:
 *
 *
 *
 * 	-Create a function setSeat:
 *
 *
 *
 *	-Create a function printSeats:
 *
 *
 *
 * 	-Create a function calcOccupied that return an int value:
 *
 *
 * 2.ResSysDriver class that contains main():
 * 	-
 * 	-
 * 	-
 * 	-
 * 	-
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>			 // input and output stream of data.
 * #include "ReservationSystem.h"// makes ReservationSystem class available
 */
#include <iostream>
#include "ReservationSystem.h"

using namespace std;

int main()
{

	ReservationSystem sys;
	for (int i = 0; i < 11; i++)
	{
		cout << "The airline is " << (sys.calcOccupied() * 10)
				<< "% occupied.\n";
		sys.printSeats();
		sys.setName();
		sys.setSeat();
		sys.printReceipt();
		cout << endl;
	}

	return 0;
}
