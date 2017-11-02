import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.PrintWriter ;
import java.util.Scanner ;

public class VirtualMachinePart1
{
	

	public static void main(String[] args)
	{

		String path = System.getProperty("user.dir") + "\\TestFiles" ;

		File folder = new File(path) ;
		File[] listOfFiles = folder.listFiles() ;
/*
		for (int i = 0; i < listOfFiles.length; i++)
		{
			String nameIn = null, nameOut = null ;
			PrintWriter outputFile = null ; // keep compiler happy
			//SymbolTable symbolTable ;

			nameIn = listOfFiles[i].getPath() ;

			if (nameIn.substring(nameIn.indexOf(".") + 1).equals("vm"))
			{// System.out.printf("%s %n %s%n",listOfFiles[i].getName(),
				// nameOut) ;

				nameOut = listOfFiles[i].getName() ;
				nameOut = nameOut.substring(0, nameOut.lastIndexOf('.')) ;// + "0" ;
				nameOut = path + "\\" + nameOut + ".asm" ;

				try
				{
					outputFile = new PrintWriter(new FileOutputStream(nameOut)) ;
				}
				catch (FileNotFoundException ex)
				{
					System.err.println("Could not open output file " + nameOut) ;
					System.err.println("Run program again, make sure you have write permissions, etc.") ;
					System.exit(0) ;
				}

				// TODO: finish driver as algorithm describes

				//symbolTable = new SymbolTable() ;
				//firstPass(nameIn, symbolTable) ;
				//secondPass(nameIn, symbolTable, outputFile) ;
				outputFile.close() ;
				System.out.println(listOfFiles[i].getName() + " Done successfully!") ;

			}
		}
*///----------------------------------------------------------------------------------
		String[] a = {"BasicTest.vm","PointerTest.vm","SimpleAdd.vm","StackTest.vm","StaticTest.vm"} ;
		//String[] a = {"StaticTest.vm"} ;
		for (int j = 0; j < a.length; j++)
		{
			Parser p = new Parser( path + "\\" + a[j]) ;
			CodeWriter w = new CodeWriter(a[j]) ;
			
			for (int i = 1 ; p.hasMoreCommands() ; i++)
			{
				p.advance() ;				
				if (p.getCommand() != null && p.getSegment() != null && p.getIndex() != -1)
				{
					w.writePushPop(p.getCommand(), p.getSegment(), p.getIndex());
				}
				else if (p.getCommand() != null && p.getCommandType().equals(Parser.C_ARITHMETIC))
				{
					w.writeArithmetic(p.getCommand()) ;
				}				
				//new Scanner(System.in).nextLine() ;	
			}
			w.close() ; 
			
		}
		System.out.println("==============DONE!!==============") ; 
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}