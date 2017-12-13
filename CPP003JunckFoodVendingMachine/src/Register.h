#ifndef REGISTER_H
#define REGISTER_H

#include <iostream>
#include <string>

using namespace std;

class Register
{
public:
	Register()
	{
		balance = 0;
	}

	~Register()
	{
	}

	double getBalance()
	{
		return balance;
	}

	double deposit(double cashIn)
	{
		return balance += cashIn;
	}

	bool withdraw(double cashOut)
	{
		if (balance < cashOut)
		{
			return false;
		}
		balance -= cashOut;
		return true;
	}

	double calcChange(double cost, double cashIn)
	{
		return cashIn - cost;
	}

	void makeCreditReceipt(double amount)
	{
		cout << "--------------------------------------------\n";
		cout << "| Your have a credit of: $" << amount << endl;
		cout << "--------------------------------------------\n";
	}

private:
	double balance;
};

#endif /* REGISTER_H_ */
