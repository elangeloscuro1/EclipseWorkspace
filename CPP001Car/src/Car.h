/*
 * Car.h:	Specifies the definition
 * 			of the class Car
 */

#ifndef CAR_H
#define CAR_H

#include <string>

using namespace std ;

class Car
{
	public://Public member functions
		Car() ;								//empty constructor
		Car(int itsYear, string itsMake) ; 	//year and make constructor
		~Car() ;							//Destructor

		// Public accessors
		void setYear(int itsYear) ;
		int getYear() ;
		void setMake(string itsMake) ;
		string getMake() ;
		int getSpeed() ;

		// Public member function prototype
		void accelerate() ;
		void brake() ;

	private ://private member data
		int year ;		//model year
		string make ;	//make of the car
		int speed ;		//current speed
} ;

#endif
