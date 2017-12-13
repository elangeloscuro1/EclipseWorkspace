/* 	Chapter No. 07      	Exercise No. 04
   	File Name:          	CarTest.cpp
   	Programmer:         	Angel Tapia
   							Gabriella Long
   							...
   							...
   	Date Last Modified: 	November 03, 2017

    Problem Statement:	This program program tests
    					the definition of a class Car.
	Algorithm:
	1.Create a header file of the class Car:
		a)use include guard (Use #ifndef, #define, and #endif)
		  to prevent header to be include twice
		b)Add the std library and #include: <string>
		c)Declare public member functions prototypes:
			Car(): Default constructor
			Car(int itsYear, string itsMake): Constructor that takes in
											  the year and make of the
											  car as parameters
			~Car(): Destructor (allows the user to deallocate memory)
			void setYear(int itsYear): Sets the year of the car
			int getYear(): Access the year of the car
			void setMake(string itsMake): Sets the make of the car
			string getMake(): Access the make of the car
			int getSpeed(): Access the current speed of the car
			void accelerate(): Adds 5 to speed each time it is called
			void brake(): Subtracts 5 to speed each time it is called
		d)Declare private member data:
			int year: represents the model year
			string make: represents the make of the car
			int speed: represents the current speed of the car
	2.Implement the class Car:
		a)Add the std library and #include: <string> and "Car.H"
		b)Implement the Default Constructor by initializing
		  year = 0 , make = "UNKNOWN", and speed = 0
		c)Implement the constructor that takes the year and make of the
		  car as parameters by initializing year and make with the
		  provided year and make.
		d)Implement the Destructor (this should be empty)
		e)Implement setYear(int itsYear) by setting year = itsYear
		f)Implement getYear() by returning the value of year
		g)Implement setMake(string itsYear) by setting make = itsMake
		h)Implement getMake() by returning the value of make
		i)Implement setSpeed() by returning the value of speed
		j)Implement accelerate() by incrementing speed by 5
		k)Implement brake() by decrementing speed by 5
	2.Create a tester program:
		a)Add the std library and #include: <string> and "Car.H"
		b)Implement main():
			*Create an instance of the class Car and pass year (int)
			 and make (string) of the car as its parameters
			*Print the make and year of the car by calling the
			 functions getMake() and getYear()
			*Test the function accelerate() in a loop for 5 times:
				-Call the function accelerate()
				-Print the current speed by calling the function getSpeed()
			*Test the function brake() in a loop for 5 times:
				-Call the function brake()
				-Print the current speed by calling the function getSpeed()
			*print end of the test.

	Classes needed and Purpose (Input, Processing, Output):
	#include <iostream>  // input and output stream of data.
	#include <string>    // allows the use of the string class.
*/

#include <iostream>
#include <string>
#include "Car.h"

using namespace std ;

int main()
{
	// Creates an instance of the class Car
	Car car(2017, "HONDA") ;

	// Print the make and year of the car
	cout << "Make: " << car.getMake() << endl ;
	cout << "Year: " << car.getYear() << endl ;

	// Test the function accelerate() in a loop for 5 times
	cout << "\naccelerate test:" << endl ;
	for (int run = 0; run < 5 ; ++run)
	{
		car.accelerate() ;
		cout << "current speed: " << car.getSpeed() << " m/p." << endl ;
	}

	// Test the function brake() in a loop for 5 times
	cout << "\nbrake test: " << endl ;
	for (int run = 0; run < 5 ; ++run)
	{
		car.brake();
		cout << "current speed: " << car.getSpeed() << " m/p." << endl ;
	}

	// print end of the test
	cout << "\nEnd of the test!" << endl ;
	return 0 ;
}
