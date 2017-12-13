/*
 * Chapter No. 08      	Exercise No. 15
 * File Name:          	TestGrader.h
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 27, 2017
 */

#ifndef TESTGRADER_H
#define TESTGRADER_H

#include <string>
using namespace std;

class TestGrader
{
public:
	TestGrader()
	{

	}
	~TestGrader()
	{

	}

	/** Copies answer key*/
	void setKey(string ansKey)
	{
		for (int i = 0; i < 20; i++) //copies answer key
		{
			answers[i] = ansKey[i];
		}
	}

	/** Compares answers and return results*/
	string grade(char userAnswers[])
	{
		int countRights = 0;
		string wrongs = "";
		string grade = "";


		for (int i = 0; i < 20; i++) //compares answers
		{
			if (answers[i] == userAnswers[i])
			{
				countRights++;
			}
			else
			{
				wrongs += int2string(i + 1);
				wrongs += " ";
			}
		}

		if (countRights >= 15) // Concatenate passing score
		{
			grade = "You have passed the driver's license exam!";
		}
		else //Concatenate no passing score
		{
			grade = "You failed the driver's license exam :(";
		}

		if (countRights == 20)// Concatenate if perfect score
		{
			grade += "You got a perfect score 20 out of 20!!!" ;
		}
		else//Concatenate number of rights and number of wrongs
		{
			grade += "\nYou got ";
			grade += int2string(countRights) ;//concatenate number of rights
			grade += " rights and the following wrong:\n";
			grade += wrongs;//concatenate list of wrongs
		}
		return grade;
	}
	/** Casts positive int values to string*/
	string int2string(int intValue)
	{
		if (intValue == 0) //intValue is 0
		{
			return "0";
		}
		string castInt = "";

		while (intValue > 0) //shift right and cast as char
		{
			string temp = castInt;
			castInt = static_cast<char>((intValue % 10) + 48);
			castInt += temp;
			intValue /= 10;
		}
		return castInt;
	}

private:
	char answers[20]; //answer key
};

#endif //End of TESTGRADER_H
