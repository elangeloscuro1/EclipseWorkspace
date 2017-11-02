import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.InputStreamReader ;
import java.net.InetAddress ;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.net.UnknownHostException ;

@SuppressWarnings("resource")
public class ServerClient
{
	private static final String HOSTNAME = "localhost" ;// 127.0.0.1/localhost
	private static final int PORT = 7654 ;

	public static void main(String args[])
	{
		
		
		try
		{
			System.out.println(InetAddress.getLocalHost()) ;
			System.out.println(InetAddress.getLoopbackAddress()) ;
			// server() ;
			// client() ;
		}
		catch (UnknownHostException e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
		}

	}// END of main

	public static void server()
	{
		boolean flag = true ;
		while (flag)
			try
			{
				System.out.println("------------------------------------") ;
				System.out.println("Waiting for a connection on port " + PORT + ".") ;

				ServerSocket serverSock = new ServerSocket(PORT) ;
				Socket connectionSock = serverSock.accept() ;
				BufferedReader clientInput = new BufferedReader(
						new InputStreamReader(connectionSock.getInputStream())) ;
				DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

				System.out.println("Connection made, waiting data from client: ") ;
				System.out.println("CLIENT: " + clientInput.readLine()) ;
				String input = new java.util.Scanner(System.in).nextLine() + "\n" ;

				flag = input.charAt(0) != 'x' ;
				clientOutput.writeBytes(input) ;

				clientOutput.close() ;
				clientInput.close() ;
				connectionSock.close() ;
				serverSock.close() ;
			}
			catch (Exception e)
			{
				System.out.println("ERROR: DateServer ==> :" + e.getMessage()) ;
				System.exit(0) ;
			}

	}// END of server

	public static void client()
	{
		boolean flag = true ;
		while (flag)		
			try
			{
				System.out.println("Connecting to server on port " + PORT) ;

				Socket connectionSock = new Socket(HOSTNAME, PORT) ;

				BufferedReader serverInput = new BufferedReader(
						new InputStreamReader(connectionSock.getInputStream())) ;
				DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

				System.out.print("Connection made, Send data to server: ") ;
				String input = new java.util.Scanner(System.in).nextLine() + "\n" ;
				flag = input.charAt(0) != 'x' ;
				serverOutput.writeBytes(!flag ? input : "Client quited!!\n") ;

				String serverData = serverInput.readLine() ;
				System.out.println("SERVER: " + serverData) ;

				serverOutput.close() ;
				serverInput.close() ;
				connectionSock.close() ;
			}
			catch (Exception e)
			{
				System.out.println("ERROR: DateClient ==> : " + e.getMessage()) ;
				System.exit(0) ;
			}
	}// END of client
}