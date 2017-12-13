/*
 Chapter No. 07      	Exercise No. 08
 File Name:          	MovieData.h
 Programmer:         	Angel Tapia
 Date Last Modified: 	November 14, 2017

 Problem Statement:	This header file represents an MovieData class
 */
#ifndef MOVIEDATA_H
#define MOVIEDATA_H

#include <string>
using namespace std;

class MovieData
{
public:
	/**Initializes instance variable to zero*/
	MovieData()
	{
		title = "N/A";
		director = "N/A";
		year = "N/A";
		time = 0;
	}
	/**Initializes instance variable to the given values*/
	MovieData(string itsTitle, string itsDirector, string itsYear, int itsTime)
	{
		setTitle(itsTitle);
		setDirector(itsDirector);
		setYear(itsYear);
		setTime(itsTime) ;
	}
	/**Destructor*/
	~MovieData()
	{

	}

	/**title Getter*/
	string getTitle()
	{
		return title;
	}

	/**title Setter*/
	void setTitle(string itsTitle)
	{
		title = itsTitle;
	}

	/**director Getter*/
	const string getDirector()
	{
		return director;
	}

	/**director Setter*/
	void setDirector(string itsDirector)
	{
		director = itsDirector;
	}

	/**time Getter*/
	int getTime() const
	{
		return time;
	}

	/**time Setter*/
	void setTime(int itsTime)
	{
		time = itsTime;
	}

	/**year Getter*/
	string getYear()
	{
		return year;
	}

	/**year Setter*/
	void setYear(string itsYear)
	{
		year = itsYear;
	}

private:
	string title;//title of the movie
	string director;//director of the movie
	string year;//year of the movie
	int time;//running time  of the movie

};

#endif /**End definition of MovieData*/
