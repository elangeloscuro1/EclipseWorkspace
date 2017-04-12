

package com.example.Apk_Minutos_A_Horas ;


import android.app.Activity ;
import android.os.Bundle ;
import android.view.KeyEvent ;
import android.view.View ;
import android.view.View.OnKeyListener ;
import android.widget.EditText ;

public class MainActivity extends Activity implements OnKeyListener
{

	private EditText hours, minutes, total ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;

		hours = (EditText) findViewById(R.id.hours) ;
		minutes = (EditText) findViewById(R.id.minutes) ;
		total = (EditText) findViewById(R.id.total) ;

		hours.setOnKeyListener(this) ;
		minutes.setOnKeyListener(this) ;
		total.setOnKeyListener(this) ;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event)
	{

		long hrs = getLong(hours.getText().toString()) * 60 ;
		long mins = getLong(minutes.getText().toString()) ;
		long time = getLong(total.getText().toString()) ;

		if (v == total)
		{
			hours.setText(Long.toString(time / 60)) ;
			minutes.setText(Long.toString(time % 60)) ;
		}
		else if (v == hours)
		{
			time = hrs + mins ;
			total.setText(Long.toString(time)) ;
			minutes.setText(Long.toString(time % 60)) ;

		}
		else if (v == minutes)
		{
			if (mins < 60)
			{
				total.setText(Long.toString(hrs + mins)) ;
			}
			else
			{
				time = hrs + mins ;
				minutes.setText(Long.toString(time % 60)) ;
				hours.setText(Long.toString(time / 60)) ;
				total.setText(Long.toString(time)) ;
			}
		}

		return false ;
	}

	public long getLong(String longValue)
	{
		try
		{
			return Long.parseLong(longValue) ;
		}
		catch (Exception e)
		{
			return 0 ;
		}
	}

	public String timeFormat(long time)// hh:mm:ss
	{
		return String.format("%02d", time) ;
	}

}
