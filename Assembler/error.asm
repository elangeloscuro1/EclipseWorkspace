// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.

//ALGORITHM:
//	Set R2 = 0 
//	Create a counter = R0
//	Create a Loop:
//		if (counter <= 0)
//			jump to END
//		else
//			set R2 = R2 + R1
//			decrement: D = counter = counter - 1
//			loop again
	//R2 = 0
	@R2			//A = R2 
	M=0			//Set RAM[A] = 0
	
	//counter = R0
	@R0			//A = R0
	D=M			//D = RAM[A]	
	@counter	//A = counter
	M=D			//M = RAM[counter]	
	
(LOOP//LOOP label
	//Jump to end of the program if D <= 0
	@END		//if (D <= 0) 
	D;JLE		//	Jump to END
	
	//Set R2 = R2 + R1
	@R1			//A = R1
	D=M			//D = RAM[A]	
	@R2			//A = R2
	M=M+D		//RAM[A] = RAM[A] + D
	
	//Decrement: D = counter = counter - 1
	@counter	//A = Counter
	MD=M-1		//D = RAM[A] = RAM[A] - 1
	
	//Jump back to loop
	@LOOP		//Jump back...
	0;JMP		//...to loop unconditionally	
(END)//END label
	@END		//End of..
	0;JMP		//..the Programs