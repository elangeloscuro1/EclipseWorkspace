import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
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
	
	
	
	private PrintWriter outputFile ;
	private String fileName ;
	private int labelCount ;
	
	
	
	
	
	
	
	
	//Opens the output file/stream and gets ready to
	//write into it.
	public CodeWriter(String vmFile)
	{
		try
		{
			fileName = vmFile.substring(0, vmFile.lastIndexOf(".")) ;
			outputFile = new PrintWriter(new FileOutputStream(fileName + ".asm")) ;					
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
			System.out.println("End of the program!") ;
			System.exit(0) ;
		}	
	}
	//Informs the code writer that the translation of a
	//new VM file is started.
	public void setFileName(String vmFile){}
	
	private void writeArithmeticPrefix()
	{
		// D must store the value to be pushed
		outputFile.println("@SP") ;
		outputFile.println("AM=M-1") ;
		outputFile.println("D=M") ;
		outputFile.println("A=A-1") ;
	}
	private void writePushSuffix()
	{
		// D must store the value to be pushed
		outputFile.println("@SP") ;
		outputFile.println("AM=M+1") ;
		outputFile.println("A=A-1") ;
		outputFile.println("M=D") ;
	}
	
	private void writePopSuffix()
	{
		// D must store the destination index to store the popped value
		outputFile.println("@R13") ;
		outputFile.println("M=D") ;
		outputFile.println("@SP") ;
		outputFile.println("AM=M-1") ;
		outputFile.println("D=M") ;
		outputFile.println("@13") ;
		outputFile.println("A=M") ;
		outputFile.println("M=D") ;	
	}	
		
	
	//Writes the assembly code that is the translation
	//of the given arithmetic command.
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
	public void writePush(String command, String segment, int index)
	{
		if (segment.equals(CONSTANT))
		{
			outputFile.println("@" + index) ;
			outputFile.println("D=A") ;
			writePushSuffix() ;
			
		}
		else if (segment.equals(TEMP))
		{
			outputFile.println("@" + (TEMP_BASE + index)) ;
			outputFile.println("D=M") ;
			writePushSuffix() ;
		}			
		else if (segment.equals(STATIC))
		{
			outputFile.println("@" + fileName + index) ;
			outputFile.println("D=M") ;
			writePushSuffix() ;
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
	
	
	
	//Writes the assembly code that is the translation
	//of the given command, where command is either
	//C_PUSH or C_POP.
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
	//Closes the output file.
	public void close()
	{
		outputFile.close() ;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
