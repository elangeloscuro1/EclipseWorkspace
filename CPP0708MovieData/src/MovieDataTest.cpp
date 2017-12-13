/*
 Chapter No. 07      	Exercise No. 08
 File Name:          	ProgrammingChallenge08.cpp
 Programmer:         	Angel Tapia
 Date Last Modified: 	November 14, 2017

 Problem Statement:	This program Tests an MovieData class

 Algorithm:
 1.Create a MovieData class:
  	 -Create instance variable:
		string title for title of the movie
		string director for director of the movie
		string year for year of the movie
		int time for running time  of the movie
	-Create an empty constructor that initializes all
		of the instance variable to zero or N/AS
	-Create a constructor that receives and initializes
		title, director, year, and time
		Call setters to set instance variables
	-Create Setters and Getters of each field
2.Create a tester file that contains main:
	-Create function prototype: void displayMovieData(MovieData movie)
	-main():
		Create two object of the class MovieData
		Set a displaying format
		Use left and setw to display TITLE, DIRECTOR, YEAR, and TIME
		Call the function displayMovieData for each movie
	-Implement displayMovieData(MovieData movie):
		Use setw to display:
			movie.getTitle()
			movie.getDirector()
			movie.getYear()
			movie.getTime()
 */
#include <iostream>
#include "MovieData.h"
#include <iomanip>

using namespace std;

void displayMovieData(MovieData movie);
int main()
{
	//Creates two instances of the class MovieData
	MovieData movie1("Terminator 2", "Alan taylor", "01/01/17", 60);
	MovieData movie2("Predator", "John McTiernan", "01/01/17", 90);

	//Display format
	cout << left ;
	cout << setw(20) << "TITLE" ;
	cout << setw(20) << "DIRECTOR";
	cout << setw(8) << "YEAR";
	cout << setw(8) << "TIME";
	cout << "\n----------------------------------------------------\n" ;

	//Displays movie details
	displayMovieData(movie1);
	displayMovieData(movie2);
	return 0;
}
/**Displays movie details*/
void displayMovieData(MovieData movie)
{
	cout << setw(20) << movie.getTitle();
	cout << setw(20) << movie.getDirector() ;
	cout << setw(8) << movie.getYear();
	cout << setw(8) << movie.getTime() << endl;

}
