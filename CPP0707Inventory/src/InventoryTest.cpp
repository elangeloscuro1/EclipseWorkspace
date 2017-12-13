/*
 Chapter No. 07      	Exercise No. 07
 File Name:          	ProgrammingChallenge07.cpp
 Programmer:         	Angel Tapia
 Date Last Modified: 	November 14, 2017

 Problem Statement:	This program Tests an Inventory class
					by letting the user enter as many items in
					an inventory data base.
 Algorithm:
 1.Create a Inventory Inventory class
 	 -Create instance variable:
 		int itemNumber for category
		int quantity for quantity of items
		double cost for cost per item
		double totalCost for total cost of all of the items
	-Create an empty constructor that initializes all
		of the instance variable to zero
	-Create a constructor that receives and initializes
		itemNumber, quantity, and cost.
		this constructor call the  setTotalCost function to set totalCost:
		setTotalCost(itemNumber * cost)
	-Create Setters and Getters of each field
2.Create a tester file that contains main:
	-Create function prototypes for:
		double validate(double min, double max); that validates double inputs
		int validate(int min, int max); that validates integers inputs
	-main():
		Create a do-while loop that:
			Asks the user to enter an Item number
			Asks the user to enter an Item quantity
			Asks the user to enter an Item cost
			Creates and instance of the class Inventory
				and displays iteNumber, quantity, and cost
			Asks the user if he/she wants to enter a new item:
				if no: break the loop
		Display the end of the program
	-Implement the double validate(double min, double max) functions:
		create a while loop that loop if input < min || input > max
		return input
	-Implement the int validate(int min, int max) functions:
		create a while loop that loop if input < min || input > max
		return input
 */
#include <iostream>
#include <iomanip>
#include "Inventory.h"
using namespace std;

double validate(double min, double max); //validates double inputs
int validate(int min, int max); //validates integers inputs

int main()
{
	cout << fixed << showpoint << setprecision(2);
	cout << "==== Inventory ====" << endl;

	char flag = ' ';

	do
	{
		cout << "Enter Item Number: ";
		int itemNumber = validate(0, 100000);
		cout << "Enter Item Quantity: ";
		int quantity = validate(0, 10000);
		cout << "Enter Item Cost: ";
		double cost = validate(0, 1000);

		Inventory inventory(itemNumber, quantity, cost);

		cout << "\nYou have entered the following information:";
		cout << "\nItem Number: " << inventory.getItemNumber();
		cout << "\nItem Quantity: " << inventory.getQuantity();
		cout << "\nItem Cost: $" << inventory.getCost();
		cout << "\nTotal Cost: $" << inventory.getTotalCost();

		cin.clear();
		fflush(stdin);
		cout << "\n\nNew item(y/n)?:";
		cin >> flag;
		if (flag == 'n')
		{
			break;
		}
	}
	while (true);

	cout << "\nThank you for using Inventory!!\n" << endl;
	return 0;
}

/**validates double inputs*/
double validate(double min, double max)
{
	double input;
	cin >> input;
	while (input < min || input > max || cin.fail())
	{
		cin.clear();
		fflush(stdin);
		cout << "Please enter [" << min << " - " << max << "] : ";
		cin >> input;
	}
	return input;
}
/**validates integer inputs*/
int validate(int min, int max)
{
	int input;
	cin >> input;
	while (input < min || input > max || cin.fail())
	{
		cin.clear();
		fflush(stdin);
		cout << "Please enter [" << min << " - " << max << "] : ";
		cin >> input;
	}
	return input;
}
