/**
 * Car.cpp:	Implementation file of class Car
 */

#include <string>
#include "Car.h"

using namespace std ;

	/* Constructor: Default*/
	Car::Car()
	{
		year = 0 ;
		make = "NO MAKE" ;
		speed = 0 ;
	}

	/* Constructor: takes in year and make
	 * 				and initializes speed = 0*/
	Car::Car(int itsYear, string itsMake)
	{
		year = itsYear ;
		make = itsMake ;
		speed = 0 ;
	}

	/* Destructor*/
	Car::~Car()
	{
	}

/* Sets the car's year*/
void Car::setYear(int itsYear)
{
	year = itsYear ;
}

/* Returns the car's year*/
int Car::getYear()
{
	return year ;
}

/* Sets the car's make*/
void Car::setMake(string itsMake)
{
	make = itsMake ;
}

/* Returns the car's make*/
string Car::getMake()
{
	return make ;
}

/* Returns the car's current speed*/
int Car::getSpeed()
{
	return speed ;
}

/* Increments speed*/
void Car::accelerate()
{
	speed += 5 ;
}

/* Decrements speed*/
void Car::brake()
{
	speed -= 5 ;
}
