package com.example.apk_camara ;


import android.app.Activity ;
import android.content.Context ;
import android.hardware.camera2.CameraAccessException ;
import android.hardware.camera2.CameraManager ;
import android.os.Bundle ;
import android.widget.FrameLayout ;

public class MainActivity extends Activity
{
	
	 

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		 CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE); 
		 
		
	}

}
