/*
	 Chapter No. 00      	Exercise No. 00
	 File Name:          	FileName.cpp
	 Programmer:         	Angel Tapia
	 Date Last Modified: 	November 00, 2017

	 Problem Statement:	This program...
	 Algorithm:
	 1.

	 Classes needed and Purpose (Input, Processing, Output):
	 #include <iostream>  // input and output stream of data.
	 #include <string>    // allows the use of the string class.
	 #include <iomanip>	 // Allows to format the output data.
 */

#include <iostream>
#include <string>
#include <iomanip>

#include "VendingMachine.h"

using namespace std;

int main()
{

	const string MNMS_NAME = "m&ms";
	const int MNMS_QTY = 3;
	const double MNMS_COST = 1.25;

	const string POT_CHIPS_NAME = "Potato Chips";
	const int POT_CHIPS_QTY = 5;
	const double POT_CHIPS_COST = 1.75;

	const string DORITOS_NAME = "Doritos";
	const int DORITOS_QTY = 4;
	const double DORITOS_COST = 2.25;

	const string COOKIES_NAME = "Chocolate Cookies";
	const int COOKIES_QTY = 2;
	const double COOKIES_COST = 2.50;

	const double INITIAL_CASH = 5.00;

	VendingMachine vendingMachine(INITIAL_CASH);
	vendingMachine.setDispenser1(1, MNMS_NAME, MNMS_QTY, MNMS_COST);
	vendingMachine.setDispenser2(2, POT_CHIPS_NAME, POT_CHIPS_QTY,
			POT_CHIPS_COST);
	vendingMachine.setDispenser3(3, DORITOS_NAME, DORITOS_QTY, DORITOS_COST);
	vendingMachine.setDispenser4(4, COOKIES_NAME, COOKIES_QTY, COOKIES_COST);

	char test = 'y';

	while (test == 'y')
	{
		vendingMachine.vendItem();
		cout << "Do you want to continue (y/n):" ;
		cin >> test ;
		cout << endl ;
	}

	cout << "End of Test" << endl;
	return 0;
}

