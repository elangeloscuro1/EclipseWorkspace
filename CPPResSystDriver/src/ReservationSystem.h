/*
 * Chapter No. 08      	Group Project: Airline Reservation System
 * File Name:          	ReservationSystem.h
 * Programmer:         	Angel Tapia, Sahni Coe, David whang, ...
 * Date Last Modified: 	November 30, 2017
 */
#ifndef RESERVATIONSYSTEM_H
#define RESERVATIONSYSTEM_H

#include <iostream>
#include <string>

using namespace std;

class ReservationSystem
{
public:
	/** Default constructor */
	ReservationSystem()
	{
		for (int i = 0; i < 10; i++)
		{
			seats[i] = 0;
		}
		currentName = "null";
		currentSeatClassA = 0;
		currentSeatClassB = 5;
		selected = 0;
	}
	/** Destructor */
	~ReservationSystem()
	{

	}

	/**
	 * Assigns seat in First Class
	 * Increments currentSeatClassA
	 * Returns true if seat is available; false otherwise
	 */
	bool setClassA()
	{
		if (currentSeatClassA < 5)
		{
			seats[currentSeatClassA++] = 1;
			//names[currentSeatClassA - 1] = currentName;//is needed?
			return true;
		}
		return false;
	}

	/**
	 * Assigns seat in Economy Class
	 * Increments currentSeatClassB
	 * Returns true if seat is available; false otherwise
	 */
	bool setClassB()
	{
		if (currentSeatClassB < 10)
		{
			seats[currentSeatClassB++] = 1;
			//names[currentSeatClassB - 1] = currentName;//is needed?
			return true;
		}
		return false;
	}

	/**
	 * if selected First class or economy: Prints out name, flight section
	 * if selected is unavailable or flight is full: prints out next flight
	 */
	void printReceipt()
	{
		if (selected != 0)
		{
			cout << "Your name is " << currentName << endl;
			cout << "Your seat # is ";
			if (selected == 1)
			{
				cout << currentSeatClassA;
				cout << ". You are sat in First Class\n";
				return;
			}
			cout << currentSeatClassB;
			cout << ". You are sat in Economy Class\n";
			return;
		}
		cout << "Next flight leaves in 2 hours.\n";

	}

	/**
	 * Sets Current passenger name
	 */
	void setName() //supports empty string?
	{
		cin.clear();
		fflush(stdin);
		selected = 0;
		cout << "Enter Name: ";
		getline(cin, currentName);
	}

	/**
	 *	If flight is not full:
	 *	Asks the to pick a flight category: First/Economy class
	 *	If The selected class is unavailable: offer available class
	 *	Reset selected for next passenger
	 */
	void setSeat()
	{
		if (calcOccupied() >= 10) // >= 100 ?
		{
			cout << "The airline is currently full.\n ";
			return;
		}
		cout << "Please enter '1' for \"First-Class\" or '2' for \"Economy\": ";
		cin >> selected;
		while (selected < 1 || selected > 2 || cin.fail())
		{
			cin.clear();
			fflush(stdin);
			cout << "Please enter 1 or 2: ";
			cin >> selected;
		}

		if (selected == 1 && !setClassA())
		{
			char isFine = ' ';
			cout << "Would \"Economy\" be fine (y/n): ";
			cin >> isFine;
			if ((isFine == 'y' || isFine == 'Y') && setClassB())
			{
				selected = 2;
				return;
			}
			selected = 0;
		}
		else if (selected == 2 && !setClassB())
		{
			char isFine = ' ';
			cout << "Would \"First Class\" be fine (y/n): ";
			cin >> isFine;
			if ((isFine == 'y' || isFine == 'Y') && setClassA())
			{
				selected = 1;
				return;
			}
			selected = 0;
		}
	}

	/**
	 * Calculates the number of occupied seats
	 */
	int calcOccupied()
	{
		return (currentSeatClassA + currentSeatClassB - 5); // * 10;
	}
	/**
	 * Displays the actual seats filled
	 */
	void printSeats()
	{
		cout << "SEATS MAP: ";
		for (int i = 0; i < 10; i++)
		{
			cout << "[" << seats[i] << "]";
		}
		cout << endl;
	}

private:
	int seats[10];			//represents each seat for a passenger
	//string names[10];//is needed?
	string currentName;		//represents the current passenger's name
	int currentSeatClassA;	//current available set in First class
	int currentSeatClassB;	//current available set in Economy class
	int selected;			//current selected class

};

#endif // End of RESERVATIONSYSTEM_H
