/**
 * Chapter No. 08      	Exercise No. 08
 * File Name:          	RainfallStatistics.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 21, 2017
 *
 * Problem Statement:	This program analyzes a year's worth
 * 						of rainfall data and display a
 * 						summary rainfall report.
 * Algorithm:
 *	1.Create function prototypes for:
 *		void getData(const string months[], double rainData[], int num_of_months);
 *		double totalRainfall(double rainData[], int months);
 *		double averageRainfall(double rainData[], int months);
 *		int driestMonth(double rainData[], int months);
 *		int wettestMonth(double rainData[], int months);
 *	2.Implement main():
 *		-Create a constant int NUM_OF_MONTHS = 12
 *		-Create a string months[] that contains the names of the months
 *		-Create double rainData[] that will store the rain data for each month
 *		-Create variables for:
 *			double totalRain for total year's worth of rainfall
 *			double averageRain for the average year's worth of rainfall
 *			int driest for the driest month
 *			int wettest for the wettest month
 *		-Call the function getData to store data in rainDat[]
 *		-Calculate totalRain by calling the totalRainfall function
 *		-Calculate averageRain by calling the averageRainfall function
 *		-Calculate driest by calling the driestMonth function
 *		-Calculate wettest by calling the wettestMonth function
 *		-Display: total, average, driest, and wettest
 *	3.Implement the function getData:
 *		-Request input from the user
 *		-Create a for-loop that loops months times:
 *			Request data for each month
 *			and validate data using a while loop
 *			store data in each index of rainData[]
 *	4.Implement the function totalRainfall:
 *		-Create a double total to store the total rainfall data
 *		-Create a for loop to add all of the
 *		 rainfall data in rainDat[] to total
 *		-Return total
 *	5.Implement the function averageRainfall:
 *		-Create a double total to store the total rainfall data
 *		-Call the function totalRainfall and store result in total
 *		-return total / months
 *	6.Implement the function driestMonth:
 *		-Create a int driest variable that stores the index of the driest month
 *		-Create a for loop to find driest  month:
 *			driest = rainData[i] < rainData[driest] ? i : driest;
 *		-return driest
 *	7.Implement the function wettestMonth:
 *		-Create a int driest variable that stores the index of the wettest month
 *		-Create a for loop to find wettest  month:
 *			wettest = rainData[i] < rainData[wettest] ? i : wettest;
 *		-return wettest
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 * #include <string>    // allows the use of the string class.
 *
 */
#include <iostream>
#include <string>
using namespace std;

void getData(const string months[], double rainData[], int num_of_months);
double totalRainfall(double rainData[], int months);
double averageRainfall(double rainData[], int months);
int driestMonth(double rainData[], int months);
int wettestMonth(double rainData[], int months);

/*********************************************************
 * analyzes a year's worth of rainfall data and
 * display a summary rainfall report.
 *********************************************************/
int main()
{
	//total months to be analyzed
	const int NUM_OF_MONTHS = 12;

	//months names
	string months[] = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };

	//array for ranin data
	double rainData[NUM_OF_MONTHS];

	//Variables for: total, average, driest, wettest
	double totalRain = 0;
	double averageRain = 0.0;
	int driest = 0;
	int wettest = 0;

	//Calculate total, average, driest, and wettest
	getData(months, rainData, NUM_OF_MONTHS);
	totalRain = totalRainfall(rainData, NUM_OF_MONTHS);
	averageRain = averageRainfall(rainData, NUM_OF_MONTHS);
	driest = driestMonth(rainData, NUM_OF_MONTHS);
	wettest = wettestMonth(rainData, NUM_OF_MONTHS);

	//Display: total, average, driest, and wettest
	cout << "\n   2017 Rain Report for Neversnows County\n\n";
	cout << "Total Rainfall: " << totalRain << endl;
	cout << "Average Monthly Rainfall: " << averageRain << " inches." << endl;
	cout << "The least rain felt in " << months[driest] << " with "
			<< rainData[driest] << " inches." << endl;
	cout << "The most rain felt in " << months[wettest] << " with "
			<< rainData[wettest] << " inches." << endl;

	return 0;
}

/*********************************************************
 * Request rainfall data from the user for num_of_months months
 *********************************************************/
void getData(const string months[], double rainData[], int num_of_months)
{
	cout << "Please enter total rainfall ";
	cout << "data for " << num_of_months << " months:\n";

	for (int i = 0; i < num_of_months; i++) //loops months times
	{
		cout << "Month " << months[i] << ": ";
		cin >> rainData[i];

		while (rainData[i] < 0 || cin.fail()) //validates input
		{
			cin.clear();
			fflush(stdin);
			cout << "Please enter a non-negative number: ";
			cin >> rainData[i];
		}
	}
}

/*********************************************************
 * Adds all of the given rainfall data and returns result
 *********************************************************/
double totalRainfall(double rainData[], int months)
{
	double total = 0;
	for (int i = 0; i < months; i++) //loops months times
	{
		total += rainData[i];
	}
	return total;
}

/*********************************************************
 * Calculates the average rainfall data and returns result
 *********************************************************/
double averageRainfall(double rainData[], int months)
{
	double total = totalRainfall(rainData, months);
	return total / static_cast<double>(months);
}

/*********************************************************
 * Finds the driest month from rainData and returns result
 *********************************************************/
int driestMonth(double rainData[], int months)
{
	int driest = 0;
	for (int i = 0; i < months; i++) //loops months times
	{
		driest = rainData[i] < rainData[driest] ? i : driest;
	}
	return driest;
}

/*********************************************************
 * Finds the wettest month from rainData and returns result
 *********************************************************/
int wettestMonth(double rainData[], int months)
{
	int wettest = 0;
	for (int i = 0; i < months; i++) //loops months times
	{
		wettest = rainData[i] > rainData[wettest] ? i : wettest;
	}
	return wettest;
}

