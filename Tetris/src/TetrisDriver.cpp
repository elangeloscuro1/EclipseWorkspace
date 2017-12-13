/**
 * CS150 Final Project: Tetris
 * File Name:          	TetrisDriver.cpp
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	December 10, 2017
 *
 * Problem Statement:	This program runs the Tetris
 * 						game.
 * Algorithm:
 *	1.Create a Cell class that represent a cell of the Tetris board:
 *		-Create constant:
 *			int CELL_DEFAULT_ID = 0: The default value for a Cell object
 *			char CELL_DEFAULT_SYMBOL = ' '//The default symbol for a Cell object
 *		-Create instance variables:
 *			int cellID that represents the id number for the cell object.
 *			char cellSymbol that represent the symbol to be used for the cell object.
 *		-Create Constructors:
 *			Default Constructor that initializes cellID an dcellSymbol to default values.
 *			Constructor: takes in values for cellID and symbol.
 *		-Create Getters and setter for each instance variable.
 *		-Create a method isEmpty that check and returns true
 *		 if the CellSymbol is a space.
 *	2.Create a Block class that represent a block of the Tetris board:
 *		-Create constant:
 *			int BOARD_ROW = 20: The board row size
 *			int BOARD_COL = 10: The board column size
 *			int BLOCK_CELLS = 4: The number of cell per block
 *			int DEFAULT_ID = 0: The default ID for each block
 *			int DEFAULT_SYMBOL = ' ':The default symbol for each block
 *		-Create instance variables:
 *			int blockID: The ID that identifies the block.
 *			char blockSymbol: The symbol that represent the block.
 *			int blockMap[BLOCK_CELLS][2]: Stores the values of the original block map.
 *			int currentMap[BLOCK_CELLS][2]: Stores the index values of current block map.
 *			int rotateMap[BLOCK_CELLS][BLOCK_CELLS][2]: Stores the index values of the rotation.
 *		-Create Constructor:
 *			Default constructor: initializes rotateMap, BlockID
 *			and BlockSymbol to default values.
 * 			Constructor that takes in a block map and a symbol that represents the block.
 * 				Initializes rotateMap and copies setBlockMap to blockMap and to currentMap.
 *		-Create a Getters and Setter for blockID and blockSymbol
 *		-Create a function initRotateMap that initializes rotateMap:
 *			for each index:
 * 				rotateMap[i][j][0] = j;
 * 				rotateMap[i][j][1] = (3 - i);
 * 		-Create a method getRowIndex():
 * 			Create a local variable int row = currentMap[0][0]
 * 			Search the first row index (top-left) of the first cell that is part of the block.
 * 			Use a for loop that compares each row in currentMap.
 * 			if currentMap[i][0] < row: set row = currentMap[i][0].
 * 			returns the row.
 * 		-Create a method getColIndex():
 * 			Create a local variable int col = currentMap[0][1]
 * 			Search the first row index (top-left) of the first cell that is part of the block.
 * 			Use a for loop that compares each row in currentMap.
 * 			if currentMap[i][1] < col: set col = currentMap[i][1].
 * 			returns the col.
 * 		-Create a function getRotatedBlockMap(int getMap[][2]):
 *			Map the block into the given char array:
 *				get each row and column values that is store in blockMap
 *				set block[row][column] = blockSymbol
 *		-Create a function bool copyBlock(int from[][2], int to[][2], int changeRow, int changeCol):
 *			Use a nested lopp to copy each value in array from to array to:
 *				set row = from[i][0] + changeRow
 *				set col = from[i][1] + changeCol
 *				return false if row < 0 || row >= BOARD_ROW || col < 0 || col >= BOARD_COL
 *				else continue copying values
 *		-Create a getRotatedBlockMap(int getMap[][2]):
 *			Get each row and column in blockMap
 *			update row and column:
 *				int rotateRow = rotateMap[row][column][0]
 *				int rotateCol = rotateMap[row][column][1]
 *			set getmap:
 *				getMap[i][0] = rotateRow
 *				getMap[i][1] = rotateCol
 *			if There is empty index to the left: move left:
 *				while (copyBlock(getMap, helper, 0, -1))
 *		-Create a function paintBlock(Cell board[][BOARD_COL], int id, char symbol):
 *			Check that the block to be painted is inside board
 *			Get row and columns values from currentMap:
 *				set each painted cell to blockID and blockSymbol
 *		-Create a function bool isValidMove(Cell board[][BOARD_COL], int row, int col):
 *			Create a helper int helper[BLOCK_CELLS][2]
 *			Check that the block will move inside the board.
 *				copyBlock(currentMap, helper, row, col): if true:
 *					for each cell to be occupied:
 *						Check that the block will not in an nonempty cell.
 *			if one of this tests fails return false: else return true;
 *		-Create a function bool moveBlock(Cell board[][BOARD_COL], int row, int col)
 *			if isValifMove:
 *				Unpaint currentMap.
 *				Update currentMap to valid move.
 *				Paint currentMap to its new location.
 *			return true for succeed move; false otherwise.
 *		-Create a function bool addToBoard(Cell board[][BOARD_COL], int row, int col):
 *			Check if the given row and column are inside the board.
 *			if validMove:
 *				update blockMap to currentMap:
 *					copyBlock(blockMap, currentMap, row, col);
 *				call paintBlock(board, blockID, blockSymbol);
 *			return true for addToBoard succeed ; false otherwise.
 *		-Create a function rotate(Cell board[][BOARD_COL]):
 *			Create int currentMapCopy[BLOCK_CELLS][2] to back up currentMap.
 *			Create int getRotateMap[BLOCK_CELLS][2] to store rotation Map
 *			Create int row = getRowIndex() to sore current row location
 *			Create	int col = getColIndex() to store current column location
 *			Copy currentMap to currentMapCopy
 *			Get rotated block map and store it in getRotatedMap.
 *			Un-paint currentMap.
 *			Update currentMap to getRotatedMap.
 *			Move block to current location: row, column
 *				if moveBlock == false: try col - 1, col - 2, col -3, row - 1.
 *			If the block is rotated succesfully:
 *				Update blockMap:
 *					copyBlock(getRotateMap, blockMap, 0, 0)
 *
 *	3.Create a Tetris class that puts all of the pieces of the
 *	  Tetris game together :
 *		-Create constant:
 *			int BOARD_ROW = 20: The board row size.
 *			int BOARD_COL = 10: The board column size.
 *			int BLOCK_CELLS = 4: The number of cell per block.
 *			int TOTAL_BLOCK = 7: the number of different blocks for the game.
 *			int SPAWN_ROW = 0: the row to spawn a block.
 *			int SPAWN_COL = 3: the column to spawn a block.
 *		-Create instance variables:
 *			int score: The number of lines that heve been cleared.
 *			int currentID: The ID of the current block: currentID++.
 *			Block nextBlock: The next block.
 *			Block currentBlock: The current block.
 *			Block blocks[TOTAL_BLOCK]: The blocks available for the game.
 *			Cell board[BOARD_ROW][BOARD_COL]: The board of the game.
 *		-Create the default constructor that:
 *			Create 7 different index map for seven different blocks
 *			Create 7 blocks that takes eack index maps
 *			Store each block in block[0.1.2...]
 *			Use srand(time(0)) to set random blosck for nectBlock
 *			set nextBlock = blocks[rand() % TOTAL_BLOCK]
 *			set currentBlock = blocks[rand() % TOTAL_BLOCK]
 *		-Create a function displayBoard():
 *			Call the function system("cls") to clear the scren
 *			Create a char tempBlock to get nextBlock.getBlock(block)
 *			Create a nested for loop to display the board:
 *				for (int j = 0; j < BOARD_COL; j++) //display each cell
 *				if (i > 1 && i < 6) //display nextBlock
 *					for (int j = 0; j < BLOCK_CELLS; j++)//display row of the nextBlock
 *				if (i == 8) //display score value
 *		-Create a char function enterMove():
 *			flush cin
 *			Create a string temp
 *			getLine(cin, temp)
 *			return temp[0]
 *		-Create a function bool isRowFull(int row):
 *			return false if one of the cell of the given row for board if empty
 *			Else return true.
 *		Create a function clearRow(int row):
 *			if row == 0 clear row 0
 *			else use a nested loop to set:
 *				board[i][j] = board[i - 1][j]
 *				board[i][j] = board[i - 1][j];
 *		Overload the clearRow()
 *			for each row: check for any full rows:
 *			 for every full row: clear row and increment score.
 *		Create a funtion startGame():
 *			set an  id to current block.
 *			Add currentBloak to board.
 *			While(block is movin and enterMove !- x)
 *				move block:
 *					a|| A = move left: currentBlock.moveBlock(board, 0, -1)
 *					d|| D = move Rght: currentBlock.moveBlock(board, 0,  1)
 *					s|| S = rotate:    currentBlock.rotate(board)
 *				Always Move down: currentBlock.moveBlock(board, 1, 0)
 *				if block did not move:
 *					set currentBlock = nextBlock
 *					generate ne block for nextBlock
 *					set ID and add currentBlock to board.
 *	4.Create a TetrisDriver that runs a Tetris game:
 *		-Create a flag char playAgain
 *		Do-while (playAgain == 'y' || playAgain == 'Y')
 *			Create a new Tetris object
 *			start game
 *			Ask the user to choose to play or not again.
 *
 * Classes needed and Purpose (Input, Processing, Output):
 * #include <string>    // Allows the use of the string class.
 *#include "Tetris.h"   // Allows the user to crete an inctance
 *						//Tetris class
 */

#include <iostream>
#include "Tetris.h"

using namespace std;

int main()
{
	char playAgain = 'y';
	do
	{
		Tetris tetris; //Create an instance of the game Tetris
		tetris.startGame(); //start game
		cout << "PLAY AGAIN (y/n)?";
		cin.clear();
		fflush(stdin);
		cin >> playAgain;
	}
	while (playAgain == 'y' || playAgain == 'Y');
	return 0;
}
