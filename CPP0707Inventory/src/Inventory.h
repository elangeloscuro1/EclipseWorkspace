/*
 Chapter No. 07      	Exercise No. 07
 File Name:          	Inventory.h
 Programmer:         	Angel Tapia
 Date Last Modified: 	November 14, 2017

 Problem Statement:	This header file represents an inventory class
 */

#ifndef INVENTORY_H
#define INVENTORY_H

class Inventory
{
public:
	/**Initializes instance variable to zero*/
	Inventory()
	{
		itemNumber = 0;
		quantity = 0;
		cost = 0.0;
		totalCost = 0.0;
	}
	/**Initializes instance variable to the given values*/
	Inventory(int itemNumber, int quantity, double cost)
	{
		setItemNumber(itemNumber);
		setQuantity(quantity);
		setCost(cost);
		setTotalCost(itemNumber * cost);
	}
	/**Destructor*/
	~Inventory()
	{

	}

	/**itemNumber Getter*/
	int getItemNumber()
	{
		return itemNumber;
	}

	/**itemNumber Setter*/
	void setItemNumber(int itemNum)
	{
		itemNumber = itemNum;
	}

	/**quantity Getter*/
	int getQuantity()
	{
		return quantity;
	}

	/**quantity Setter*/
	void setQuantity(int qty)
	{
		quantity = qty;
	}

	/**cost Getter*/
	double getCost()
	{
		return cost;
	}

	/**cost Setter*/
	void setCost(double itemCost)
	{
		cost = itemCost;
	}

	/**totalCost Getter*/
	double getTotalCost()
	{
		return totalCost;
	}

	/**totalCost Setter*/
	void setTotalCost(double totalItemsCost)
	{
		totalCost = totalItemsCost;
	}

private:
	int itemNumber; //category
	int quantity; //quantity of items
	double cost; //cost per item
	double totalCost; //
};

#endif /**End definition of Inventory*/
