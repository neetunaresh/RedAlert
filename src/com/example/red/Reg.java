package com.example.red;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class Reg extends Activity {
	EditText fname,email,contact,address,password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		
		
		fname=(EditText)findViewById(R.id.editText1);
		email=(EditText)findViewById(R.id.editText2);
		contact=(EditText)findViewById(R.id.editText3);
		address=(EditText)findViewById(R.id.editText4);
		password=(EditText)findViewById(R.id.editText5);
		
		
		
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
	       
        
	       mywebview.loadUrl("file:///android_asset/yy/reg.html"); 
	       
	       
	}
	
	
	@SuppressLint("NewApi")
	private class Download extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));
					String s = "";
					while ((s = buffer.readLine()) != null) {
						response += s;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}
		

		@Override
		protected void onPostExecute(String result) {
			
			//msg.setText(result);
			if (result.contains("success")) {
				Toast.makeText(getApplicationContext(), "Successfully Registered", 30).show();
				finish();
			}else{Toast.makeText(getApplicationContext(), "Enter Details", 30).show();}
				
		}
	}
	
	
	public void back(View v){
		Intent i = new Intent(this, Home.class);
		startActivity(i);
		
	}
	
	public void reg(View v)
	{
		
		
		
		String t1,t2,t3,t4,t5,t6,t7,t8;
		t1 = fname.getText().toString();
		t2 = email.getText().toString();
		t3 = contact.getText().toString(); 
		t4 = address.getText().toString();
		t5 = password.getText().toString();
		
if(t1.equals("")){
			
			Toast.makeText(this, "Enter ", Toast.LENGTH_SHORT).show();
		}
		

		else if(t2.equals("")){
			
			Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
		}
		
else if(t3.equals("")){
			
			Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
		}
else if(t4.equals("")){
	
	Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
}
else if(t5.equals("")){
	
	Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
}
else{
		
		Download task = new Download();
		
		task.execute(new String[]{"http://yajna.orgfree.com/redalert/reg.php?fname="+t1+"&email="+t2+"&contact="+t3+"&address="+t4+"&password="+t5});
		
}		
	}
	
	
	
	
	
}
