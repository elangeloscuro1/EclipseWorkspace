package com.example.apk_division_with_remainder ;

import android.app.Activity ;
import android.os.Bundle ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.Button ;
import android.widget.EditText ;
import android.widget.TextView ;

public class MainActivity extends Activity
{
	private EditText dividend, divisor ;
	private Button enter ;
	private TextView result ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		
		dividend = (EditText) findViewById(R.id.dividend) ;
		divisor  = (EditText) findViewById(R.id.divisor) ;
		enter    = (Button) findViewById(R.id.enter) ;
		result   = (TextView) findViewById(R.id.result) ;
		
		enter.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				if (dividend.getText().toString().length() > 0  && divisor.getText().toString().length() > 0)
				{
					int numerator = Integer.parseInt(dividend.getText().toString()) ;
					int denominator = Integer.parseInt(divisor.getText().toString()) ;
					int remainder = numerator % denominator ;
					
					String display =  numerator + "/" + denominator + " = " + (numerator / denominator) 
							+ (remainder != 0 ? "   Remainder: " + remainder : "" + (numerator / denominator) ) ;
					result.setText(display) ;					
				}
			}
		});
	}
}