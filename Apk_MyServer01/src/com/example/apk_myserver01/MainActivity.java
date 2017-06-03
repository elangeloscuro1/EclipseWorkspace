package com.example.apk_myserver01 ;


import java.net.ServerSocket ;

import android.app.Activity ;
import android.os.Bundle ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.Button ;
import android.widget.TextView ;

public class MainActivity extends Activity implements DataDisplay
{
	
	public static final String SERVICE_ON = "SERVICE IS ON" ;
	public static final String SERVICE_OFF = "SERVICE IS OFF" ;
	
	private TextView text ;
	private Button startService ;
	private Button stopService ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		text = (TextView) findViewById(R.id.text) ;
		
		startService = (Button) findViewById(R.id.start_service) ;
		stopService = (Button) findViewById(R.id.stop_service) ;
		
		startService.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				text.setText("START") ;// test				
			}
		});
		
		stopService.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				text.setText("STOP") ;// test
			}
		});
		
		
	}
	
	   public void connect(View view)
	   {
//		    MyServer server= new MyServer();
//		   	 server.setEventListener(this);
//		   	 server.startListening();

	   }
	   public void Display(String message)
	   {
		   text.setText(""+message);
	   }

}
