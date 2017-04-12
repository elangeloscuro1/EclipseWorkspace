package com.example.codigoqr ;


import android.app.Activity ;
import android.content.Intent ;
import android.os.Bundle ;
import android.widget.TextView ;
import android.widget.Toast ;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		
		//Intent intent = new Intent("com.example.codigoqr.SCAN") ;
		
		//startActivityForResult(intent, 0);
		

		Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
		intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
		intent.putExtra("ENCODE_DATA", "Hola ZOMWI!!!");
		startActivity(intent);
		
	}
	
/*	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		String contenido = "null" ;
		String formato = "null" ;
		if (requestCode == 0)
		{
			if (resultCode == RESULT_OK)
			{
				contenido = intent.getStringExtra("SCAN_RESULT") ;
				formato = intent.getStringExtra("SCAN_RESULT_FORMAT") ;
				
				
				Toast.makeText(this, contenido + "   " + formato, Toast.LENGTH_SHORT ).show() ;
			}
			else if (resultCode == RESULT_CANCELED)
			{
				Toast.makeText(this, contenido + "   " + formato, Toast.LENGTH_SHORT ).show() ;
				
			}
			((TextView) findViewById(R.id.text)).setText(contenido + "   " + formato) ;
		}
	}*/
}
