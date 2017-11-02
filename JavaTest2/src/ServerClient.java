import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.net.ServerSocket ;
import java.net.Socket ;

@SuppressWarnings("resource")
public class ServerClient
{
	private static final String HOSTNAME = "localhost" ;
	private static final int PORT = 7654 ;	
	
	public static void main(String[] args)
	{
		server() ;
		//client() ;
	}// END of main
	
	
	public static void server()
	{
		boolean flag = true ;
		while (true)
		try
		{
			System.out.println("------------------------------------") ;
			System.out.println("Waiting for a connection on port " + PORT + ".") ;

			ServerSocket serverSock = new ServerSocket(PORT) ;
			Socket connectionSock = serverSock.accept() ;
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream())) ;
			DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

			System.out.println("Connection made, waiting data from client: ") ;
			System.out.println("CLIENT: " + clientInput.readLine()) ;
			System.out.print(">> ") ;
			String input = new java.util.Scanner(System.in).nextLine() + "\n" ;
			clientOutput.writeBytes(input) ;

			clientOutput.close() ;
			clientInput.close() ;
			connectionSock.close() ;
			serverSock.close() ;
		}
		catch (IOException e)
		{
			System.out.println("ERROR: DateServer ==> :" + e.getMessage()) ;
			System.exit(0) ;
		}

	}// END of server	
	
	
	
	public static void client()
	{
		while (true)
		{
			try
			{

				System.out.println("Connecting to server on port " + PORT) ;

				Socket connectionSock = new Socket(HOSTNAME, PORT) ;
				BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream())) ;
				DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

				System.out.print("Connection made, Send data to server: ") ;
				String input = new java.util.Scanner(System.in).nextLine() + "\n" ;
				serverOutput.writeBytes(input) ;
				
				String serverData = serverInput.readLine() ;
				System.out.println("SERVER: " + serverData) ;

				serverOutput.close() ;
				serverInput.close() ;
				connectionSock.close() ;
			}
			catch (IOException e)
			{
				System.out.println("ERROR: DateClient ==> : " + e.getMessage()) ;
				System.exit(0) ;
			}
		}
	}// END of client
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}