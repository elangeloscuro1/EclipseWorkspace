package C19_2_NetworkingWithStreamSocketsDateServer ;

import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.net.ServerSocket ;
// import java.net.ServerSocket ;
import java.net.Socket ;

public class ClientHandler implements Runnable
{

	private ServerSocket serverSock ;

	public ClientHandler(ServerSocket serverSocket)
	{
		this.serverSock = serverSocket ;
	}

	@Override
	public void run()
	{
		try
		{
			Socket connectionSock = serverSock.accept() ;
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream())) ;
			DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream()) ;

			System.out.println("Connection made, waiting for client " + "to send their name.") ;

			String clientText = clientInput.readLine() ;
			System.out.println("CLIENT: " + clientText) ;
			
			String replyText = "Welcome, " + clientText +"\n" ;			
			clientOutput.writeBytes(replyText) ;

			clientOutput.close() ;
			clientInput.close() ;
			connectionSock.close() ;

		}
		catch (IOException e)
		{
			System.out.println("ERROR: ClientHandler ==> :" + e.getMessage()) ;
		}
	}

}
