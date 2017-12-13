/**
 * Chapter No. 08      	Exercise No. Serendipity part: 7, 8
 * File Name:          	mainmenu.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 29, 2017
 *
 * Problem Statement:	This Serendipity version Uses a class
 * 						BookData to represent a book that
 * 						will be used in the inventory.
 *
 * Algorithm:
 * 1.Create a BookData class:
 * 	-Create instance variables:
 * 		string bookTitle, isbn, author, publisher, dateAdded
 * 		int qtyOnHand
 * 		double wholesale, retail
 * 		bool empty
 *	-Create a default constructor that sets all member data to
 *	 default values
 *	-Create Getters and setter for each instance variable
 *	 (for member data empty only a getter named isEmpty)
 *	-Create a function insertBook that sets empty == false
 *	-Create a function removeBook that sets empty == true
 * 2.Create mainmenu class that contain main():
 * 	-Create an array of 20  BookData objects
 * 	-Sets Title, Author, Added Date, ISBN, and Publisher for one book
 * 	-Display Title, Author, Added Date, ISBN, and Publisher
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <iostream>  // input and output stream of data.
 */

#include <iostream>
#include "BookData.h"
using namespace std;

int main()
{
	BookData bookData[20]; //Create an array of 20  BookData objects

	//Sets Title, Author, Added Date, ISBN, and Publisher
	bookData[0].setBookTitle("Starting Out With C++");
	bookData[0].setAuthor("Gaddis, etc");
	bookData[0].setDateAdded("01/01/2017");
	bookData[0].setIsbn("978-0-13-440024 -2");
	bookData[0].setPub("Pearson");

	// Display Title, Author, Added Date, ISBN, and Publisher
	cout << "***********     Book info     ***********" << endl;
	cout << "Title:      " << bookData[0].getTitle() << endl;
	cout << "Author:     " << bookData[0].getAuthor() << endl;
	cout << "Added DAte: " << bookData[0].getDateAdded() << endl;
	cout << "ISBN:       " << bookData[0].getIsbn() << endl;
	cout << "Publisher:  " << bookData[0].getTitle() << endl;
	return 0;
}
