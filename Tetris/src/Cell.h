/**
 * CS150 Final Project: Tetris
 * File Name:          	Cell.h
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	December 10, 2017
 */

#ifndef CELL_H
#define CELL_H

class Cell
{
public:
	/** Cell default cellID = 0 */
	static const int CELL_DEFAULT_ID = 0;
	/** Cell cellSymbol = ' ' */
	static const char CELL_DEFAULT_SYMBOL = ' ';

	/**
	 * Default Constructor:
	 * initializes cellID an dcellSymbol to default values.
	 */
	Cell()
	{
		setCellID(CELL_DEFAULT_ID);
		setCellSymbol(CELL_DEFAULT_SYMBOL);
	}

	/**
	 * Constructor: takes in values for cellID and symbol.
	 * cellID: 		An id number to be used for this cell object.
	 * cellSymbol: 	A symbol to be used for this cell object.
	 */
	Cell(int cellID, char cellSymbol)
	{
		setCellID(cellID);
		setCellSymbol(cellSymbol);
	}

	/** Destructor*/
	~Cell()
	{
	}

	/**
	 * Setter for cellSymbol:
	 * aSymbol: A symbol to be used for this cell object.
	 */
	void setCellSymbol(char aSymbol)
	{
		cellSymbol = aSymbol;
	}

	/**
	 * Getter for cellSymbol:
	 * returns the current char value of symbol.
	 */
	char getCellSymbol()
	{
		return cellSymbol;
	}

	/**
	 * Setter for cellID:
	 * setID: An id number to be used for this cell object.
	 */
	void setCellID(int setID)
	{
		cellID = setID;
	}

	/**
	 * Getter for cellID:
	 * returns the current int value of cellID.
	 */
	int getCellID()
	{
		return cellID;
	}

	/**
	 * Checks if cellSymbol contains a char value of space.
	 * returns true if cellSymbol contains a space; false otherwise.
	 */
	bool isEmpty()
	{
		return cellSymbol == CELL_DEFAULT_SYMBOL;
	}
private:
	int cellID; //represents the id number for this cell object.
	char cellSymbol; //a symbol to be used for this cell object.
};

#endif // End of CELL_H
