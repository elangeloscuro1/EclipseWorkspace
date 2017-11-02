/**
 * CodeWriter:	Writes the corresponding assembly code
 * 				of the given VM file into a new ASM file.
 * 
 * @author Angel Tapia
 * @version 1.0
 */
import java.io.PrintWriter ;

public class CodeWriter
{
	//	Memory segments for CodeWriter version 1.0	
	public static final String LCl_BASE  = "LCL" ;
	public static final String ARG_BASE  = "ARG" ;
	public static final String THIS_BASE = "THIS" ;
	public static final String THAT_BASE = "THAT" ;
	public static final int TEMP_BASE    = 5 ;
	public static final String TEMP      = "temp" ;
	public static final String CONSTANT  = "constant" ;
	public static final String STATIC    = "static" ;
	public static final String POINTER   = "pointer" ;		
	
	//	Instance variable for output stream file and label counter
	private PrintWriter outputFile ;
	private String fileName ;
	private int labelCount ;
	
	/**
	 * DESCRIPTION :	Receives a reference of a PrintWriter object
	 * 					and gets ready to write into it.
	 * PRECONDITION:	The reference of the PrintWriter must be initialized
	 * POSTCONDITION:	CodeWriter is able to write into asmFile
	 * @param asmFile:	The name of the file to be written on (including .asm)
	 */
	public CodeWriter(PrintWriter asmFile)
	{
		outputFile = asmFile ;	
	}
	
	/**
	 * DESCRIPTION:		Sets fimeName that is used for static variables
	 * PRECONDITION:	If used: static variables uses nameFile.i else null.i
	 * POSTCONDITION:	Static variables uses nameFile.i
	 * @param asmFile: The name of the file to be used (including .asm)
	 */
	public void setFileName(String asmFile)
	{
		fileName = asmFile.substring(0, asmFile.lastIndexOf(".")) ;
	}
	
	/**
	 * DESCRIPTION:		Writes the assembly code to calculate [SP] and [SP-1]
	 * PRECONDITION:	Can be used for  C_ARITHMETIC except: NOT and NEG
	 * POSTCONDITION:	Complete by writing: M=M-D, M=D+M, M=D&M, or M=D|M
	 */
	private void writeArithmeticPrefix()
	{
		outputFile.println("@SP") ;
		outputFile.println("AM=M-1") ;
		outputFile.println("D=M") ;
		outputFile.println("A=A-1") ;
	}
	
	/**
	 * DESCRIPTION:		Writes assembly code to push to the stack
	 * PRECONDITION:	Write code so D stores the value to be pushed
	 * POSTCONDITION:	The value of D is pushed to the stack
	 */
	private void writePushSuffix()
	{
		outputFile.println("@SP") ;
		outputFile.println("AM=M+1") ;
		outputFile.println("A=A-1") ;
		outputFile.println("M=D") ;
	}

	/**
	 * DESCRIPTION:		Writes assembly code to push to the stack
	 * PRECONDITION:	Write code so D stores the destination 
	 * 					index to store the popped value
	 * POSTCONDITION:	RAM[destination] contains the popped value
	 */
	private void writePopSuffix()
	{
		outputFile.println("@R13") ;
		outputFile.println("M=D") ;
		outputFile.println("@SP") ;
		outputFile.println("AM=M-1") ;
		outputFile.println("D=M") ;
		outputFile.println("@13") ;
		outputFile.println("A=M") ;
		outputFile.println("M=D") ;	
	}		

	/**
	 * DESCRIPTION: 	Writes the assembly code that is the translation
	 * 					of the given arithmetic command
	 * PRECONDITION:	command must be a valid VM code
	 * @param command:	VM code to be translated
	 */
	public void writeArithmetic(String command)
	{
		
		if (command.equals(Parser.NEG))
		{
			outputFile.println("@SP") ;
			outputFile.println("A=M-1") ;
			outputFile.println("M=!M") ;
		}
		else if (command.equals(Parser.NOT))
		{
			outputFile.println("@SP") ;
			outputFile.println("A=M-1") ;
			outputFile.println("M=-M") ;
		}
		else
		{
			writeArithmeticPrefix() ;
			
			if (command.equals(Parser.ADD))
			{
				outputFile.println("M=D+M") ;	
			}
			else if (command.equals(Parser.SUB))
			{		
				outputFile.println("M=M-D") ;	
			}
			else if (command.equals(Parser.AND))
			{		
				outputFile.println("M=D&M") ;	
			}
			else if (command.equals(Parser.OR))
			{		
				outputFile.println("M=D|M") ;	
			}
			else
			{
				String bool = command.equals(Parser.EQ) ? "JEQ" 
							: command.equals(Parser.GT) ? "JGT" :"JLT" ;				
				outputFile.println("MD=M-D") ;
				outputFile.println("@_" + (labelCount + 1)) ;
				outputFile.println("D;" + bool) ;
				outputFile.println("@_" + (labelCount + 2)) ;
				outputFile.println("D=0") ;
				outputFile.println("0;JMP") ;
				outputFile.println("(_" + (labelCount + 1) + ")") ;
				outputFile.println("D=-1") ;
				outputFile.println("(_" + (labelCount + 2) + ")") ;
				outputFile.println("@SP") ;
				outputFile.println("A=M-1") ;
				outputFile.println("M=D") ;
				labelCount += 2 ;
			}			
		}		
	}
	
	/**
	 * DESCRIPTION:		Writes the assembly code to push a value to the stack
	 * PRECONDITION:	command, segment and index must be a valid VM code
	 * @param command:	VM code to be translated
	 * @param segment:	Memory segment that is/stores the value to be pushed
	 * @param index:	The index/value of the memory segment
	 */
	private void writePush(String command, String segment, int index)
	{
		if (segment.equals(CONSTANT))
		{
			outputFile.println("@" + index) ;
			outputFile.println("D=A") ;
			writePushSuffix() ;// hard code would be better
		}
		else if (segment.equals(TEMP))
		{
			outputFile.println("@" + (TEMP_BASE + index)) ;
			outputFile.println("D=M") ;
			writePushSuffix() ;//hard code would be better
		}			
		else if (segment.equals(STATIC))
		{
			outputFile.println("@" + fileName + index) ;
			outputFile.println("D=M") ;
			writePushSuffix() ;// hard code would be better
		}
		else
		{
			if (segment.equals(POINTER))
			{
				String pointer = index == 0 ? THIS_BASE : THAT_BASE ;
				outputFile.println("@" + pointer) ;				
			}
			else
			{
				String location = segment.equals("this") ? THIS_BASE 
								: segment.equals("that") ? THAT_BASE
								: segment.equals("local") ? LCl_BASE : ARG_BASE ;  
				outputFile.println("@" + location) ;
				outputFile.println("D=M") ;
				outputFile.println("@" + index) ;
				outputFile.println("A=A+D") ;							
			}				
			outputFile.println("D=M") ;
			writePushSuffix() ;
		}
	}
	
	/**
	 * DESCRIPTION:		Writes the assembly code to pop a value from the stack
	 * PRECONDITION:	command, segment and index must be a valid VM code
	 * @param command:	VM code to be translated
	 * @param segment:	Memory segment that is/stores the value to store the popped value
	 * @param index:	The index/value of the memory segment
	 */
	public void writePop(String command, String segment, int index)
	{
		if (segment.equals(TEMP))
		{
			outputFile.println("@SP") ;
			outputFile.println("M=M-1") ;
			outputFile.println("A=M") ;
			outputFile.println("D=M") ;			
			outputFile.println("@" + (TEMP_BASE + index)) ;
			outputFile.println("M=D") ;
		}			
		else if (segment.equals(STATIC))
		{
			outputFile.println("@SP") ;
			outputFile.println("M=M-1") ;
			outputFile.println("A=M") ;
			outputFile.println("D=M") ;			
			outputFile.println("@" + fileName + index) ;
			outputFile.println("M=D") ;
		}
		else
		{
			if (segment.equals(POINTER))
			{
				String pointer = index == 0 ? THIS_BASE : THAT_BASE ;
				outputFile.println("@" + pointer) ;
				outputFile.println("D=A") ;					
			}
			else
			{
				String location = segment.equals("this") ? THIS_BASE 
								: segment.equals("that") ? THAT_BASE
								: segment.equals("local") ? LCl_BASE : ARG_BASE ; 			
				outputFile.println("@" + location) ;
				outputFile.println("D=M") ;
				outputFile.println("@" + index) ;
				outputFile.println("D=D+A") ;				
			}				
			writePopSuffix() ;
		}
	}	
	
	/**
	 * DESCRIPTION:		Writes the assembly code, of the given command
	 * 					where command is either C_PUSH or C_POP.
	 * PRECONDITION:	command, segment and index must be a valid VM code
	 * @param command:	VM code to be translated
	 * @param segment:	Memory segment that is/stores the value to be pushed/popped
	 * @param index:	The index/value of the memory segment
	 */
	public void writePushPop(String command, String segment, int index)
	{
		if (command.equals(Parser.PUSH))
		{
			writePush(command, segment, index) ;
		} 
		else
		{
			writePop(command, segment, index) ;	
		}
	}
	
	/**
	 * DESCRIPTION:		Closes the output file.
	 * POSTCONDITION:	Cannot longer write into the file
	 */
	public void close()
	{
		outputFile.close() ;
	}	
}