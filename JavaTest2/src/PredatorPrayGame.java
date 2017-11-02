

import java.util.Scanner ;



public class PredatorPrayGame
{

	public static void mainAAAAAA(String[] args)
	{
		for (int j = 0; j < 100; j++)
			System.out.println() ;
		PredatorPrayGame game = new PredatorPrayGame(15, 15) ;
		Scanner scan = new Scanner(System.in) ;
		game.buildWorld() ;
		char move = ' ' ;
		
		while ((move = scan.next().charAt(0)) != 'x' )
		{				
			game.movePray(move) ;			
			for (int j = 0; j < 100; j++)
			System.out.println() ;
			game.buildWorld() ;
		}
		scan.close() ;
		System.out.println("end of the game") ; 
		
	}
	
	public static int time ;
	private char predator ;
	private char pray ;	
	private char[][] world  ;
	private int rowPosition ;
	private int columnPosition ;
	
	
	public PredatorPrayGame(int row, int column)
	{
		world = new char[row][column] ;
	}
	
	
	
	char c = 'o' ;
	public void buildWorld()
	{
//		 rowPosition = (int) (Math.random() * world.length) ;
//		 columnPosition = (int) (Math.random() * world[0].length) ;
		
		char[] chars = {'w', 's', 'd', 'a'} ;
		//movePray(chars[(int) (Math.random() * chars.length)] ) ; 
		
		
		
		System.out.println(rowPosition + "  " + columnPosition) ;
		String bound = " " ;		
		for (int i = 0; i <= world[0].length -1 ; i++)
		{
			bound += "-" ;
		}
		System.out.println(bound) ;
		for (int row = 0; row < world.length; row++)
		{
			System.out.print("|") ;
			for (int column = 0; column < world[0].length; column++)
			{
				if (row == rowPosition && column == columnPosition && world[row][column] != ' ')
				{
					world[row][column] = 'X' ;
				}
				else
				{
					
					world[row][column] = ' ' ;
				}
				System.out.print(world[row][column]) ;
			}
			System.out.println("|") ;			
		}
		System.out.println(bound) ;
	}
	
	
	public void movePray(char direction)
	{
		
		if(direction == 'w' && rowPosition > 0)
		{
			rowPosition-- ;
		}
		else if(direction == 's' && rowPosition < world.length - 1)
		{
			rowPosition++ ;
		}
		else if(direction == 'a' && columnPosition > 0)
		{
			columnPosition-- ;
		}
		else if(direction == 'd' && columnPosition <  world[0].length - 1)
		{
			columnPosition++ ;
		}		
	}
	
	public boolean checkBounds(int row, int column)
	{
		if ( (row < 0 || row > world[0].length - 1) || (column < 0 || column > world.length - 1))
		{
			return false ;
		}
		else
		{
			return true ;
		}
	}




	
//	@Override
//	public void keyTyped(KeyEvent e)
//	{
//		System.out.println("keyTyped") ;
//		
//	}
//	
//	@Override
//	public void keyReleased(KeyEvent e)
//	{
//		System.out.println("keyReleased") ;
//		
//	}
//	
//	@Override
//	public void keyPressed(KeyEvent e)
//	{
//		System.out.println("keyPressed") ;
//		
//	}
	
/*	public void printCrittersWorld()
    {
        String topLine = " " ;
        String dividerLine = "" ;                
        for (int l = 0 ; l < worldColumn ; l++ )
        {            
            topLine += "----" ;     // generates a straight line
            dividerLine += "|---" ; // generates a line with bars
        }
        dividerLine = ((dividerLine.length() > 0)// adding the last bar
                    ? (dividerLine + "|") : dividerLine) ;        
        // prints board this is occupied by 'X', 'o' or ' '.
        System.out.println(topLine) ;
        for (int row = 0 ; row < worldRow ; row++ )
		{
            for (int column = 0 ; column < worldColumn ; column++ )
            {
                // Print one object in each cell
                String obj = ((Organism.getCritter(row, column) == null) ? " " 
                           : Organism.getCritter(row, column).getIdentification()) ;
                System.out.print("| " + obj + " ") ;
            }            
            System.out.print("|\n") ;
            
            dividerLine = ((row < (worldRow - 1)) ? dividerLine : topLine) ;
            System.out.println(dividerLine) ;// adding the last line     
        }        
    }
*/	
	
}
