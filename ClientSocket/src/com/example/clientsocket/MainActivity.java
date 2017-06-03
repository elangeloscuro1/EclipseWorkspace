package com.example.clientsocket ;

import java.io.ObjectInputStream ;
import java.io.ObjectOutputStream ;
import java.net.Socket ;

import android.os.Bundle ;
import android.os.Handler ;
import android.os.Message ;
import android.app.Activity ;
import android.view.Menu ;
import android.view.View ;
import android.widget.TextView ;

public class MainActivity extends Activity
{
	TextView serverMessage ;
	Thread m_objThreadClient ;
	Socket clientSocket ;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		serverMessage = (TextView) findViewById(R.id.textView1) ;
	}

	public void Start(View view)
	{
		m_objThreadClient = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					clientSocket = new Socket("127.0.0.1", 2001) ;
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream()) ;
					oos.writeObject("Hellow there") ;
					Message serverMessage = Message.obtain() ;
					ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream()) ;
					String strMessage = (String) ois.readObject() ;
					serverMessage.obj = strMessage ;
					mHandler.sendMessage(serverMessage) ;
					oos.close() ;
					ois.close() ;
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace() ;
				}

			}
		}) ;

		m_objThreadClient.start() ;

	}

	Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			messageDisplay(msg.obj.toString()) ;
		}
	} ;

	public void messageDisplay(String servermessage)
	{
		serverMessage.setText("" + servermessage) ;
	}

}
