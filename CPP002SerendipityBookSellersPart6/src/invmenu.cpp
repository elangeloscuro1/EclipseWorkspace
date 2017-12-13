/* 	Chapter No. 07      	Exercise No. Serendipity Part6 (multifile)
   	File Name:          	invmenu.cpp
   	Programmer:         	Angel Tapia
   	Date Last Modified: 	November 5, 2017
*/

#include <iostream>

#include "invmenu.h"

using namespace std;

void invMenu()
{	
	int option = 0 ;// stores the option chosen by the user.
	
	while(option != -1)
	{	
		// inventory menu
		cout<<"\tSerendipity Booksellers\n" ;
		cout<<"\t Inventory Database\n\n" ;
		cout<<"\t1. Look-Up a Book\n" ;
		cout<<"\t2. Add a Book\n" ;
		cout<<"\t3. Edit a Book's Record\n" ;
		cout<<"\t4. Delete a Book\n" ;
		cout<<"\t5. Return to the Main Menu\n\n" ;
	
		do // validates data input
		{
			cin.clear() ;//Cleans cin...
			fflush(stdin) ;//...in case of invalid data
				
			// Asks user for data in
			cout<<"\tEnter your choice (1-5): " ;				 
			cin >> option ;// Store data in option 
				
		} while(option < 1 || option > 5 || cin.fail()) ;
		
		cout<<"\tYou selected item " << option << ".\n\n" ;
		
		switch(option)//instead of displaying the number entered by the user
		{
			case 1:
				lookUpBook() ;//Calls lookUpBook()
				break ;
			case 2:
				addBook() ;//Calls addBook()
				break ;
			case 3:
				editBook() ;//Calls editBook()
				break ;
			case 4:
				deleteBook() ;//Calls deleteBook()
				break ;
			default :
				option = -1 ;// 5 was selected
				break ;
		}
	}
}

/***********************************************************
 *	STUB FUNCTIONS
 ***********************************************************/
void lookUpBook()
{
	cout<<"You selected Look Up Book.\n" ;
}
void addBook()
{
	cout<<"You selected Add Book.\n" ;
}
void editBook()
{
	cout<<"You selected Edit Book.\n" ;
}
void deleteBook()
{
	cout<<"You selected Delete Book.\n" ;
}
