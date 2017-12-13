/*
 * Chapter No. 08      	Exercise No. 16
 * File Name:          	PayRoll.h
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 27, 2017
 */
#ifndef PAYROLL_H
#define PAYROLL_H

class PayRoll
{
public:
	/** Default constructor*/
	PayRoll()
	{
		hourlyPayRate = 0.0;
		hoursWorked = 0;
	}
	/** Initializes hoursWorked and hourlyPayRate*/
	PayRoll(double payRate, int hours)
	{
		hourlyPayRate = payRate;
		hoursWorked = hours;
	}
	/** Destructor*/
	~PayRoll()
	{

	}

	/** hourlyPayRate Getter */
	double getHourlyPayRate()
	{
		return hourlyPayRate;
	}

	/** hourlyPayRate Setter */
	void setHourlyPayRate(double payRate)
	{
		hourlyPayRate = payRate;
	}

	/** hoursWorked Getter */
	double getHoursWorked()
	{
		return hoursWorked;
	}

	/** hoursWorked Setter */
	void setHoursWorked(double hours)
	{
		hoursWorked = hours;
	}

	/** calculates and returns gross pay*/
	double getGrossPay()
	{
		return hourlyPayRate * hoursWorked ;
	}
private:
	double hoursWorked;//total hours worked
	double hourlyPayRate;//actual payRate
};

#endif//End of PAYROLL_H
