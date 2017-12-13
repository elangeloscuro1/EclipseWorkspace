#ifndef VENDINGMACHINE_H
#define VENDINGMACHINE_H

#include <iostream>
#include <iomanip>
#include <string>

#include "Register.h"
#include "Dispenser.h"
using namespace std;

class VendingMachine
{

public:
	VendingMachine()
	{
		selected = 0;
		addedItems = 0;
		itemCost = 0;
		cashIn = 0;
	}
	VendingMachine(double initialCash)
	{
		reg.deposit(initialCash);
		addedItems = 0;
		selected = 0;
		itemCost = 0;
		cashIn = 0;
	}
	~VendingMachine()
	{

	}

	double validate(double min, double max)
	{
		double choice;
		cin >> choice;
		while (choice < min || choice > max || cin.fail())
		{
			cin.clear();
			fflush(stdin);
			cout << "Please enter [$" << min << " - $" << max << "] : ";
			cin >> choice;
		}
		return choice;
	}

	int validate(int min, int max)
	{
		int choice;
		cin >> choice;
		while (choice < min || choice > max || cin.fail())
		{
			cin.clear();
			fflush(stdin);
			cout << "Please enter [" << min << " - " << max << "] : ";
			cin >> choice;
		}
		return choice;
	}

	void displayItems()
	{
		cout << fixed << showpoint << setprecision(2);
		cout << "--------------------------------------------\n";
		cout << "|  Current Machine balance:    $" << reg.getBalance();
		cout << "\n--------------------------------------------\n";
		cout << "|[CHOICE]  " << "PRICE" << "  " << "  ITEM" << setw(18)
				<< right << "TOTAL" << endl;
		cout << "|  [1]     $" << dispenser1.getItemCost() << "    " << setw(20)
				<< left << dispenser1.getItemName() << dispenser1.getQuantity()
				<< endl;
		cout << "|  [2]     $" << dispenser2.getItemCost() << "    " << setw(20)
				<< left << dispenser2.getItemName() << dispenser2.getQuantity()
				<< endl;
		cout << "|  [3]     $" << dispenser3.getItemCost() << "    " << setw(20)
				<< left << dispenser3.getItemName() << dispenser3.getQuantity()
				<< endl;
		cout << "|  [4]     $" << dispenser4.getItemCost() << "    " << setw(20)
				<< left << dispenser4.getItemName() << dispenser4.getQuantity()
				<< endl;
		cout << "--------------------------------------------\n";
	}

	void vendItem()
	{
		if (selected == 0 && cashIn == 0)
		{
			displayItems();
			makeSelection();
			acceptMoney();
		}

		if (cashIn < itemCost)
		{
			double needed = reg.calcChange(cashIn, itemCost);
			cout << "$" << needed << " is needed for $" << itemCost << endl;
			acceptMoney();
			double change = reg.calcChange(itemCost, cashIn);

			if (cashIn < itemCost)
			{
				cout << "Please try again later!" << endl;
				cout << "Don't forget your change! $" << -change << endl;
			}
			else if (!dispense())
			{
				vendItem(); // recursion call
				return;
			}
			else if (cashIn > itemCost && reg.getBalance() < change)
			{
				reg.makeCreditReceipt(change);
				reg.deposit(cashIn);
			}
			else if (cashIn > itemCost)
			{
				cout << "Don't forget your change! $" << change << endl;
				reg.deposit(cashIn - change);
			}
			resetSelection();
			cout << "Thank you, come back soon!" << endl;
			return;
		}

		double change = reg.calcChange(itemCost, cashIn);

		if (!dispense())
		{
			displayItems();
			cout << "\nSorry! Item sold out!\n";
			cout << "Please select a different Item or enter 0 to quit: ";

			if ((selected = validate(0, 4)) != 0)
			{
				vendItem(); // recursion call
				return;
			}
			cout << "Don't forget your change! $" << cashIn << endl;
		}
		else if (cashIn > itemCost && reg.getBalance() < change)
		{
			reg.makeCreditReceipt(change);
			reg.deposit(cashIn);
		}
		else if (cashIn > itemCost)
		{
			cout << "Don't forget your change! $" << change << endl;
			reg.deposit(cashIn - change);
		}
		resetSelection();
		cout << "Thank you, come back soon!" << endl;
	}

	void resetSelection()/**Reset current selection*/
	{
		selected = 0;
		cashIn = 0;
		itemCost = 0;
	}

	void makeSelection()/**Requests the user to select an item*/
	{
		cout << "please select item: ";
		double cost = getItemCost(validate(1, 4));
		cout << "[ ==> $" << cost << " <==]" << endl;
	}

	void acceptMoney()/** adds cash to the vending machine*/
	{
		cout << "please Enter Cash: ";
		cashIn += validate(0.1, 20.0);
	}

	double getItemCost(int item)/**return the selected item's cost*/
	{
		switch (selected = item)
		{
		case 1:
			return itemCost = dispenser1.getItemCost();
		case 2:
			return itemCost = dispenser2.getItemCost();
		case 3:
			return itemCost = dispenser3.getItemCost();
		case 4:
			return itemCost = dispenser4.getItemCost();
		default:
			return selected = 0;
		}
	}

	void setDispenser1(int select, string name, int qty, double cost)/** Sets dispenser1*/
	{
		dispenser1.setDispenser(name, qty, cost);
	}

	void setDispenser2(int select, string name, int qty, double cost)/** Sets dispenser2*/
	{
		dispenser2.setDispenser(name, qty, cost);
	}

	void setDispenser3(int select, string name, int qty, double cost)/** Sets dispenser3*/
	{
		dispenser3.setDispenser(name, qty, cost);
	}

	void setDispenser4(int select, string name, int qty, double cost)/** Sets dispenser4*/
	{
		dispenser4.setDispenser(name, qty, cost);
	}

	bool dispense()
	{
		switch (selected)
		{
		case 1:
			return dispenser1.dispense();
		case 2:
			return dispenser2.dispense();
		case 3:
			return dispenser3.dispense();
		case 4:
			return dispenser4.dispense();
		default:
			return false;
		}
	}

private:
	int selected;
	double cashIn;
	double itemCost;
	Register reg;
	int addedItems;
	Dispenser dispenser1;
	Dispenser dispenser2;
	Dispenser dispenser3;
	Dispenser dispenser4;
};

#endif /* VENDINGMACHINE_H_ */
