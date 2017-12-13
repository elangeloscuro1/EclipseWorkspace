/* 	Chapter No. 07      	Exercise No. Serendipity Part6 (multifile)
    File Name:          	cashier.cpp
   	Programmer:         	Angel Tapia
  	Date Last Modified: 	November 5, 2017
*/

#include <iostream>
#include <iomanip>
#include <string>

#include "cashier.h"

using namespace std;

void cashier()
{
	// Declare a constant
	const double TAX_RATE = 0.06 ;	
	
	// flags for another transaction
	char doAnother = 'y' ;
	do// Allows the user to input more than	one transaction.
	{		
		// Declare a varible
		string date ;		
		int quantity ;		
		string isbn ;		
		string title ;		
		double price ;
	
		// Display the the title of the prpgram.
		cout <<"\tSerendipity Booksellers\n" ;
		cout <<"\tCashier Module\n\n" ;
		
		// Request values from the user
		cout <<"\tDate: " ;
		cin >> date ;
		cout << "\tQuantity of Book: " ;
		cin >> quantity ;
		cout <<"\tISBN: " ;
		cin >> isbn ;
		cout <<"\tTitle: " ;
		fflush(stdin) ;
		getline(cin, title) ;
		cout <<"\tPrice: " ;
		cin >> price ;	
		
		// Displays formated titles for each row.
		cout <<"\n\tSerendipity Booksellers\n\n\t" ;
		cout << setw(5) << left << "Qty" ;
		cout << setw(15) << left  << "ISBN" ;
		cout << setw(20) << left << "Title" ;
		cout << setw(10) << right << "Price" ;
		cout << setw(10) << right << "Total" ;	
		
		// Creates a line that divides the formated titles for each row.
		string divisorLine ; 
		divisorLine.assign(60, '_') ;
		cout << "\n\t" + divisorLine + "\n\t" ;
		
		// Sets format to display details.
		cout << fixed << showpoint << setprecision(2) ;
		
		cout << setw(5) << left << quantity ;
		cout << setw(15) << left << isbn ;
		cout << setw(20) << left << title ;
		cout << setw(4) << right << "$" << setw(6) << price ;
		cout << setw(4) << right << "$" << setw(6) << price * quantity ;
		cout << "\n\n\t" ;
		
		// Calculates book info, subtotal, tax, and total.
		divisorLine.assign(20, ' ') ;
		cout << divisorLine << setw(20) << left << "Subtotal " ;
		cout << setw(14) << right << "$" << setw(6) << price * quantity  ;	
		cout << "\n\t" << divisorLine << setw(20) << left << "Tax " ;
		cout << setw(14) << right << "$" << setw(6) << (price * quantity) * TAX_RATE ;
		cout << "\n\t" << divisorLine << setw(20) << left << "Total " ;
		cout << setw(14) << right << "$" << setw(6) << (price * quantity) * (1 + TAX_RATE) ;
		
		// Asks the user if he/she wants to enter another transaction
		cout << "\n\tWhould you like to add another transaction (y/n)? " ;
		cin >> doAnother ;
		cout << endl ;
	
	} while (doAnother == 'y' || doAnother == 'Y') ;
	
	// Displays end of the program.
	cout << "\n\n\tThank You For Shopping Serendipity!\n" ;
}
