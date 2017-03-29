/*
package servidor_java ;
package Servidor ;

import java.util.Date ;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.io.DataOutputStream ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.io.IOException ;

public class DateServer1
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
			
			
			
			System.out.println("Connection made, waiting for client to send their name.") ;
			String clientText = clientInput.readLine() ;
			
			
			
			String replyText = "Welcome, " + clientText + ", Today is " + now.toString() + "\n" ;
			clientOutput.writeBytes("SERVER: " + replyText) ;
			System.out.println("Sent: " + replyText) ;
			
			
			
			clientOutput.close() ;
			clientInput.close() ;
			connectionSock.close() ;
			serverSock.close() ;
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage()) ;
		}
	}
	
	//*********************************************************
	public static void myWaitingTime()
	{
		try
		{
			Thread.sleep(500);
		}
		catch (Exception e)
		{
			
		}
	}
	
}*/