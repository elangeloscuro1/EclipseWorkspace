/*
 Chapter No. 07      	Exercise No. 09
 File Name:          	MovieData2.h
 Programmer:         	Angel Tapia
 Date Last Modified: 	November 14, 2017

 Problem Statement:	This header file represents an MovieData class
 */
#ifndef MOVIEDATA2_H
#define MOVIEDATA2_H

#include <string>
using namespace std;

class MovieData2
{
public:
	/**Initializes instance variable to zero*/
	MovieData2()
	{
		title = "N/A";
		director = "N/A";
		year = "N/A";
		time = 0;
		productionCost = 0.0;
		yearlyRevenue = 0.0;
	}
	/**Initializes instance variable to the given values*/
	MovieData2(string itsTitle, string itsDirector, string itsYear,
			int itsTime, double itsProductionCost, double itsYearlyRevenue)
	{
		setTitle(itsTitle);
		setDirector(itsDirector);
		setYear(itsYear);
		setTime(itsTime) ;
		setProductionCost(itsProductionCost);
		setYearlyRevenue(itsYearlyRevenue);
	}
	/**Destructor*/
	~MovieData2()
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

	/**productionCost Getter*/
	double getProductionCost()
	{
		return productionCost;
	}

	/**productionCost Setter*/
	void setProductionCost(double itsProductionCost)
	{
		productionCost = itsProductionCost;
	}

	/**yearlyRevenue Getter*/
	double getYearlyRevenue() const
	{
		return yearlyRevenue;
	}
	/**yearlyRevenue Setter*/
	void setYearlyRevenue(double itsYearlyRevenue)
	{
		yearlyRevenue = itsYearlyRevenue;
	}

private:
	string title;//title of the movie
	string director;//director of the movie
	string year;//year of the movie
	int time;//running time  of the movie
	double productionCost;//production cost of the movie
	double yearlyRevenue;//revenue of the movie
};

#endif /**End definition of MovieData2*/
