// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//ALGORITHM:
//	Set current register = @SCREEN = 16384
//	Create a LOOP:
//		if RAM[KBD] == 0
//			jump to CLEAR
//				start from the first screen register
//				check current register is inside of bound
//				set current screen to white(0)
//				increment
//		else
//			paint the screen




(LOOP)//main loop
	@SCREEN			//A = SCREEN = 16384
	D=A				//D = 16384
	@current		//A = current...
	M=D				//...stores the current register
	
	(PAINT)//paints in black
		@KBD		//if RAM[KBD] == 0...
		D=M			//...then, jump to...
		@CLEAR		//...CLEAR
		D;JEQ		//else, continue to the next line on code
		
		@current	//A = current
		AD=M		//AD = RAM[current]
		M=-1		//paint RAM[current] = -1 (black)
		@KBD		//A = KBD (this is one register outside the screen)
		D=A-D		//D = current - KBD
		@LOOP		//if D - 1 == 0 (next register is outside of the screen...
		D-1;JEQ		//...then, jump back to LOOP)
					//else continue to the next line
		@current	//increment..
		M=M+1		//...current and...
		@PAINT		//...jump back to PAINT...
		0;JMP		//...unconditionally
	
(CLEAR)//clears the screen
	@SCREEN			//Sets...
	D=A				//...current = SCREEN...
	@current		//...(first register...
	M=D				//... the screen)
	
	(CLEARING)//loops until the screen is cleared
		@current	//A stores the current screen register that is been cleared
		AD=M		//AD = RAM[current]
		M=0			//paints the crrent register back to white
		@KBD		//A = KBD (this is one register outside the screen)
		D=A-D		//D = current - KBD
		@LOOP		//if D - 1 == 0 (next register is outside of the screen...
		D-1;JEQ		//...then, jump back to LOOP)
					//else continue to the next line
		@current	//increment..
		M=M+1		//...current and...
		
		@CLEARING	//...jump back to CLEARING...
		0;JMP		//...unconditionally
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	