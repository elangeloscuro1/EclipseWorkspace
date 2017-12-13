/**
 * CS150 Final Project: Tetris
 * File Name:          	Block.h
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	December 10, 2017
 */
#ifndef BLOCK_H
#define BLOCK_H

#include <iostream>
#include "Cell.h"

using namespace std;

class Block
{
public:

	/** board row */
	static const int BOARD_ROW = 20;
	/** board column */
	static const int BOARD_COL = 10;
	/** number of cell per block */
	static const int BLOCK_CELLS = 4;
	/** default ID for each block */
	static const int DEFAULT_ID = 0;
	/** default symbol for each block */
	static const char DEFAULT_SYMBOL = ' ';

	/**
	 * Default constructor: initializes rotateMap,
	 * 			BlockID and BlockSymbol to default values.
	 */
	Block()
	{
		initRotateMap();
		setBlockId(DEFAULT_ID);
		setBlockSymbol(DEFAULT_SYMBOL);
	}

	/**
	 * Constructor: takes in a block map and a symbol that
	 * 			represents the block. This Constructor initializes
	 * 			rotateMap and copies setBlockMap to blockMap and to
	 * 			currentMap.
	 * setBlockMap: The index map of a block. must be 4 index pairs
	 * 			where (0 =< row < 4, 0 =< col < 4).
	 * symbol: 	The symbol that represent the block.
	 */
	Block(int setBlockMap[BLOCK_CELLS][2], char symbol)
	{
		initRotateMap();
		setBlockId(DEFAULT_ID);
		setBlockSymbol(symbol);
		copyBlock(setBlockMap, blockMap, 0, 0);
		copyBlock(setBlockMap, currentMap, 0, 0);
	}

	/** Destructor */
	~Block()
	{
	}

	/**
	 * Setter for blockID:
	 * id:		The number that identifies the block
	 * 			(Default blockID = 0)
	 */
	void setBlockId(int id)
	{
		blockID = id;
	}

	/**
	 * Getter for blockID
	 * returns the current int value of blockID.
	 */
	int getBlockId()
	{
		return blockID;
	}

	/**
	 * Setter for blockSymbol:
	 * setSymbol: The symbol that represents the block.
	 */
	void setBlockSymbol(char setSymbol)
	{
		blockSymbol = setSymbol;
	}

	/**
	 * Getter for blockSymbol:
	 * returns the char value of blockSymbol.
	 */
	char getBlockSymbol()
	{
		return blockSymbol;
	}

	/**
	 * Initializes rotateMap:
	 * for each index:
	 * 			rotateMap[i][j][0] = j;
	 * 			rotateMap[i][j][1] = (3 - i);
	 */
	void initRotateMap()
	{
		for (int i = 0; i < BLOCK_CELLS; i++)
		{
			for (int j = 0; j < BLOCK_CELLS; j++)
			{
				rotateMap[i][j][0] = j;
				rotateMap[i][j][1] = (BLOCK_CELLS - i - 1);
			}
		}
	}

	/**
	 * Searches the first row index (top-left)
	 * 			of the first cell that is part of the block.
	 * returns the first row index of the block.
	 */
	int getRowIndex()
	{
		int row = currentMap[0][0];

		for (int i = 1; i < BLOCK_CELLS; i++)
		{
			int rowMap = currentMap[i][0];
			row = rowMap < row ? rowMap : row;
		}
		return row;
	}

	/**
	 * Searches the first column index (top-left)
	 * 			of the first cell that is part of the block.
	 * returns the first column index of the block.
	 */
	int getColIndex()
	{
		int col = currentMap[0][1];

		for (int i = 1; i < BLOCK_CELLS; i++)
		{
			int colMap = currentMap[i][1];
			col = colMap < col ? colMap : col;
		}
		return col;
	}

	/**
	 * Inserts the block symbol char value to the given char array.
	 * 			(no other initialization is perform to the given array).
	 * block: The char array to store the block.
	 */
	void getBlock(char block[][BLOCK_CELLS])
	{
		for (int i = 0; i < BLOCK_CELLS; i++)
		{
			int row = blockMap[i][0];
			int col = blockMap[i][1];
			block[row][col] = blockSymbol;
		}
	}

	/**
	 * Inserts the rotated (clockwise) block map to the given array.
	 * getMap: 	The int array to store the rotated values.
	 */
	void getRotatedBlockMap(int getMap[][2])
	{
		int helper[BLOCK_CELLS][2];
		for (int i = 0; i < BLOCK_CELLS; i++)
		{
			int mapRow = blockMap[i][0]; //get row and col of each cell
			int mapCol = blockMap[i][1];
			int rotateRow = rotateMap[mapRow][mapCol][0]; //get row and col rotation values
			int rotateCol = rotateMap[mapRow][mapCol][1];
			getMap[i][0] = rotateRow;
			getMap[i][1] = rotateCol;
		}

		while (copyBlock(getMap, helper, 0, -1)) //move map column to the left
		{
			copyBlock(helper, getMap, 0, 0);
		}
	}

	/**
	 * Makes a copy of the given block map and can change the row and
	 * 			column values for the resultant array.
	 * 			The resultant block must remain in bounds:
	 * 			(0 =< row < 20, 0 =< col < 10).
	 * from: 	The bloc map to be copied.
	 * to: 		The resultant block map.
	 * changeRow: The change in row for the resultant block.
	 * changeCol: The change in column for the resultant block.
	 * returns true if resultant value is in bounds; false otherwise.
	 */
	bool copyBlock(int from[][2], int to[][2], int changeRow, int changeCol)
	{
		for (int i = 0; i < BLOCK_CELLS; i++)
		{
			int row = from[i][0] + changeRow; //adding change in row and column
			int col = from[i][1] + changeCol;

			//Check for
			if (row < 0 || row >= BOARD_ROW || col < 0 || col >= BOARD_COL)
			{
				return false;
			}
			to[i][0] = row;
			to[i][1] = col;
		}
		return true;
	}

	/**
	 * Paints the block to the given board, using the given id and symbol.
	 * 			(Cannot perform if block is not in bound).
	 * board:	The board to be painted.
	 * id:		The ID int value that represents the block.
	 * symbol:	The symbol char value that represent the block.
	 */
	void paintBlock(Cell board[][BOARD_COL], int id, char symbol)
	{
		int helper[BLOCK_CELLS][2]; //checks bounds
		if (copyBlock(currentMap, helper, 0, 0))
		{
			for (int i = 0; i < BLOCK_CELLS; i++)
			{
				int row = currentMap[i][0];
				int col = currentMap[i][1];
				board[row][col].setCellID(id);
				board[row][col].setCellSymbol(symbol);
			}
		}
	}

	/**
	 * Checks if the intended move is valid.
	 * board: 	The board that contains the block.
	 * row:		The intended row index for the block.
	 * col: 	The intended column index for the block.
	 * returns true if intended cells are empty or share same id;
	 * 			false otherwise.
	 */
	bool isValidMove(Cell board[][BOARD_COL], int row, int col)
	{
		int helper[BLOCK_CELLS][2]; //helper
		if (copyBlock(currentMap, helper, row, col)) //checks in bound move
		{
			for (int i = 0; i < BLOCK_CELLS; i++)
			{
				int blockRow = helper[i][0];
				int blockCol = helper[i][1];
				Cell blockCell = board[blockRow][blockCol];

				if (!(blockCell.isEmpty() || blockCell.getCellID() == blockID))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Moves the block that is in the given board.
	 * board: 	The board that contains the block.
	 * row:		The new row index for the block.
	 * col:		The new column index for the block.
	 * returns true if the intended move is valid move; false otherwise.
	 */
	bool moveBlock(Cell board[][BOARD_COL], int row, int col)
	{
		int helper[BLOCK_CELLS][2]; //helper
		copyBlock(currentMap, helper, row, col); //copy currentMap

		if (isValidMove(board, row, col)) //check move
		{
			paintBlock(board, DEFAULT_ID, DEFAULT_SYMBOL); //un-paint currentMap
			copyBlock(helper, currentMap, 0, 0); //update currentMap
			paintBlock(board, blockID, blockSymbol); //paint currentMap
			return true; //move succeed
		}
		return false; //move failed
	}

	/**
	 * Adds the block to the given board.
	 * board: 	The board to be used for the block.
	 * row:		The row position where the blocks first appears.
	 * col:		The column position where the blocks first appears.
	 * returns true if the block is successfully added; false otherwise.
	 * 			(false = is not a valid move).
	 */
	bool addToBoard(Cell board[][BOARD_COL], int row, int col)
	{
		if (!moveBlock(board, row, col)) //board is full? spawn final block
		{
			copyBlock(blockMap, currentMap, row, col);
			paintBlock(board, blockID, blockSymbol);
			return false; //board might be full
		}
		return true; //added successfully
	}

	/**
	 * Rotates the block (cannot perform is rotation vales are not valid)
	 * 		and updates blockMap to the new rotated map.
	 * board: The board that contains the block.
	 */
	void rotate(Cell board[][BOARD_COL])
	{
		int currentMapCopy[BLOCK_CELLS][2]; //back ups currentMap
		int getRotateMap[BLOCK_CELLS][2]; //stores rotation Map
		int row = getRowIndex(); //current row location
		int col = getColIndex(); //current column location
		bool canRotate = true; //supposes rotation values are valid.

		//Copy to back up and get rotation map
		copyBlock(currentMap, currentMapCopy, 0, 0);
		getRotatedBlockMap(getRotateMap);

		//un-paint block
		paintBlock(board, DEFAULT_ID, DEFAULT_SYMBOL);

		//rotate currentMap
		copyBlock(getRotateMap, currentMap, 0, 0);

		//find valid location for rotation
		if (!moveBlock(board, row, col))
		{
			if (!moveBlock(board, row, col - 1))
			{
				if (!moveBlock(board, row, col - 2))
				{
					if (!moveBlock(board, row, col - 3))
					{
						if (!moveBlock(board, row, col + 1))
						{
							if (!moveBlock(board, row - 1, col))
							{
								//Coudn't rotate
								copyBlock(currentMapCopy, currentMap, 0, 0);
								moveBlock(board, 0, 0);
								canRotate = false;
							}
						}
					}
				}
			}
		}
		if (canRotate)		//if true: update blockMap
		{
			copyBlock(getRotateMap, blockMap, 0, 0);
		}
	}

private:
	int blockID; //The ID that identifies the block.
	char blockSymbol; //The symbol that represent the block.
	int blockMap[BLOCK_CELLS][2]; // Stores the values of the original block map.
	int currentMap[BLOCK_CELLS][2]; //Stores the index values of current block map.
	int rotateMap[BLOCK_CELLS][BLOCK_CELLS][2]; //Stores the index values of the rotation.
};

#endif /* BLOCK_H */
