


package com.example.apkserver ;





import android.annotation.SuppressLint ;
import android.app.Activity ;
import android.os.Bundle ;
import android.widget.TextView ;



public class Main extends Activity
{

	private TextView text ;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.main) ;

		text = (TextView) findViewById(R.id.text2) ;
		text.setText("Text connnected with activity...") ;
		
		
		
		
		
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
