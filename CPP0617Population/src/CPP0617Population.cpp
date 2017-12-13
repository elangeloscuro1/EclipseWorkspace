/**
 * Chapter No. 06      	Exercise No. 17 (Extra Credit)
 * File Name:          	Population.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 16, 2017
 *
 * Problem Statement:	This program calculates
 * 						the projected population
 * 						in one or more years.
 *
 * Algorithm:
 * 1. Create a function calcPopulation:
 * 	-calcPopulation receives
 * 		double population for starting population
 * 		double birthRate for yearly birth rate
 * 		double deathRate for yearly death rate
 * 		calcPopulation the provided formula:
 * 			N = P(1 + B)(1 - D)
 * 			N = new population
 * 			P = starting population
 * 			B = birth rate
 *			D = death rate
 *		return population * (1 + birthRate) * (1 - deathRate)
 * 2. Create a function validate:
 * 	-calcPopulation receives an int min for a minimum valid value
 *	-Create a loop that runs until a valid value is entered
 * 	-return valid value
 * 3. Implement main:
 * 	-Declare local variables for inputs
 * 		int startPop for starting population
 * 		double birthRate for yearly birth rate
 * 		double deathRate for yearly death rate
 * 		int numYears for the number of year of calculation
 * 	-requesting valid data
 * 	-calculating and displaying population for the given year:
 * 		Create a for-loop:
 * 			Display the current year
 * 			Display starting population
 * 			Calculate projected population o f current year
 * 			Display projected population
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 *
 */
#include <iostream>
using namespace std;

/**
 * calculates the projected population in one or more years.
 */
int calcPopulation(double population, double birthRate, double deathRate)
{
	return population * (1 + birthRate) * (1 - deathRate);
}

/**
 * Validates a minimum data
 */
int validate(int min)
{
	int valid;
	cin >> valid;
	while (valid < min || cin.fail())
	{
		cin.clear();
		fflush(stdin);
		cout << "Please enter a number greater or equals to " << min << ": ";
		cin >> valid;
	}
	return valid;
}

/**
 * Runs the program that asks user for inputs and
 * calculates the projected population in one or more years.
 */
int main()
{
	//local variables for inputs
	int startPop = 200;
	double birthRate = 0;
	double deathRate = 0;
	int numYears = 0;

	//requesting valid data
	cout << "Enter starting population: ";
	startPop = validate(2);

	cout << "Enter annual birth rate: ";
	birthRate = validate(0);

	cout << "Enter annual death rate: ";
	deathRate = validate(0);

	cout << "Enter years to display: ";
	numYears = validate(1);

	//calculating and displaying population for the given year
	for (int i = 1; i <= numYears || startPop < 0; i++)
	{
		cout << "Year " << i << ": " << startPop << " ";
		startPop = calcPopulation(startPop, birthRate / startPop,
				deathRate / startPop);
		cout << startPop << endl;
	}

	return 0;
}

