package com.example.apktest ;

//import android.support.v4.app.NotificationCompat ;
import android.util.Log ;
import android.annotation.SuppressLint ;
import android.app.Activity ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.content.Context ;
import android.content.Intent ;
import android.graphics.drawable.BitmapDrawable ;
import android.os.Bundle ;
import android.os.SystemClock ;
import android.view.View ;
import android.view.View.OnClickListener ;
import android.widget.Button ;
import android.widget.Chronometer ;
import android.widget.Chronometer.OnChronometerTickListener ;
import android.widget.Toast ;

public class Main extends Activity 
{

	Button button ;
	MyTimer timer ;

	public static final int ALERTA_ID = 1 ;

	String texto = "" ;// " TEXTO TEXTO TEXTO TEXTO" ;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.main) ;

		button = (Button) findViewById(R.id.button1) ;
		
		timer = new MyTimer(this) ;
		timer.setBase(SystemClock.elapsedRealtime() -  30000);
		
		
		


		timer.setOnChronometerTickListener(new OnChronometerTickListener()
		{
			@Override
			public void onChronometerTick(Chronometer chronometer)
			{
				
				
				button.setText(timer.getText()) ;
				
/*				Toast.makeText(Main.this, "is adkadjakdjkad", Toast.LENGTH_SHORT).show() ;
				
				long myElapsedMillis = SystemClock.elapsedRealtime() - timer.getBase() ;
				String strElapsedMillis = "Elapsed milliseconds: " + myElapsedMillis ;
				Toast.makeText(Main.this, strElapsedMillis, Toast.LENGTH_SHORT).show() ;*/
			}
		}) ;
		
		
		
		button.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				Toast.makeText(Main.this, "timer: " + (timer == null) , Toast.LENGTH_SHORT).show() ;
				if (timer.isRunning)
				{
					timer.stop() ;
					Toast.makeText(Main.this, "stop running", Toast.LENGTH_SHORT).show() ;
				}
				else
				{
					timer.start() ;
					Toast.makeText(Main.this, "start  running", Toast.LENGTH_SHORT).show() ;
				}
			}
		});

	}
	
	
	
	private class MyTimer extends Chronometer 
	{
		private boolean isRunning ;
		public MyTimer(Context context)
		{
			super(Main.this) ;
		}
		@Override
		public void start()
		{
			isRunning = true ;
			super.start() ;
		}
		@Override
		public void stop()
		{
			isRunning = false ;
			super.stop() ;
		}		
	}
	
	
	
	

	@SuppressWarnings("deprecation")
	@Override
	protected void onStop()
	{

/*		NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this) ;
		myBuilder.setSmallIcon(android.R.drawable.stat_sys_warning) ;
		myBuilder.setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher)).getBitmap()) ;
		myBuilder.setContentTitle("Este es un MENSAGE de alerta").setContentText("Este es el TEXTO de alerta" + texto) ;
		myBuilder.setContentInfo("2").setTicker("Alertaaaaaaaaa!!!!!!") ;

		PendingIntent conIntent = PendingIntent.getActivity(Main.this, 0, new Intent(this, Main.class), 0) ;
		myBuilder.setContentIntent(conIntent) ;

		NotificationManager myNotifi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE) ;
		myNotifi.notify(ALERTA_ID, myBuilder.build()) ;

		// Calling the onStop from the super class
		super.onStop() ;
*/
	}

}















