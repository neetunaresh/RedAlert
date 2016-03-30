package com.example.red;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
	       
        
	       mywebview.loadUrl("file:///android_asset/yy/Home.html"); 	
		
	}
	
	public void login(View v){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		
	}
	public void user(View v){
		Intent i = new Intent(this, Reg.class);
		startActivity(i);
		
	}
	
	public void about(View v){
		Intent i = new Intent(this, About.class);
		startActivity(i);
		
	}
}
