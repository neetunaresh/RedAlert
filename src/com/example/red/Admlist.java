package com.example.red;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class Admlist extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admlist);
		
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
	       
        
	       mywebview.loadUrl("file:///android_asset/yy/admlist.html"); 
	}
	
	public void ngo(View v){
		Intent i = new Intent(this, Admngo.class);
		startActivity(i);
		
	}
	
	public void edu(View v){
		Intent i = new Intent(this, Admeducation.class);
		startActivity(i);
		
	}
	
	public void hospital(View v){
		Intent i = new Intent(this, Admhospital.class);
		startActivity(i);
		
	}
	
	public void security(View v){
		Intent i = new Intent(this, Admsecurity.class);
		startActivity(i);
		
	}
	
}
