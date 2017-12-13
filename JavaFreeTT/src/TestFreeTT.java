import java.util.Scanner ;

public class TestFreeTT
{

	public static void main(String[] args)
	{

		// Defining Scanner Object to read data from console
		Scanner inputScanner = new Scanner(System.in) ;

		TextToSpeechConvertor ttsc = new TextToSpeechConvertor() ;

		System.out.println("Enter the Text : (type 'exit' to terminate)") ;

		// Reading the text
		String inputText = inputScanner.nextLine() ;

		while (true)
		{

			if ("-".equalsIgnoreCase(inputText))
			{

				inputText = "Exit!" ;
				ttsc.speak(inputText) ;
				break ;
			}

			ttsc.speak(inputText) ;

			System.out.println("Enter the Text : (type 'exit' to terminate)") ;
			inputText = inputScanner.nextLine() ;
		}

		inputScanner.close() ;

	}
}