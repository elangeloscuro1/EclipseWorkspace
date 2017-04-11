package com.example.quickresponsecode ;


import android.app.Activity ;
import android.content.Intent ;
import android.os.Bundle ;
import android.widget.TextView ;

public class MainActivity extends Activity
{

	TextView text ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		text = (TextView) findViewById(R.id.text) ;
		
		
//		Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
//		intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
//		intent.putExtra("ENCODE_DATA", "Hola ZOMWI!!!");
//		startActivity(intent);
		
		//Intent intent = new Intent("com.example.quickresponsecode.SCAN");
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		startActivityForResult(intent, 0);
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if (requestCode == 0)
		{
			if (resultCode == RESULT_OK)
			{
				String contenido = intent.getStringExtra("SCAN_RESULT") ;
				String formato = intent.getStringExtra("SCAN_RESULT_FORMAT") ;
				text.setText(contenido + " > " + formato) ;

			}
			else if (resultCode == RESULT_CANCELED)
			{
				text.setText("Cancelled") ;
			}
		}
	}

}
