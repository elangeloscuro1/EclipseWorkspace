/*
 * Chapter No. 08      	Exercise No. Serendipity part: 7, 8
 * File Name:          	BookData.h
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	November 29, 2017
 */

#ifndef BOOKDATA_H
#define BOOKDATA_H

#include <string>
using namespace std;

class BookData
{
public:
	BookData()//Sets member data to default values
	{
		bookTitle = "null";
		isbn = "null";
		author = "null";
		publisher = "null";
		dateAdded = "null";
		qtyOnHand = 0;
		wholesale = 0.0;
		retail = 0.0;
		empty = true;
	}
	/** bookTitle Setter */
	void setBookTitle(string title)
	{
		bookTitle = title;
	}
	/** isbn Setter */
	void setIsbn(string setisbn)
	{
		isbn = setisbn;
	}
	/** author Setter */
	void setAuthor(string anAuthor)
	{
		author = anAuthor;
	}
	/** publisher Setter */
	void setPub(string aPublisher)
	{
		publisher = aPublisher;
	}
	/** dateAdded Setter */
	void setDateAdded(string aDate)
	{
		dateAdded = aDate;
	}
	/** qtyOnHand Setter */
	void setQty(int qty)
	{
		qtyOnHand = qty;
	}
	/** wholesale Setter */
	void setWholesale(double salePrice)
	{
		wholesale = salePrice;
	}
	/** retail Setter */
	void setRetail(double salePrice)
	{
		retail = salePrice;
	}
	/** isEmpty is false if insertBook is called */
	bool isEmpty()
	{
		return empty;
	}
	/** insertBook sets empty to false */
	void insertBook()
	{
		empty = false;
	}
	/** removeBook sets empty to true */
	void removeBook()
	{
		empty = true;
	}
	/** bookTitle Getter */
	string getTitle()
	{
		return bookTitle;
	}
	/** isbn Getter */
	string getIsbn()
	{
		return isbn;
	}
	/** author Getter */
	string getAuthor()
	{
		return author;
	}
	/** publisher Getter */
	string getPub()
	{
		return publisher;
	}
	/** dateAdded Getter */
	string getDateAdded()
	{
		return dateAdded;
	}
	/** qtyOnHand Getter */
	int getQty()
	{
		return qtyOnHand;
	}
	/** wholesale Getter */
	double getWholesale()
	{
		return wholesale;
	}
	/** retail Getter */
	double getRetail()
	{
		return retail;
	}

private://Private member data
	string bookTitle;
	string isbn;
	string author;
	string publisher;
	string dateAdded;
	int qtyOnHand;
	double wholesale;
	double retail;
	bool empty;

};

#endif // End of BOOKDATA_H
