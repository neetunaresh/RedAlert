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

public class MainActivity extends Activity {
	EditText name, pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
       
         
       mywebview.loadUrl("file:///android_asset/yy/index.html"); 
       
       name = (EditText)findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		
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
				String na = name.getText().toString();
				//String s=na;
				Intent i = new Intent(getApplicationContext(),List.class);
				
				
				i.putExtra("id",na);
				
				
				startActivity(i);
			}else{
				
				s();
				
			}
				
		}
	}
	
	public void s(){
		Toast.makeText(this, "Invalid..", Toast.LENGTH_SHORT).show();
	}
	
	public void clk(View v){
		Intent i = new Intent(this, Reg.class);
		startActivity(i);
		
	}
	
	public void login(View v){
		String n,p;
		n = name.getText().toString();
		p = pass.getText().toString();
		
		if(n.equals("")){
			
			Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
		}
		

		else if(p.equals("")){
			
			Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
		}
		
		else if(n.equals("admin")&&p.equals("admin"))
		{
			name.setText("");
			pass.setText("");
		
			Intent i = new Intent(getApplicationContext(),Admlist.class);
			
			
			startActivity(i);
		}
		
		
		else
		{
			Download task = new Download();
			
			task.execute(new String[] { "http://yajna.orgfree.com/redalert/login.php?t1="+n+"&t2="+p });
			
			
			name.setText("");
			pass.setText("");
		


		}
		
	}
	
	
	
	
	
	
	
}
