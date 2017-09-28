package C19_2_NetworkingWithStreamSocketsDateServer ;


import java.net.ServerSocket ;

public class DateServer
{
	
	public static void main(String[] args)
	{
		while (true)
		{
			try
			{				
				System.out.println("------------------------------------") ;
				System.out.println("Waiting for a connection on port 7654.") ;
				
				ServerSocket serverSock = new ServerSocket(7654) ;
				ClientHandler handler = new ClientHandler(serverSock) ;
				Thread thread = new Thread(handler) ;
				thread.run() ;
				serverSock.close() ;
			}
			catch (Exception e)
			{
				System.out.println("ERROR: DateServer ==> :" + e.getMessage()) ;
			}
		}
		

	}
	
}



/*
public class DateServer// Original
{
	public static void main(String[] args)
	{
		Date now = new Date() ;
		try
		{
			System.out.println("Waiting for a connection on port 7654.") ;

			ServerSocket serverSock = new ServerSocket(7654) ;
			Socket connectionSock = serverSock.accept() ;
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream())) ;
			DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

			System.out.println("Connection made, waiting for client " + "to send their name.") ;

			String clientText = clientInput.readLine() ;
			String replyText = "Welcome, " + clientText + ", Today is " + now.toString() + "\n" ;
			clientOutput.writeBytes(replyText) ;

			System.out.println("Sent: " + replyText) ;

			clientOutput.close() ;
			clientInput.close() ;
			connectionSock.close() ;
			serverSock.close() ;
		}
		catch (IOException e)
		{
			System.out.println("ERROR: DateServer ==> :" + e.getMessage()) ;
		}
	}
}*/
