/**
 * CS150 Final Project: Tetris
 * File Name:          	Tetris.h
 * Programmer:         	Angel Tapia
 * Date Last Modified: 	December 10, 2017
 */

#ifndef TETRIS_H
#define TETRIS_H

#include <ctime>
#include <string>
#include <cstdlib>
#include <iostream>
#include "Block.h"
#include "Cell.h"

class Tetris
{
public:
	/** board row */
	static const int BOARD_ROW = 20;
	/** board column */
	static const int BOARD_COL = 10;
	/** number of cell per block */
	static const int BLOCK_CELLS = 4;
	/** Total different blocks */
	static const int TOTAL_BLOCK = 7;
	/** the row to spawn a block  */
	static const int SPAWN_ROW = 0;
	/** the column to spawn a block  */
	static const int SPAWN_COL = 3;

	/**
	 * Default constructor:	Creates Blocks and adds them
	 * 			in an array of Blocks; and initializes:
	 * 			score = 0
	 * 			currentID = 0
	 * 			nextBlock = blocks[rand() % TOTAL_BLOCK]
	 * 			and current block blocks[rand() % TOTAL_BLOCK]
	 */
	Tetris()
	{
		int blockI[4][2] = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } };
		int blockJ[4][2] = { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 0 } };
		int blockL[4][2] = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } };
		int blockO[4][2] = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		int blockT[4][2] = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } };
		int blockS[4][2] = { { 0, 2 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
		int blockZ[4][2] = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } };

		blocks[0] = Block(blockI, 'I');
		blocks[1] = Block(blockJ, 'J');
		blocks[2] = Block(blockL, 'L');
		blocks[3] = Block(blockO, 'O');
		blocks[4] = Block(blockS, 'S');
		blocks[5] = Block(blockT, 'T');
		blocks[6] = Block(blockZ, 'Z');
		srand(time(0));
		score = 0;
		currentID = 0;
		nextBlock = blocks[rand() % TOTAL_BLOCK];
		currentBlock = blocks[rand() % TOTAL_BLOCK];

	}

	/**Destructor */
	~Tetris()
	{

	}

	/**
	 * Clears the screen to display an updated Tetris board:
	 * Displays: button menu, nextBlock, score
	 */
	void displayBoard()
	{
		system("cls");
		char block[BLOCK_CELLS][BLOCK_CELLS];
		nextBlock.getBlock(block);
		cout << "[x] Exit       \n[s] Rotate\n";
		cout << "[a] Move Left  \n[d] Move Right\n";

		cout << " -------------------\n";
		for (int i = 0; i < BOARD_ROW; i++) //print board
		{
			cout << "|";
			for (int j = 0; j < BOARD_COL; j++) //display each cell
			{
				cout << board[i][j].getCellSymbol()
						<< (j == BOARD_COL - 1 ? "|" : " ");
			}
			cout << (i == 0 ? " NEXT BLOCK" : "");
			if (i > 1 && i < 6) //display nextBlock
			{
				cout << "  ";
				for (int j = 0; j < BLOCK_CELLS; j++)
				{
					bool temp = block[i - 2][j] == nextBlock.getBlockSymbol();
					cout << (temp ? block[i - 2][j] : ' ') << " ";
				}
			}
			cout << (i == 6 ? " SCORE " : "");
			if (i == 8) //display score value
			{
				cout << "    " << score;
			}
			cout << endl;
		}
		cout << " -------------------\n";
	}

	/**
	 * Waits for an input from the user;
	 * returns a string that can be used
	 */
	char enterMove()
	{
		cin.clear();
		fflush(stdin);
		string temp;
		getline(cin, temp);
		return temp[0];
	}

	/**
	 * Checks if the given row index is full.
	 * return true if all of the cell are empty not empty; false otherwise.
	 */
	bool isRowFull(int row)
	{
		for (int i = 0; i < BOARD_COL; i++)
		{
			if (board[row][i].isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Deletes the given row and pulls down the
	 * rest of the rows.
	 */
	void clearRow(int row)
	{
		if (row == 0)
		{
			for (int i = 0; i < BOARD_COL; i++)
			{
				board[row][i].setCellID(board[row][i].CELL_DEFAULT_ID);
				board[row][i].setCellSymbol(board[row][i].CELL_DEFAULT_SYMBOL);
			}
		}
		else if (row > 0 && row < BOARD_ROW)
		{
			for (int i = row; i > 0; i--)
			{
				for (int j = 0; j < BOARD_COL; j++)
				{
					board[i][j] = board[i - 1][j];
					board[i][j] = board[i - 1][j];
				}
			}
		}
	}
	/**
	 * Deletes any row that is full; pulls down the
	 * rest of the rows; and updates scores by adding
	 * one  for each cleared row.
	 */
	void clearRow()
	{
		for (int i = BOARD_ROW - 1; i >= 0; i--)
		{
			if (isRowFull(i))
			{
				score++;
				clearRow(i++);
			}
		}
	}

	/**
	 * Runs the game:
	 * Displays the current update of the game
	 * and allows the user to enter a move.
	 *
	 */
	void startGame()
	{
		currentBlock.setBlockId(currentID++);//set new ID
		currentBlock.addToBoard(board, SPAWN_ROW, SPAWN_COL);//spawn
		displayBoard();//display board first time

		char makeMove = ' ';
		bool isMoved = true;
		while (isMoved && (makeMove = enterMove()) != 'x')
		{
			switch (makeMove)
			{
			case 'a'://Move current block to the left
			case 'A':
				currentBlock.moveBlock(board, 0, -1);
				break;
			case 'd'://Move current block to the right
			case 'D':
				currentBlock.moveBlock(board, 0, 1);
				break;
			case 's'://Rotate current block
			case 'S':
				currentBlock.rotate(board);
				break;
			default://invalid key was entered
				break;
			}
			isMoved = currentBlock.moveBlock(board, 1, 0);//move down
			displayBoard();//display update

			if (!isMoved)//true if block cannot be move any more
			{
				clearRow();//clears any full rows (increment score)

				currentBlock = nextBlock;//Update to nextBlock
				nextBlock = blocks[rand() % TOTAL_BLOCK];//Generate new block
				currentBlock.setBlockId(currentID++);//Set new ID
				isMoved = currentBlock.addToBoard(board, 0, 3); //spawn?
				displayBoard();//update or display last time
			}
		}
	}

private:
	int score; //The number of lines that heve been cleared
	int currentID; //Sets an ID of the current block: currentID++
	Block nextBlock;//The next block
	Block currentBlock;// The current block
	Block blocks[TOTAL_BLOCK];//The blocks available for the game
	Cell board[BOARD_ROW][BOARD_COL];//The board of the game
};

#endif /* TETRIS_H */
