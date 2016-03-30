package com.example.red;

import java.text.DecimalFormat;
import java.text.NumberFormat;




import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class List extends Activity  implements SensorEventListener{
	
	private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
     
     
    private static final NumberFormat nf = new DecimalFormat("##.########");
     
    private LocationManager locationManager;
    
    private float mLastX, mLastY,mLastZ ;
    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private final float NOISE = (float) 2.0;

    TextView tvX,tvY,tvZ;


    String id1,lat,lon;
    GPSTracker gps;
    	
    	
    	String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
	       
        
	       mywebview.loadUrl("file:///android_asset/yy/list.html"); 
	}
	
	public void logout(View v){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		
	}
	
	public void back(View v){
		Intent i = new Intent(this, List.class);
		startActivity(i);
		
	}
	
	public void clk(View v){
		Intent i = new Intent(this,Complaint.class);
		startActivity(i);
		
	}
	
	
	public void help(View v){
		
		Intent i = getIntent();
		name = i.getCharSequenceExtra("id").toString();
		
		gps = new GPSTracker(List.this);

		// check if GPS enabled		
        if(gps.canGetLocation()){
        	
        	double latitude = gps.getLatitude();
        	double longitude = gps.getLongitude();
        	
        	// \n is for new line
        	//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+"\n"+name, Toast.LENGTH_LONG).show();	
        }else{
        	// can't get location
        	// GPS or Network is not enabled
        	// Ask user to enable GPS/network in settings
        	gps.showSettingsAlert();
        }
		
		
		mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
		
        double latitude = gps.getLatitude();
    	double longitude = gps.getLongitude();
    	
    	if(latitude>=13.3443 && latitude<=13.345){
    		if(longitude>=74.7484 && longitude<=74.7486){
    			
    			 String phoneNo = "9686716970";
    			  String sms = "I Need Help, Current Location near to Udupi" ;

    			  try {
    				SmsManager smsManager = SmsManager.getDefault();
    				smsManager.sendTextMessage(phoneNo, null, sms,null, null);
    				
    				Toast.makeText(getApplicationContext(), "SMS Sent! Near to Udupi",
    						Toast.LENGTH_LONG).show();
    				
    				
    			  } catch (Exception e) {
    				Toast.makeText(getApplicationContext(),
    					"SMS faild, please try again later!",
    					Toast.LENGTH_LONG).show();
    				e.printStackTrace();
    			  }
    			  String phoneNo1 = "9620084454";
    			  String sms1 = "I Need Help, Current Location near to Udupi" ;

    			  try {
    				SmsManager smsManager = SmsManager.getDefault();
    				smsManager.sendTextMessage(phoneNo1, null, sms1, null, null);
    				
    				
    				
    			  } catch (Exception e) {
    				Toast.makeText(getApplicationContext(),
    					"SMS faild, please try again later!",
    					Toast.LENGTH_LONG).show();
    				e.printStackTrace();
    			  } 
    			  
    		}
    	}
		
		
    	else{ 
		  String phoneNo1 = "8884561521";
		  String sms1 = "I Need Help" ;

		  try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo1, null, sms1, null, null);
			
			Toast.makeText(getApplicationContext(), "SMS Sent!"+ " Latitude: "+ latitude + "Longitude : " +longitude,
					Toast.LENGTH_LONG).show();
			
		  } catch (Exception e) {
			Toast.makeText(getApplicationContext(),
				"SMS faild, please try again later!",
				Toast.LENGTH_LONG).show();
			e.printStackTrace();
		  } 
    	}

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	
}
