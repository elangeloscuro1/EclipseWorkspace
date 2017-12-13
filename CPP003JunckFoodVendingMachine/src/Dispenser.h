#ifndef DISPENSER_H
#define DISPENSER_H

#include <iostream>
#include <string>

using namespace std;

class Dispenser
{
public:
	Dispenser()
	{
		itemName = "N/A";
		quantity = 0;
		itemCost = 0.0;
	}

	~Dispenser()
	{
	}

	void setDispenser(string name, int qty, double cost)
	{
		itemName = name;
		quantity = qty;
		itemCost = cost;
	}
	const string getItemName()
	{
		return itemName;
	}
	double getItemCost()
	{
		return itemCost;
	}

	int getQuantity()
	{
		return quantity;
	}

	void setQuantity(int qty)
	{
		quantity = qty;
	}

	bool isEmpty()
	{
		return quantity == 0;
	}

	bool dispense()
	{
		if (isEmpty())
		{
			return false;
		}
		quantity--;
		return true;
	}

private:
	string itemName;
	int quantity;
	double itemCost;
};

#endif /* DISPENSER_H_ */
