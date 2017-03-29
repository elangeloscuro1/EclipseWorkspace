package com.example.apklocalservice ;


import android.app.Activity ;
import android.content.Intent ;
import android.os.Bundle ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.Button ;

public class MainActivity extends Activity
{

	private Button start, stop ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		
		start = (Button) findViewById(R.id.start) ;
		stop = (Button) findViewById(R.id.stop) ;
		
		start.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				startService(new Intent(getBaseContext(), MyService.class)) ;
			}
		});
		
		stop.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				stopService(new Intent(getBaseContext(), MyService.class)) ;				
			}
		});
		
		
		
	}

	
}
