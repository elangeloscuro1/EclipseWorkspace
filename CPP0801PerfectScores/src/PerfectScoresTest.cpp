/**
 * Chapter No. 08      	Exercise No. 01
 * File Name:          	PerfectScores.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 21, 2017
 *
 * Problem Statement:	This program uses an array of the
 * 						array of the int to stores the
 * 						scores (1-100) entered by the user
 * 						and counts the perfect scores.
 * Algorithm:
 * 1.Declare the instance prototype:
 * 		int perfectScore(int scoreArray[], int arraySize);
 * 		-scoreArray is the array to store the score values
 * 		-arraySize is the maximum number of values for scoreArray
 * 2.main function:
 *		-Create a constant for ARRAY_SIZE = 20
 *		-Create a scores array of the type int of size ARRAY_SIZE
 *		-Create an int countPerfect that will store the count
 *		 of perfect scores entered by the user.
 *		-Ask the user to enter 20 integer test scores (0-100)
 *		-Call the function perfectScore and pass scores
 *		 and size as parameters
 *		-Display the number of perfect score entered by the user
 * 3.Implement the function perfectScore:
 * 		-create an int countPerfect to count the perfect
 * 		 scores entered by the user
 * 		-Create a for loop that loops arraySize times:
 * 			Validate input data by using an inner while loop
 * 			Store data to scores[i]
 * 			Increment countPerfect if dataIn = 100
 * 		-Return countPerfect
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 */
#include <iostream>
using namespace std;

//sets values and counts perfect scores
int perfectScore(int scoreArray[], int arraySize);

/**
 * Tests the perfectScore function
 */
int main()
{
	const int ARRAY_SIZE = 20;//max array size
	int scores[ARRAY_SIZE];//stores scores
	int countPerfect;//stores perfect scores count

	//Ask the user to enter 20 integer test scores (0-100)
	cout << "Please Enter 20 integer test scores (0-100): " << endl;

	//Call the function perfectScore
	countPerfect = perfectScore(scores, ARRAY_SIZE);

	//display the number of perfect score entered by the user
	cout << "You entered " << countPerfect << " perfect score(s)." << endl;
	return 0;
}

/**
 * perfectScore sets values to an array of the
 * type int, by asking values to the user and
 * counts and returns the perfect score entered.
 */
int perfectScore(int scores[], int arraySize)
{
	int countPerfect = 0;
	for (int i = 0; i < arraySize; i++)//loops arraySize times
	{
		cout << "Score " << (i + 1) << ": ";
		cin >> scores[i];

		while (scores[i] < 0 || scores[i] > 100 || cin.fail())
		{//validates input
			cin.clear();
			fflush(stdin);
			cout << "Please enter a number from 0 to 100: ";
			cin >> scores[i];
		}
		countPerfect += scores[i] == 100 ? 1 : 0;//counts perfect scores
	}
	return countPerfect;
}
