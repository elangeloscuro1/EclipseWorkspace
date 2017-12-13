/**
 * Chapter No. 08      	Exercise No. 15
 * File Name:          	DriversLicenseExam.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 27, 2017
 *
 * Problem Statement:	This program helps to grade
 * 						driver's license tests
 * Algorithm:
 * 1.Create a TestGrader class:
 * 	-Create instance variable char answers[20]
 * 	 to store an answer key
 * 	-Create default constructor and destructor
 *  -Create a setKey function that copies an answer key
 * 	 passed as a parameter (a string of 20 chars):
 * 	 	for 20 times copy answers[i] = ansKey[i]
 * 	-Create a helper function int2strint that
 * 	 receives an int values and convert it to string:
 * 	 	Create a string castInt = ""
 * 	 	if int value  == 0:
 * 	 		return "0"
 * 	 	else:
 * 	 		while int value  > 0,
 * 	 			shift right int value
 * 	 	concatenate to castInt cast as char
 * 	 	return castInt
 *	-Create a grade function that receives a char array that
 *	 contains the test taker's answer:
 *		Create local variables:
 *			int countRight// counts the number of rights
 *			string grade//concatenates the result to be returned
 *			string wrongs//concatenates a list of wrong answers
 *		Create a for loop that compares each answer with the answer key:
 *			if answers[i] == userAnswers[i]: countRights++;
 *			else concatenate list of wrongs (use the int2string function)
 *		If countRights >= 15: concatenate "You have passed" to grade
 * 	   	Else: concatenate "You failed"
 * 	   	If countRights == 20: concatenate "You got a perfect score"
 * 	   	Else: concatenate the number of rights and the list of wrong
 * 	   	Return grade
 * 2.Create a tester program that contains main():
 * 	-Create variables:
 * 		answerKey that contains the correct answer for the exam
 * 		char answer[20] to store the answers from the user
 * 		char another that flags the program to stop
 * 	-Create an instance of the class TestGrader
 * 	-call the function setKey and pass answerKey as the parameter
 * 	-Create a do-while loop that allows the user to take more that one test:
 * 		Create a for loop to request 20 answers from the user:
 * 	 	Create an inner loop to validate each answer:
 * 	 	loop while answer[i] != 'A', 'B', 'C' or 'D'
 *		Call the function grade to compare the answers
 *	 	and use it to display pass/fail, numbers
 *	 	of rights, and list of wrongs
 *	 	Exit the loop if there are no more test to be taken.
 *	 -Display end of the program
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 * #include <string>    // allows the use of the string class.
 *
 */

#include <iostream>
#include <string>
#include "TestGrader.h"

using namespace std;

int main()
{
	string answersKey = "BDAACABACDBCDADCCBDA"; //answer key
	char answers[20]; //user answers

	TestGrader test; //instance of test grader
	test.setKey(answersKey); //set answer key

	char another = ' ';
	do
	{
		for (int i = 0; i < 20; i++)//get answer from user
		{
			cout << "Please enter answer for question #" << (i + 1) << ":";
			cin >> answers[i];

			while (!(answers[i] == 'A' || answers[i] == 'B' //validate answer
			|| answers[i] == 'C' || answers[i] == 'D'))
			{
				cin.clear();
				fflush(stdin);
				cout << "Please enter Upper case: A, B, C, or D: ";
				cin >> answers[i];
			}
		}
		cout << endl << test.grade(answers) << endl;
		cout << "\nDo you want to take the test again(y/n)?";
		cin >> another ;
	}
	while (another == 'y' || another == 'Y');

	//display pass/fail and numbers of rights and wrongs
	cout <<"End of the Program!"<< endl;
	return 0;
}
