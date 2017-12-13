/**
 * Chapter No. 08      	Exercise No. 16
 * File Name:          	ArrayOfPayrollObject.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 27, 2017
 *
 * Problem Statement:	This program reads a file that
 * 						contains data of employees hours worked
 * 						and pay rate. Then,  it calculates
 * 						the gross pay, and displays detail
 * 						of each employee
 * Algorithm:
 *	1.Crate a PayRoll class:
 *		-Create instance variable:
 *			double hourlyPayRate, hoursWorked;
 *		-Create default Constructor:
 *			Initializes hourlyPayRate and hoursWorked
 *		-Create Constructor that takes in hourlyPayRate and hoursWorked
 *			Set hourlyPayRate and hoursWorked to given values
 *		-Create destructor
 *		-Create Setters and Getters for each instance variables
 *		-Create a function getGrossPay that calculates and returns
 *		 hourlyPayRate * hoursWorked
 *	2.Create a tester class that contains main():
 *		-Create an array of 7 PayRoll objects
 *		-Open Data file stream:
 *			if data file == false: display "error opening dataFile"
 *			else:
 *				Create a for loop to real 7 payrolls
 *					Read hours worked from file
 *					Read payrate from file
 *					Set employees[i].setHourlyPayRate(payrate)
 *					Set employees[i].setHoursWorked(hours)
 *					Display hours, pay, and grosspay of each employee
 *		-Close data file stream
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 * #include <iomanip>	 // Allows to format the output data.
 * #include <fstream>   //reads/writes from/to a file
 *
 */

#include <iostream>
#include <iomanip>
#include <fstream>
#include "PayRoll.h"

using namespace std;

int main()
{
	PayRoll employees[7];//Create an array of 7 Payroll object
	ifstream datafile;//Open data file
	datafile.open("payroll.dat");

	if (!datafile)//is file stream open?
	{
		cout << "ERROR: payroll.dat\n";
	}
	else//yes, file stream is open
	{
		for (int i = 0; i < 7; i++)
		{
			double hours;
			double payrate;

			datafile >> hours;//read hours
			datafile >> payrate;//read parrate
			employees[i].setHourlyPayRate(payrate);//set hours
			employees[i].setHoursWorked(hours);//set pay rate

			cout << fixed << setprecision(2);//Display details
			cout << "Employee#" << (i + 1) << endl;
			cout << "HOURS: " << hours << "  PAY: $" << payrate
					<< "   GROSS_PAY: $" << employees[i].getGrossPay() << endl;
		}
	}
	datafile.close();
	return 0;
}
