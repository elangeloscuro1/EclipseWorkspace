import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;

public class FinalProjectDriver// extends JFrame
{
	
	
	Scanner airports2 = new Scanner("A\n"
								  + "B\n"
								  + "C\n"
								  + "D\n"
								  + "E\n"
								  + "F\n") ;
	
	Scanner costs2 = new Scanner("0  25 10 40  0   50 \n"
			                  + "0  0  0  10  20  0 \n"
			                  + "0  0  0  20  0   0 \n"
			                  + "0  0  0  0   30  0 \n"
			                  + "0  0  0  0   0   0 \n"
			                  + "0  0  0  15  0   0 \n") ;
	
	
	
	
	static Scanner airports = new Scanner("0\n"
							            + "1\n"
							            + "2\n"
							            + "3\n"
							            + "4\n") ;

	static Scanner costs = new Scanner("0  10 0  30  100  \n"
							         + "0  0  50 0   0    \n"
							         + "0  0  0  0   10   \n"
							         + "0  0  20 0   60   \n"
							         + "0  0  0  0   0    \n") ;
	
	
	public static void main(String[] args)
	{
		try
		{
			TravelPlanner2 test2 = new TravelPlanner2(new Scanner(new FileInputStream("FlightData.txt"))) ;

			Flight[] test3 = test2.getSortedByFromAToZdestination() ;
			for (int i = 0; i < test3.length; i++)
			{
				System.out.println(test3[i].getDestination()) ;
				
			}
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage()) ;
		}
		
//		TravelPlanner test = null ;
//		try
//		{
//			test = new TravelPlanner(airports, costs) ;
//		}
//		catch (InputMismatchException | ParseException e)
//		{
//			System.out.println(e) ;
//		}
////		test.findCheapestPath(2, 4) ;	
//		System.out.println(test.addFlight(0, 1)) ;
		
	}

	
//	public FinalProjectDriver()
//	{
//		TravelPlanner ports = new TravelPlanner(ver2, wei2) ;
//		setTitle("ADMIN") ;
//		//setSize(800, 800) ;
//		setLayout(new FlowLayout());
//		for (int i = 0; i < ports.vertices.length; i++)
//		{
//			add(new JButton(ports.vertices[i])) ;
//		}
//		pack() ;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
//		setLocationRelativeTo(null);
//		setVisible(true);	
//	}

}
