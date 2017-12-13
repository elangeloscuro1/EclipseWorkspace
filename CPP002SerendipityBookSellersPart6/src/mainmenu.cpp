/* 	Chapter No. 07      	Exercise No. Serendipity Part6 (multifile)
	File Name:          	mainmenu.cpp
	Programmer:         	Angel Tapia
	Date Last Modified: 	November 5, 2017
   
	Problem Statement:	In this version, mainmenu.cpp
						is a modular program that uses multiple
						files (headers files and implementation files)
						to access their specific functions.	
	Algorithm:
	1. Change the name and return type of the following functions:
		-main in cashier.cpp to void cashier; remove the return statement.
		-main in invmenu.cpp to void invmenu; remove the return statement.
		-main in bookinfo.cpp to void bookinfo; remove the return statement.
		-main in reports.cpp to void reports; remove the return statement.
	2. Create the following header files 
	   (use include guard: #ifndef, #define, and #endif):
		-cashier.h:	This contains the function prototype cashier()
		-invmenu.h:	This contains the function prototype invmenu()
		-bookinfo.h:	This contains the function prototype bookinfo()
		-reports.h:	This contains the function prototype reports()		
	3. Add an #include directive for each header file to mainmenu.cpp
	4. Modify the switch statement in the while-loop in function main (of mainmenu.cpp):
		-case 1: calls the cashier function
		-case 2: calls the invmenu function
		-case 3: calls the reports function
		-default: makes option = -1 to end the loop
	5. Add the following stub functions to invmenu.cpp 
	   and place function prototypes in invmenu.h:
		-void lookUpBook() displays: "You selected Look Up Book."
		-void addBook() displays: "You selected Add Book."
		-void editBook() displays: "You selected Edit Book."
		-void deleteBook() displays: "You selected Delete Book."
	6. Modify the switch statement in function invMenu so instead of displaying 
		the number entered by the user, it calls their corresponding function:
		-case 1: calls the lookUpBook function
		-case 2: calls the addBook function
		-case 3: calls the editBook function
		-case 4: calls the deleteBook function
		-default: makes option = -1 to end the loop
	7. Add the following stub functions to reports.cpp 
	   and place function prototypes in reports.h:
		-void repListing() displays: "You selected Inventory Listing."
		-void repWholesale() displays: "You selected Inventory Wholesale Value."
		-void repRetail() displays: "You selected Inventory Retail Value."
		-void repQty() displays: "You selected Listing By Quantity."
		-void repCost() displays: "You selected Listing By Cost."
		-void repAge() displays: "You selected Listing By Age."
		-Add function prototypes before the function main
	8. Modify the switch statement in function reports so instead of displaying 
	   the number entered by the user, it calls their corresponding function:
		-case 1: calls the repListing function
		-case 2: calls the repWholesale function
		-case 3: calls the repRetail function
		-case 4: calls the repQty function
		-case 5: calls the repCost function
		-case 6: calls the repAge function
		-default: makes option = -1 to end the loop
	
	Classes needed and Purpose (Input, Processing, Output):
	#include <iostream>  // input and output stream of data.
	#include <string>    // allows the use of the string class.
	#include <iomanip>	// Allows to format the output data.
*/

#include <iostream>

#include "bookinfo.h"
#include "cashier.h"
#include "invmenu.h"
#include "reports.h"

using namespace std ;

int main()
{	
	int option = 0 ;// stores the option chosen by the user.
	
	while(option != -1)
	{
		cout<<"___________________________________________________\n"  ;
		// main menu
		cout<<"\tSerendipity Booksellers\n" ;
		cout<<"\t\tMain Menu\n" ;
		cout<<"\t1. Cashier Module\n" ;
		cout<<"\t2. Inventory Database Module\n" ;
		cout<<"\t3. Report Module\n" ;
		cout<<"\t4. Exit\n\n" ;	
				
		do // validates data input
		{
			cin.clear() ;//Cleans cin...
			fflush(stdin) ;//...in case of invalid data
				
			// Asks user for data in
			cout<<"\tEnter your choice (1-4): " ;				 
			cin >> option ;// Store data in option 
				
		} while(option < 1 || option > 4 || cin.fail()) ;
		
		switch(option)//instead of displaying the number entered by the user
		{
			case 1:
				cashier() ;//Go to cashier()
				break ;
			case 2:
				invMenu() ;//Go to invMenu()
				break ;
			case 3:
				reports() ;//Go to reports()
				break ;
			default :
				option = -1 ;// 4 was selected
				break ;
		}
	}
	cout << "\n\n\tThank You For Choosing Serendipity!\n" ;
	return 0 ;
}
