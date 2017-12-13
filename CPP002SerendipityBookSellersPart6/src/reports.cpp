/* 	Chapter No. 07      	Exercise No. Serendipity Part6 (multifile)
   	File Name:          	reports.cpp
   	Programmer:         	Angel Tapia
   	Date Last Modified: 	November 5, 2017
*/

#include <iostream>

#include "reports.h"

using namespace std;

void reports()
{	
	int option = 0 ;// stores the option chosen by the user.
	
	while(option != -1)
	{
		// Report menu
		cout<<"\tSerendipity Booksellers\n" ;
		cout<<"\t   Reports\n" ;
		cout<<"\t1. Inventory Listing\n" ;
		cout<<"\t2. Inventory Wholesale Value\n" ;
		cout<<"\t3. Inventory retail Value\n" ;
		cout<<"\t4. Listing by Quantity\n" ;
		cout<<"\t5. Listing by Cost\n" ;
		cout<<"\t6. Listing by Age\n" ;
		cout<<"\t7. Return to the Main Menu\n" ;
	
		do // validates data input
		{
			cin.clear() ;//Cleans cin...
			fflush(stdin) ;//...in case of invalid data
				
			// Asks user for data in
			cout<<"\tEnter your choice (1-7): " ;				 
			cin >> option ;// Store data in option 
				
		} while(option < 1 || option > 7 || cin.fail()) ;
		
		cout<<"\tYou selected item " << option << ".\n\n" ;
		
		switch(option)//instead of displaying the number entered by the user
		{
			case 1:
				repListing() ;//Calls repListing()
				break ;
			case 2:
				repWholesale() ;//Calls repWholesale()
				break ;
			case 3:
				repRetail() ;//Calls repRetail()
				break ;
			case 4:
				repQty() ;//Calls repQty()
				break ;
			case 5:
				repCost() ;//Calls repCost()
				break ;
			case 6:
				repAge() ;//Calls repAge()
				break ;								
			default :
				option = -1 ;// 7 was selected
				break ;
		}
	}
}

/***********************************************************
 *	STUB FUNCTIONS
 ***********************************************************/
void repListing()
{
	cout<<"You selected Inventory Listing.\n" ;
}
void repWholesale()
{
	cout<<"You selected Inventory Wholesale Value.\n" ;
}
void repRetail()
{
	cout<<"You selected Inventory Retail Value.\n" ;
}
void repQty()
{
	cout<<"You selected Listing By Quantity.\n" ;
}
void repCost()
{
	cout<<"You selected Listing By Cost.\n" ;
}
void repAge()
{
	cout<<"You selected Listing By Age.\n" ;
}
