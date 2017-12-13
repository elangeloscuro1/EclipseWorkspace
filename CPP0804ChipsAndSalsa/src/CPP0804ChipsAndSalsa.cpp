/**
 * Chapter No. 08      	Exercise No. 04
 * File Name:          	ChipsAndSalsa.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 21, 2017
 *
 * Problem Statement:	This program keeps track the number
 * 						of jars sold of five different
 * 						salsa type and displays the most sold.
 * Algorithm:
 * -Create a constant: PRODUCT_COUNT = 5 for number of salsas
 * -Create a string producName array and initialize it with 5 salsas:
 * 	string productName[] = {"mild", "medium", "sweet", "hot", "zesty"}
 * -Create an int totalSold array of the size PRODUCT_COUNT
 *  that will store the number of jars sold pf each salsa
 * -Set number of jars sold :
 * 		Create a for loop that loop PRODUCT_COUNT times:
 * 			Request input form the user
 * 			Store input to totalSold[i]
 * 			Set mostSold to totalSold[i] if totalSold[i] > totalSold[mostSold]
 * -Displays slasa name and jars sold:
 * 		Create a for loop that loop PRODUCT_COUNT times:
 * 			print out productName[i] and totalSold[i]
 * -Displays most sold
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 * #include <string>    // allows the use of the string class.
 * #include <iomanip>	 // Allows to format the output data.
 *
 */
#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

int main()
{
	const int PRODUCT_COUNT = 5;//number of salsas
	string productName[] = { "mild", "medium", "sweet", "hot", "zesty" };
	int totalSold[PRODUCT_COUNT];//number of jars sold
	int mostSold = 0;//the most sold

	//Sets total sold of each salsa type and finds the most sold
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		cout << "Enter # of jars sold of  " << productName[i] << " salsa: ";
		cin >> totalSold[i];
		mostSold = totalSold[i] > totalSold[mostSold] ? i : mostSold;
	}

	//Displays slasa name and jars sold
	cout << endl << setw(15) << left << "SALSA_TYPE" << "TOTAL_JARS\n";
	cout << "----------------------------\n";
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		cout << setw(15) << productName[i] << totalSold[i] << endl;
	}

	//Displays most sold
	cout << "----------------------------\n";
	cout << productName[mostSold] << " salsa has the best sell!\n";
	return 0;
}
