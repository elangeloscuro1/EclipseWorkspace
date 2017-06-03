package C19_2_NetworkingWithStreamSocketsDateClient ;

import java.net.Socket ;
import java.io.DataOutputStream ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.io.IOException ;

public class DateClient
{
	public static void main(String[] args)
	{
		while (true)
		{
			try
			{
				String hostname = "localhost" ;// OR: http://canyouseeme.org/
				int port = 7654 ;

				System.out.println("Connecting to server on port " + port) ;

				Socket connectionSock = new Socket(hostname, port) ;
				BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream())) ;
				DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

				System.out.print("Connection made, Send name: ") ;				
				serverOutput.writeBytes(new java.util.Scanner(System.in).nextLine() + "\n") ;

				String serverData = serverInput.readLine() ;
				System.out.println("SERVER: " + serverData) ;

				serverOutput.close() ;
				serverInput.close() ;
				connectionSock.close() ;
			}
			catch (IOException e)
			{
				System.out.println("ERROR: DateClient ==> : " + e.getMessage()) ;
			}
		}
	}
}

/*
public class DateClient // Original
{
	public static void main(String[] args)
	{
		try
		{
			String hostname = "localhost" ;
			int port = 7654 ;
			
			System.out.println("Connecting to server on port " + port) ;
			
			Socket connectionSock = new Socket(hostname, port) ;
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream())) ;
			DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream()) ;
			
			System.out.println("Connection made, sending name.") ;
			
			serverOutput.writeBytes("Dusty Rhodes\n") ;
			
			System.out.println("Waiting for reply.") ;
			
			String serverData = serverInput.readLine() ;
			
			System.out.println("Received: " + serverData) ;
			
			serverOutput.close() ;
			serverInput.close() ;
			connectionSock.close() ;
		}
		catch (IOException e)
		{
			System.out.println("ERROR: DateClient ==> : " + e.getMessage()) ;
		}
	}
}*/